package itextPdf3.dynamicChartsGenerate;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GanttChartToPdf {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		TaskSeries series = new TaskSeries("Scheduled Tasks");
		series.add(new Task("Task 1", dateFormat.parse("01-08-2024"), dateFormat.parse("10-08-2024")));
		series.add(new Task("Task 2", dateFormat.parse("11-08-2024"), dateFormat.parse("20-08-2024")));

		TaskSeriesCollection dataset = new TaskSeriesCollection();
		dataset.add(series);

		JFreeChart ganttChart = ChartFactory.createGanttChart("Project Schedule", // Chart title
				"Task", // X-axis label
				"Date", // Y-axis label
				dataset);

		ChartToPDFGeneralCode.writeChartToPDF(ganttChart, 500, 400, "GanttChart.pdf");
	}
}
