package guru.qa;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.*;

public class PdfTest {
    @Test
    void pdfTest() throws IOException {
        String pdfFilePath = "./src/test/resources/file-pdf.pdf";
        String expectedData = "PDF";

        String actualData = readTextFromPdfPath(pdfFilePath);
        assertThat(actualData, containsString(expectedData));
    }
}
