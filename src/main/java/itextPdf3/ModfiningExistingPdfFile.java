package itextPdf3;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ModfiningExistingPdfFile {
	final static Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		PdfDocument pdfDocument = new PdfDocument(new PdfReader("split_2.pdf"), new PdfWriter("modified.pdf"));
		Document document = new Document(pdfDocument);

		document.add(new Paragraph("successfully added content in existing file"));
		logger.info("Successfully readed existing file .....");
		document.close();

	}
}
