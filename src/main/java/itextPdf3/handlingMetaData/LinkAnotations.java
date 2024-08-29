package itextPdf3.handlingMetaData;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;

public class LinkAnotations {
    public static void main(String[] args) {
        try {
            String src = "footer.pdf";
            String dest = "output_with_link.pdf";
            
            // Open the existing PDF
            PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
            
            // Create a link annotation
            Rectangle rect = new Rectangle(100, 700, 200, 20); // Position and size of the link
            PdfLinkAnnotation linkAnnotation = new PdfLinkAnnotation(rect);
            linkAnnotation.setAction(PdfAction.createURI("https://www.example.com"));

            // Add the annotation to a specific page
            pdfDoc.getFirstPage().addAnnotation(linkAnnotation);

            pdfDoc.close();
            System.out.println("Link annotation added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
