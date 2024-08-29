package itextPdf3.handlingMetaData;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;

public class HighlightAnotations {
    public static void main(String[] args) {
        try {
        	String src = "footer.pdf";
            String dest = "highlighted_output.pdf";

            // Open the PDF document
            PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
            PdfPage page = pdfDoc.getPage(1); // Get the first page

            // Define the rectangle for the highlight annotation
            Rectangle rect = new Rectangle(100, 500, 200, 20); // Adjust coordinates and size as needed

            // Create a highlight annotation
            PdfTextAnnotation highlight = new PdfTextAnnotation(rect);
            highlight.setColor(new DeviceRgb(255, 255, 0)); // Yellow color
            highlight.setFlag(PdfAnnotation.PRINT); // Optional: Set flags

            // Add the annotation to the page
            page.addAnnotation(highlight);

            // Close the document
            pdfDoc.close();
            System.out.println("Highlight annotation added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

