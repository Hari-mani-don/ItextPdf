package itextPdf3.dynamicChartsGenerate;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

public class HistogramToPdf {
	public static void main(String[] args) {
		HistogramDataset dataset = new HistogramDataset();
		double[] values = { 1.2, 2.3, 2.9, 3.7, 2.8, 2.5, 3.9, 1.8, 2.4, 2.9 };
		dataset.addSeries("Frequency", values, 10);

		JFreeChart histogram = ChartFactory.createHistogram("Value Distribution", // Chart title
				"Value", // X-axis label
				"Frequency", // Y-axis label
				dataset);

		ChartToPDFGeneralCode.writeChartToPDF(histogram, 500, 400, "Histogram.pdf");
	}
}
