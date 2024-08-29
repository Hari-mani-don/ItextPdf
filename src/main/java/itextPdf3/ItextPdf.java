package itextPdf3;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.AreaBreakType;

public class ItextPdf {

	public static void main(String[] args) throws IOException {
		
		 PdfDocument pdfDoc = new PdfDocument(new PdfWriter("output.pdf"));
         Document document = new Document(pdfDoc);

         // Add content to your document as needed
         document.add(new Paragraph("Hello, this is a sample document"));

         // Add a footer to the first page only
       //  pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler());

		// Close the document
		document.close();
		//logger.info("PDF generation successful");
		System.out.println("PDF generated successfully");
	}
}

//Logger logger = LogManager.getLogger(ItextPdf.class);
//logger.info("Starting PDF generation");
//
//// Create PdfWriter and PdfDocument
//PdfWriter writer = new PdfWriter("binary.pdf");
//PdfDocument pdfDocument = new PdfDocument(writer);
//
//// Create a Document
//Document document = new Document(pdfDocument, PageSize.A4);
//
//// Add content to the document
//for (int i = 0; i <= 5; i++) {
//	document.add(new Paragraph("Page: " + i));
//	if (i != 5) {
//		document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//	}
//}
//
//logger.info("Document content added");
//
//// Access the first page
//PdfPage page = pdfDocument.getPage(1);
//
//// Create PdfCanvas for the page
//// PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(),
//// page.getResources(), pdfDocument);
//
//// Add watermark text to the page
//if (page.getResources() == null) {
//	System.out.print("Page resources are not available.");
//	document.close();
//	return;
//}
//System.out.println(";sfs");
//// Create PdfCanvas for the page
//PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), pdfDocument);
//// Add watermark text to the page
//pdfCanvas.beginText().setFontAndSize(PdfFontFactory.createFont(), 60) // Ensure you have a valid font
//		.setColor(new DeviceRgb(200, 200, 200), true) // Set color and transparency
//		.moveText(200, 400) // Position
//		.showText("Watermark").endText();
