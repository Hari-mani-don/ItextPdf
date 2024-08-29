package itextPdf3.handlingMetaData;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfStampAnnotation;

public class StampAnotations {
	public static void main(String[] args) {
		try {
			String src = "footer.pdf";
			String dest = "stamped_output.pdf";

			PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
			PdfPage page = pdfDoc.getPage(1);

			Rectangle rect = new Rectangle(100, 500, 200, 50);
			PdfStampAnnotation stamp = new PdfStampAnnotation(rect);

			// Set the title using PdfString
			stamp.setTitle(new PdfString("Confidential"));
			stamp.setColor(new DeviceRgb(255, 0, 0)); // Red color

			// PdfStampAnnotation does not have setBorderWidth, so omit it

			page.addAnnotation(stamp);

			pdfDoc.close();

			System.out.println("Stamp annotation added successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
