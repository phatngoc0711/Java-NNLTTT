package UI.RevenueForm;

import java.awt.EventQueue;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Entity.Revenue;
import Model.RevenueModel;

public class ChartRevenue {
	public static JFreeChart createChart() throws ClassNotFoundException {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU TRONG THÁNG",
                "Tháng", "Nghìn đồng",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    public static CategoryDataset createDataset() throws ClassNotFoundException {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Revenue> listRevenues = new ArrayList<Revenue>();
        RevenueModel revenueModel = new RevenueModel();
        listRevenues = revenueModel.getAllRevenues();
        float[] ValueOnMonth = new float[12];
        for(Revenue revenue : listRevenues) {
        	if(revenue.getValue() > 0) {
        		int month = revenue.getDate().getMonth();
            	ValueOnMonth[month] += revenue.getValue();
        	}
        }
        dataset.addValue(ValueOnMonth[0], "Nghìn đồng", "1");
        dataset.addValue(ValueOnMonth[1], "Nghìn đồng", "2");
        dataset.addValue(ValueOnMonth[2], "Nghìn đồng", "3");
        dataset.addValue(ValueOnMonth[3], "Nghìn đồng", "4");
        dataset.addValue(ValueOnMonth[4], "Nghìn đồng", "5");
        dataset.addValue(ValueOnMonth[5], "Nghìn đồng", "6");
        dataset.addValue(ValueOnMonth[6], "Nghìn đồng", "7");
        dataset.addValue(ValueOnMonth[7], "Nghìn đồng", "8");
        dataset.addValue(ValueOnMonth[8], "Nghìn đồng", "9");
        dataset.addValue(ValueOnMonth[9], "Nghìn đồng", "10");
        dataset.addValue(ValueOnMonth[10], "Nghìn đồng", "11");
        dataset.addValue(ValueOnMonth[11], "Nghìn đồng", "12");
        return dataset;
    }

    /*public static void main(String[] args) throws ClassNotFoundException {
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }*/
    public ChartRevenue() throws ClassNotFoundException {
    	ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ Doanh thu");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
