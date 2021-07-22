package guru.qa;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.readTextFromDocxPath;

public class DocsTest {
    @Test
    void docsTest() throws IOException {
        String docxFilePath = "./src/test/resources/docx-text.docx";
        String expectedData = "Did";

        String actualData = readTextFromDocxPath(docxFilePath);
        assertThat(actualData, containsString(expectedData));
    }
}






