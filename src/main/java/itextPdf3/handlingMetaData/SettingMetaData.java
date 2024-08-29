package itextPdf3.handlingMetaData;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfWriter;

public class SettingMetaData {
	public static void main(String[] args) {
		try {
			PdfWriter pdfWriter = new PdfWriter("meta.pdf");
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);

			PdfDocumentInfo pdfDocumentInfo = pdfDocument.getDocumentInfo();
			pdfDocumentInfo.setAuthor("Selvamani");
			pdfDocumentInfo.setKeywords("itext");
			pdfDocumentInfo.setTitle("metaData");
			pdfDocumentInfo.setSubject("learning itextPdf");

			System.out.println("Metadata set successfully.");
			pdfDocument.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
