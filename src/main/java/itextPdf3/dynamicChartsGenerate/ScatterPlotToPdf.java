package itextPdf3.dynamicChartsGenerate;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlotToPdf {
	public static void main(String[] args) {
		XYSeries series = new XYSeries("Data Points");
		series.add(1.0, 5.0);
		series.add(2.0, 7.0);
		series.add(3.0, 3.0);
		series.add(4.0, 6.0);

		XYDataset dataset = new XYSeriesCollection(series);

		JFreeChart scatterPlot = ChartFactory.createScatterPlot("Scatter Plot", // Chart title
				"X-Axis", // X-axis label
				"Y-Axis", // Y-axis label
				dataset);

		ChartToPDFGeneralCode.writeChartToPDF(scatterPlot, 500, 400, "ScatterPlot.pdf");
	}
}
