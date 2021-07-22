package guru.qa;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.*;

public class XlsxTest {
    @Test
    void xlsxTest() throws IOException {
        String xlsxFilePath = "./src/test/resources/doc-xlsx.xlsx";
        String expectedData = "Розы (белые)";

        String actualData = readTextFromXlsxPath(xlsxFilePath);
        assertThat(actualData, containsString(expectedData));
    }
}
