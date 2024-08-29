package itextPdf3;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

public class SPlitLargeFileIntoSmall {
	final static Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws IOException {
		PdfDocument pdfDocument = new PdfDocument(new PdfReader("/home/hari/Downloads/ReportingModule.pdf"));
		int totalPages = pdfDocument.getNumberOfPages();
		int splitNumberOfPages = 2;

		for (int i = 1; i <= totalPages; i += splitNumberOfPages) {
			PdfDocument srcPdfDocument = new PdfDocument(
					new PdfWriter("spliting" + ((i - 1) / splitNumberOfPages + 1) + ".pdf"));
			pdfDocument.copyPagesTo(i, Math.min(i + splitNumberOfPages - 1, totalPages), srcPdfDocument);
			srcPdfDocument.close();
		}
		logger.info("successfully splited file.........");
		pdfDocument.close();
	}
}
