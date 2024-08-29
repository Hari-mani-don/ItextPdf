package itextPdf3.handlingMetaData;

import java.io.File;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfReader;

public class ReadMetaData {
	public static void main(String[] args) {
		try {
			String src = "meta.pdf";
			File file = new File(src);
			if (file.exists() && file.length() > 0) {
				PdfDocument pdfDoc = new PdfDocument(new PdfReader(src));

				// Read document metadata
				PdfDocumentInfo info = pdfDoc.getDocumentInfo();
				System.out.println("Title: " + info.getTitle());
				System.out.println("Author: " + info.getAuthor());
				System.out.println("Subject: " + info.getSubject());
				System.out.println("Keywords: " + info.getKeywords());

				pdfDoc.close();
			} else {
				System.out.println("file does not exist or empty");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
