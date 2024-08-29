package itextPdf3.handlingMetaData;

import java.util.HashMap;
import java.util.Map;

import com.itextpdf.kernel.pdf.PdfCatalog;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;

public class BookMardPdf {
	public static void main(String[] args) {
		try {
			String dest = "bookmarked.pdf";
			PdfWriter writer = new PdfWriter(dest);
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document doc = new Document(pdfDoc);

			  // Get the catalog of the document
            PdfCatalog catalog = pdfDoc.getCatalog();
            
            // Create a map to hold the named destinations
            Map<String, PdfObject> namedDestinations = new HashMap<>();
            
            // Define a named destination with the name "chapter1"
            PdfDestination destination = PdfExplicitDestination.createFit(pdfDoc.getPage(1));
            namedDestinations.put("chapter1", destination.getPdfObject());

            // Add named destinations to the catalog
            catalog.getNameTree(PdfName.Dests).addNames(namedDestinations);
			doc.close();
			System.out.println("Bookmarks added successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
