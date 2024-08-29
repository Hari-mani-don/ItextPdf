package itextPdf3.dynamicChartsGenerate;

import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class AreaChartToPDF {
	public static void main(String[] args) throws IOException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Sales", "January");
		dataset.addValue(4, "Sales", "February");
		dataset.addValue(3, "Sales", "March");
		dataset.addValue(5, "Sales", "April");

		JFreeChart areaChart = ChartFactory.createAreaChart("Monthly Sales", // Chart title
				"Month", // X-axis label
				"Sales", // Y-axis label
				dataset);
		ChartToPDFGeneralCode.writeChartToPDF(areaChart, 500, 400, "AreaChart.pdf");


	}
}
