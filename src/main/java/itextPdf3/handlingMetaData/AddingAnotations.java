package itextPdf3.handlingMetaData;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;

public class AddingAnotations {
	public static void main(String[] args) {
		try {
			String src = "footer.pdf";
			String dest = "output_with_annotation.pdf";

			// Open the existing PDF
			PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));

			// Create a text annotation (sticky note)
			Rectangle rect = new Rectangle(100, 600, 50, 50); // Position and size of the annotation
			PdfAnnotation annotation = new PdfTextAnnotation(rect).setContents("Hi selvamani"); // Annotation is open
																									// by default

			// Add the annotation to a specific page
			pdfDoc.getFirstPage().addAnnotation(annotation);

			pdfDoc.close();
			System.out.println("Annotations added successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
