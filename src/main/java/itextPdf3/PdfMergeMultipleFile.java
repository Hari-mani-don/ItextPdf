package itextPdf3;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;

public class PdfMergeMultipleFile {
	final static Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws IOException {
		PdfDocument pdfDocument = new PdfDocument(new PdfWriter("MergedFile.pdf"));
		PdfMerger pdfMerger = new PdfMerger(pdfDocument);

		String[] files = { "spliting1.pdf", "spliting2.pdf" };

		for (String file : files) {
			PdfDocument pdfRaderDocument = new PdfDocument(new PdfReader(file));
			pdfMerger.merge(pdfRaderDocument, 1, pdfRaderDocument.getNumberOfPages());
			pdfRaderDocument.close();

		}
		logger.info("successfully merged file.....");
		pdfDocument.close();

	}
}
