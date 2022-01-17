package com.group.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;

/**
 * Class
 *
 * @author Scoowy
 * @version 2022.01.16.1833
 */
public class TweetRaterUI {
    public JButton btnRun;
    public JPanel mainPnl;
    public JTextField txtTweetId;
    public JPanel pnlChart;
    public JPanel pnlControls;
    public JPanel pnlStatistics;
    public JLabel lblContent;
    public JLabel lblImpact;
    public JLabel lblNumComments;
    public JLabel lblRetweetRate;

    public DefaultPieDataset dataset;

    public TweetRaterUI() {
        dataset = new DefaultPieDataset();

        JFreeChart pieChart = ChartFactory.createPieChart("Comentarios", dataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(pieChart);
        pnlChart.setLayout(new BorderLayout());
        pnlChart.add(chartPanel, BorderLayout.CENTER);
        pnlChart.validate();
    }
}
