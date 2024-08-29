package itextPdf3.dynamicChartsGenerate;

import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends JFrame {

	public static void main(String[] args) throws IOException { // Create a line chart
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Sales", "January");
		dataset.addValue(4, "Sales", "February");
		dataset.addValue(3, "Sales", "March");
		dataset.addValue(5, "Sales", "April");

		JFreeChart lineChart = ChartFactory.createLineChart("Monthly Sales", // Chart title
				"Month", // X-axis label
				"Sales", // Y-axis label
				dataset);

		// Write the chart to a PDF
		ChartToPDFGeneralCode.writeChartToPDF(lineChart, 500, 400, "LineChart.pdf");

	}
}
