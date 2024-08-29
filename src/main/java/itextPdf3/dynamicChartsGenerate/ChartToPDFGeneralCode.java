package itextPdf3.dynamicChartsGenerate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.JFreeChart;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class ChartToPDFGeneralCode {
	public static void writeChartToPDF(JFreeChart chart, int width, int height, String filePath) {
		// Convert the chart to a BufferedImage
		try {
			BufferedImage bufferedImage = chart.createBufferedImage(width, height);

			// Convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", baos);
			byte[] imageBytes = baos.toByteArray();

			// Use iText 7 to create a PDF document
			PdfWriter writer = new PdfWriter(filePath);
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document document = new Document(pdfDoc);

			// Add the chart image to the PDF document
			ImageData imageData = ImageDataFactory.create(imageBytes);
			Image pdfImage = new Image(imageData);
			document.add(pdfImage);

			// Close the document
			document.close();
			System.out.println("PDF  successfully at: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
