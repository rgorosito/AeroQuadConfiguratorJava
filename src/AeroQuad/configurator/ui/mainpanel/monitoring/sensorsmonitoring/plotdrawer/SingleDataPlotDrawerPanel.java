package AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.plotdrawer;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class SingleDataPlotDrawerPanel extends JPanel
{
    private TimeSeries _series = new TimeSeries("");
    private boolean _visible = true;

    public SingleDataPlotDrawerPanel(final String name)
    {
        init(name);
    }

    private void init(final String name)
    {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        final TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection(_series);
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(name, "Time", "Value", timeSeriesCollection, true, true, false);
        final XYPlot xyplot = (XYPlot) chart.getPlot();
        final ValueAxis valueaxis = xyplot.getDomainAxis();
        valueaxis.setFixedAutoRange(15000);
        valueaxis.setAxisLineStroke(new BasicStroke(3));

        final ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setPreferredSize(new Dimension(0, 250));
        add(chartpanel);

    }

    public void addValue(final float value)
    {
        if (_visible)
        {
            _series.addOrUpdate(new Millisecond(), value);
        }
    }

    public void setValueVisible(final boolean visible)
    {
        _visible = visible;
    }
}
