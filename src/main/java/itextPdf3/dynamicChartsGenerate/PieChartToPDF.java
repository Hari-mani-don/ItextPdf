package itextPdf3.dynamicChartsGenerate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class PieChartToPDF {

	public static void main(String[] args) {
		// Create a pie chart using JFreeChart
		JFreeChart pieChart = createPieChart();
		
		
//		SwingUtilities.invokeLater(() -> {
//			JFrame frame = new JFrame("Pie Chart Example");
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.add(new JLabel(new ImageIcon(pieChart.createBufferedImage(500, 400))));
//			frame.pack();
//			frame.setVisible(true);
//		});
		// Write the pie chart to a PDF file
		try {
			writeChartToPDF(pieChart, 500, 400, "piecharts.pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static JFreeChart createPieChart() {
		// Create dataset for the pie chart
		DefaultPieDataset<String> dataset = new DefaultPieDataset();
		dataset.setValue("Category 40", 40);
		dataset.setValue("Category 30", 30);
		dataset.setValue("Category 20", 20);
		dataset.setValue("Category 10", 10);

		// Create the pie chart
		JFreeChart chart = ChartFactory.createPieChart("Sales Distribution", // chart title
				dataset, // dataset
				true, // include legend
				true, false);

		// Customize plot (optional)
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setSectionPaint("Hari A", new java.awt.Color(0, 100, 100));
		plot.setSectionPaint("Category B", new java.awt.Color(100, 255, 100));
		plot.setSectionPaint("Mani C", new java.awt.Color(100, 0, 255));
		plot.setSectionPaint("Category D", new java.awt.Color(255, 255, 100));

		return chart;
	}

	private static void writeChartToPDF(JFreeChart chart, int width, int height, String filePath) throws IOException {
		// Convert the chart to a BufferedImage
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
	}
}
