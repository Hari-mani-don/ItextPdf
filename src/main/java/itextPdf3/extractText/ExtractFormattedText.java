package itextPdf3.extractText;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;

public class ExtractFormattedText {
	public static void main(String[] args) {
		try {
			String src = "split_1.pdf"; // Path to the PDF file

			// Initialize the PDF document
			PdfDocument pdfDoc = new PdfDocument(new PdfReader(src));

			// Extract text with a strategy that preserves some formatting
			ITextExtractionStrategy strategy = new LocationTextExtractionStrategy();
			for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
				String text = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i), strategy);
				System.out.println("Page " + i + ": " + text);
			}

			pdfDoc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
