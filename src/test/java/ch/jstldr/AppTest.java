package ch.jstldr;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() throws URISyntaxException, IOException {

        String htmlContent = "<html>\n"
                + "<head>\n"
                + "    <style>\n"
                + "        img {\n"
                + "            max-height: 885px;\n"
                + "            max-width: 750px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
//                + "<div class=\"content\">\n"
//                + "    <div>"
//                + "        <p><span><span>Inline image:</span></span></p>\n"
                + "        <img height=\"310\" src=\"file:_2019-11-22_11-34-28.png\" width=\"700\"/>\n"
                //         + "        <img height=\"3301\" src=\"file:_2019-11-22_11-34-28.png\" width=\"1462\"/>\n"
//                + "    </div>\n"
//                + "</div>\n"
                + "</body>\n"
                + "</html>";

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.usePdfAConformance(PdfRendererBuilder.PdfAConformance.PDFA_2_A);

            builder
                    .withHtmlContent(htmlContent, "./")
                    .useFastMode()
                    .toStream(outputStream).run();
            FileUtils.writeByteArrayToFile(new File("test.pdf"), outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
