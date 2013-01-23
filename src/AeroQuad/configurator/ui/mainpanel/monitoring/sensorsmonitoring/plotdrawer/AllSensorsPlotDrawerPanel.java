package AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.plotdrawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class AllSensorsPlotDrawerPanel extends JPanel
{
    private final TimeSeries _gyroXSeries = new TimeSeries("Gyro X");
    private final TimeSeries _gyroYSeries = new TimeSeries("Gyro Y");
    private final TimeSeries _gyroZSeries = new TimeSeries("Gyro Z");
    private final TimeSeries _accelXSeries = new TimeSeries("Gyro X");
    private final TimeSeries _accelYSeries = new TimeSeries("Gyro Y");
    private final TimeSeries _accelZSeries = new TimeSeries("Gyro Z");
    private final TimeSeries _magXSeries = new TimeSeries("Gyro X");
    private final TimeSeries _magYSeries = new TimeSeries("Gyro Y");
    private final TimeSeries _magZSeries = new TimeSeries("Gyro Z");
    private boolean _gyroXVisible = true;
    private boolean _gyroYVisible = true;
    private boolean _gyroZVisible = true;
    private boolean _accelXVisible = true;
    private boolean _accelYVisible = true;
    private boolean _accelZVisible = true;
    private boolean _magXVisible = true;
    private boolean _magYVisible = true;
    private boolean _magZVisible = true;

    private JFreeChart _chart;

    public AllSensorsPlotDrawerPanel()
    {
        init();
    }

    private void init()
    {
        setLayout(new BorderLayout());

        final TimeSeriesCollection gyroXTimeSeriesCollection = new TimeSeriesCollection(_gyroXSeries);
        final TimeSeriesCollection gyroYTimeSeriesCollection = new TimeSeriesCollection(_gyroYSeries);
        final TimeSeriesCollection gyroZTimeSeriesCollection = new TimeSeriesCollection(_gyroZSeries);
        final TimeSeriesCollection accelXTimeSeriesCollection = new TimeSeriesCollection(_accelXSeries);
        final TimeSeriesCollection accelYTimeSeriesCollection = new TimeSeriesCollection(_accelYSeries);
        final TimeSeriesCollection accelZTimeSeriesCollection = new TimeSeriesCollection(_accelZSeries);
        final TimeSeriesCollection magXTimeSeriesCollection = new TimeSeriesCollection(_magXSeries);
        final TimeSeriesCollection magYTimeSeriesCollection = new TimeSeriesCollection(_magYSeries);
        final TimeSeriesCollection magZTimeSeriesCollection = new TimeSeriesCollection(_magZSeries);

        _chart = ChartFactory.createTimeSeriesChart("Sensors Display", "Time", "Value", gyroXTimeSeriesCollection, true, true, false);

        final XYPlot xyplot = (XYPlot) _chart.getPlot();
        final DateAxis valueaxis = (DateAxis)xyplot.getDomainAxis();
        valueaxis.setFixedAutoRange(15000);
        valueaxis.setAutoRange(true);
        valueaxis.setAutoTickUnitSelection(true);

        xyplot.setDataset(1, gyroYTimeSeriesCollection);
        final DefaultXYItemRenderer gyroYItemRenderer = new DefaultXYItemRenderer();
        gyroYItemRenderer.setBaseShapesVisible(false);
        xyplot.setRenderer(1, gyroYItemRenderer);

        xyplot.setDataset(2, gyroZTimeSeriesCollection);
        final DefaultXYItemRenderer gyroZItemRenderer = new DefaultXYItemRenderer();
        gyroZItemRenderer.setBaseShapesVisible(false);
        xyplot.setRenderer(2, gyroZItemRenderer);




        xyplot.setDataset(3, accelXTimeSeriesCollection);
        final DefaultXYItemRenderer accelXItemRenderer = new DefaultXYItemRenderer();
        accelXItemRenderer.setBaseShapesVisible(false);
        xyplot.setRenderer(3, accelXItemRenderer);

        xyplot.setDataset(4, accelYTimeSeriesCollection);
        final DefaultXYItemRenderer accelYItemRenderer = new DefaultXYItemRenderer();
        accelYItemRenderer.setBaseShapesVisible(false);
        xyplot.setRenderer(4, accelYItemRenderer);

        xyplot.setDataset(5, accelZTimeSeriesCollection);
        final DefaultXYItemRenderer accelZItemRenderer = new DefaultXYItemRenderer();
        accelZItemRenderer.setBaseShapesVisible(false);
        xyplot.setRenderer(5, accelZItemRenderer);


        xyplot.setDataset(6, magXTimeSeriesCollection);
        final DefaultXYItemRenderer magXItemRenderer = new DefaultXYItemRenderer();
        magXItemRenderer.setBaseShapesVisible(false);
        xyplot.setRenderer(6, magXItemRenderer);

        xyplot.setDataset(7, magYTimeSeriesCollection);
        final DefaultXYItemRenderer magYItemRenderer = new DefaultXYItemRenderer();
        magYItemRenderer.setBaseShapesVisible(false);
        xyplot.setRenderer(7, magYItemRenderer);

        xyplot.setDataset(8, magZTimeSeriesCollection);
        final DefaultXYItemRenderer magZItemRenderer = new DefaultXYItemRenderer();
        magZItemRenderer.setBaseShapesVisible(false);
        xyplot.setRenderer(8, magZItemRenderer);



        final ChartPanel chartpanel = new ChartPanel(_chart);
        chartpanel.setPreferredSize(new Dimension(0, 250));
        add(chartpanel);
    }

    public void addGyroX(final float v)
    {
        if (_gyroXVisible)
        {
            _gyroXSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addGyroY(final float v)
    {
        if (_gyroYVisible)
        {
            _gyroYSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addGyroZ(final float v)
    {
        if (_gyroZVisible)
        {
            _gyroZSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addAccelX(final float v)
    {
        if (_accelXVisible)
        {
            _accelXSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addAccelY(final float v)
    {
        if (_accelYVisible)
        {
            _accelYSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addAccelZ(final float v)
    {
        if (_accelZVisible)
        {
            _accelZSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addMagX(final float v)
    {
        if (_magXVisible)
        {
            _magXSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addMagY(final float v)
    {
        if (_magYVisible)
        {
            _magYSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addMagZ(final float v)
    {
        if (_magZVisible)
        {
            _magZSeries.addOrUpdate(new Millisecond(), v);
        }
    }

    public void addBaroAltitude(final float value)
    {
    }

    public void setGyroXVisible(final boolean gyroXVisible)
    {
        _gyroXVisible = gyroXVisible;
    }

    public void setGyroYVisible(final boolean gyroYVisible)
    {
        _gyroYVisible = gyroYVisible;
    }

    public void setGyroZVisible(final boolean gyroZVisible)
    {
        _gyroZVisible = gyroZVisible;
    }

    public void setAccelXVisible(final boolean accelXVisible)
    {
        _accelXVisible = accelXVisible;
    }

    public void setAccelYVisible(final boolean accelYVisible)
    {
        _accelYVisible = accelYVisible;
    }

    public void setAccelZVisible(final boolean accelZVisible)
    {
        _accelZVisible = accelZVisible;
    }

    public void setMagXVisible(final boolean magXVisible)
    {
        _magXVisible = magXVisible;
    }

    public void setMagYVisible(final boolean magYVisible)
    {
        _magYVisible = magYVisible;
    }

    public void setMagZVisible(final boolean magZVisible)
    {
        _magZVisible = magZVisible;
    }

}
