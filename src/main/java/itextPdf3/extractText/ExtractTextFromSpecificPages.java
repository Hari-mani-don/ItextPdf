package itextPdf3.extractText;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

public class ExtractTextFromSpecificPages {
	    public static void main(String[] args) {
	        try {
	            String src = "split_1.pdf"; // Path to the PDF file

	            // Initialize the PDF document
	            PdfDocument pdfDoc = new PdfDocument(new PdfReader(src));

	            // Specify the pages you want to extract text from
	            int[] pagesToExtract = {1, 3, 5}; // Example: Extract text from pages 1, 3, and 5

	            for (int pageNum : pagesToExtract) {
	                if (pageNum <= pdfDoc.getNumberOfPages()) {
	                    String text = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(pageNum));
	                    System.out.println("Page " + pageNum + ": " + text);
	                } else {
	                    System.out.println("Page " + pageNum + " does not exist in this document.");
	                }
	            }

	            pdfDoc.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

