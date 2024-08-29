package itextPdf3;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.AreaBreakType;

public class HeaderFooterAndWaterMark {
	private static final Logger logger = LogManager.getLogger(HeaderFooterAndWaterMark.class);

	public static void main(String[] args) throws IOException, FileNotFoundException, MalformedURLException {

		// Logger logger = LogManager.getLogger();
		logger.info("Generate pdf .............");
		// create
		PdfWriter pdfWriter = new PdfWriter("header.pdf");
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);

//		for (int i = 0; i < 5; i++) {
//			document.add(new Paragraph("Page " + i));
//			if (i <= 5) {
//				document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//			}
//		}
		// create Event
		pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderHandler());
		pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterHandler());
		pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new WaterMarkHandler());
		// add image into document
		ImageData imageData = ImageDataFactory.create("/home/hari/car.jpg");
		Image image = new Image(imageData).scaleToFit(500, 700);
		document.add(image);

		ImageData imageData1 = ImageDataFactory.create("/home/hari/car.jpg");
		Image image1 = new Image(imageData1).scaleToFit(500, 700);
		document.add(image1);

		logger.info("Generated pdf successfully .........");
		document.close();

	}

	static class HeaderHandler implements IEventHandler {

		@Override
		public void handleEvent(Event event) {
			PdfDocumentEvent pdfDocumentEvent = (PdfDocumentEvent) event;
			PdfDocument pdfDocument = pdfDocumentEvent.getDocument();
			PdfPage pdfPage = pdfDocumentEvent.getPage();

			PdfCanvas pdfCanvas = new PdfCanvas(pdfPage.newContentStreamBefore(), pdfPage.getResources(), pdfDocument);
			PdfFont pdfFont;
			try {
				pdfFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
				pdfCanvas.beginText().setFontAndSize(pdfFont, 20).setColor(ColorConstants.BLACK, true)
						.moveText(pdfPage.getPageSize().getWidth() / 2 - 55, pdfPage.getPageSize().getTop() - 30)
						.showText("Page Title");

			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pdfCanvas.endText().release();
		}

	}

	static class FooterHandler implements IEventHandler {

		@Override
		public void handleEvent(Event event) {
			PdfDocumentEvent pdfDocumentEvent = (PdfDocumentEvent) event;
			PdfDocument pdfDocument = pdfDocumentEvent.getDocument();
			PdfPage pdfPage = pdfDocumentEvent.getPage();

			PdfCanvas pdfCanvas = new PdfCanvas(pdfPage.newContentStreamBefore(), pdfPage.getResources(), pdfDocument);
			PdfFont pdfFont;
			try {
				pdfFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
				pdfCanvas.beginText().setFontAndSize(pdfFont, 20).setColor(ColorConstants.BLACK, true)
						.moveText(pdfPage.getPageSize().getWidth() / 2, pdfPage.getPageSize().getBottom() + 30)
						.showText("Page 1");

			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pdfCanvas.endText().release();
		}
	}

	static class WaterMarkHandler implements IEventHandler {
		@Override
		public void handleEvent(Event event) {
			PdfDocumentEvent pdfDocumentEvent = (PdfDocumentEvent) event;
			PdfDocument pdfDocument = pdfDocumentEvent.getDocument();
			PdfPage pdfPage = pdfDocumentEvent.getPage();

			PdfCanvas pdfCanvas = new PdfCanvas(pdfPage.newContentStreamBefore(), pdfPage.getResources(), pdfDocument);
			PdfFont pdfFont;
			float x = pdfPage.getPageSize().getWidth() / 2;
			float y = pdfPage.getPageSize().getHeight() / 2;
			try {
				pdfFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
				pdfCanvas.saveState();
				pdfCanvas.setColor(ColorConstants.LIGHT_GRAY, true);
				pdfCanvas.setTextMatrix(1, 1, -1, 1, x, y);
				pdfCanvas.beginText();
				pdfCanvas.setFontAndSize(pdfFont, 60);
				pdfCanvas.moveText(x - 150, y - 20); // Adjust position if needed
				pdfCanvas.showText("WATERMARK");
				pdfCanvas.endText();
				pdfCanvas.restoreState();
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
			pdfCanvas.release();
		}

	}

}
