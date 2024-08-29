package itextPdf3.dynamicChartsGenerate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class BarChartToPDF {
    public static void main(String[] args) {
        // Create a bar chart using JFreeChart
        JFreeChart barChart = createBarChart();

        // Write the bar chart to a PDF file
        try {
            writeChartToPDF(barChart, 600, 400, "barchart.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JFreeChart createBarChart() {
        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "Sales", "January");
        dataset.addValue(3, "Sales", "February");
        dataset.addValue(0, "Sales", "March");
        dataset.addValue(5, "Sales", "April");

        // Create a bar chart
        return ChartFactory.createBarChart(
                "Monthly Sales",             // Chart title
                "Month",                    // X-axis label
                "Sales (Units)",            // Y-axis label
                dataset,                    // Dataset
                PlotOrientation.VERTICAL,   // Orientation
                true,                       // Include legend
                true,                       // Tooltips
                false                       // URLs
        );
    }

    private static void writeChartToPDF(JFreeChart chart, int width, int height, String filePath) throws IOException {
        // Convert the chart to a BufferedImage
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);

        // Convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        // Use iText 7 to create a PDF document
        PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add the chart image to the PDF document
        ImageData imageData = ImageDataFactory.create(imageBytes);
        Image pdfImage = new Image(imageData);
        document.add(pdfImage);

        // Close the document
        document.close();

        System.out.println("PDF created successfully at: " + filePath);
    }
}

