package guru.qa;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.readTextFromDocxPath;
import static utils.Files.readTextFromPath;
import static utils.Zip.unzip;


public class ZipTest {
    @Test
    void zipTest() throws IOException, ZipException {
        String zipFilePath = "./src/test/resources/archive.zip";
        String unzipFolderPath = "./src/test/resources/unzip";
        String password = "123";
        String unzipTxtFilePath = "./src/test/resources/unzip/file.txt";
        String expectedData = "Привет!";

        unzip(zipFilePath,password,unzipFolderPath);

        String actualData = readTextFromPath(unzipTxtFilePath);
        assertThat(actualData, containsString(expectedData));

    }
}
