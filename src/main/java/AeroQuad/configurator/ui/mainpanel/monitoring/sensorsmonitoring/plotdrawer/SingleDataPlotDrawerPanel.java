package AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.plotdrawer;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
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
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(name, null, null, timeSeriesCollection, false, false, false);

        final XYPlot xyplot = (XYPlot) chart.getPlot();
        final ValueAxis valueaxis = xyplot.getDomainAxis();
        valueaxis.setFixedAutoRange(20000);

        final ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setPreferredSize(new Dimension(0, 250));
        add(chartpanel);

    }

    public void addValue(float value)
    {
        if (!_visible)
        {
            value = 0.0F;
        }
        _series.addOrUpdate(new Millisecond(), value);
    }

    public void setValueVisible(final boolean visible)
    {
        _visible = visible;
    }
}
