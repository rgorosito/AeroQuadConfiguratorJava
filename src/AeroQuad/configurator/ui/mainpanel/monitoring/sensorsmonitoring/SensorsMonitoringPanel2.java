package AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring;


import AeroQuad.configurator.ui.ConfiguratorPanel;
import AeroQuad.configurator.ui.IConfiguratorController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.plotdrawer.AllSensorsPlotDrawerPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.sensorsselectiontree.ISensorsSelectionTree;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.sensorsselectiontree.SensorsSelectionTree;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.sensorsselectiontree.TreeSelectionChangeListener;

import java.awt.BorderLayout;

public class SensorsMonitoringPanel2  extends ConfiguratorPanel implements ISensorsMonitoringPanel
{
    private final SensorsSelectionTree _sensorsTree = new SensorsSelectionTree();
    private final AllSensorsPlotDrawerPanel _plotPanel = new AllSensorsPlotDrawerPanel();

    private final SensorsMonitoringController _controller;

    public SensorsMonitoringPanel2(final SensorsMonitoringController controller)
    {
        _controller = controller;

        _controller.setPanel(this);

        init();
    }

    private void init()
    {
        setLayout(new BorderLayout());

        add(_sensorsTree, BorderLayout.WEST);
        add(_plotPanel);

        _sensorsTree.addSelectionChangeListener(new TreeSelectionChangeListener()
        {
            @Override
            public void selectionChanged(final String key, final boolean selected)
            {
                analyseTreeChangedEvent(key, selected);
            }
        });
    }

    @Override
    public IConfiguratorController getController()
    {
        return _controller;
    }

    @Override
    public void setHaveMagnetometer(final boolean value)
    {
        _sensorsTree.setHaveMagnetometer(value);
    }

    @Override
    public void setHaveBarometer(final boolean value)
    {
        _sensorsTree.setHaveBarometer(value);
    }

    @Override
    public void setGyroX(final String value)
    {
        _plotPanel.addGyroX(Float.parseFloat(value));
    }

    @Override
    public void setGyroY(final String value)
    {
        _plotPanel.addGyroY(Float.parseFloat(value));
    }

    @Override
    public void setGyroZ(final String value)
    {
        _plotPanel.addGyroZ(Float.parseFloat(value));
    }

    @Override
    public void setAccelX(final String value)
    {
        _plotPanel.addAccelX(Float.parseFloat(value));
    }

    @Override
    public void setAccelY(final String value)
    {
        _plotPanel.addAccelY(Float.parseFloat(value));
    }

    @Override
    public void setAccelZ(final String value)
    {
        _plotPanel.addAccelZ(Float.parseFloat(value));
    }

    @Override
    public void setMagX(final String value)
    {
        _plotPanel.addMagX(Float.parseFloat(value));
    }

    @Override
    public void setMagY(final String value)
    {
        _plotPanel.addMagY(Float.parseFloat(value));
    }

    @Override
    public void setMagZ(final String value)
    {
        _plotPanel.addMagZ(Float.parseFloat(value));
    }

    @Override
    public void setBaroAltitude(final float value)
    {
        _plotPanel.addBaroAltitude(value);
    }


    private void analyseTreeChangedEvent(final String key, final boolean selected)
    {
        if (key.equals(ISensorsSelectionTree.GYRO_X_KEY))
        {
            _plotPanel.setGyroXVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.GYRO_Y_KEY))
        {
            _plotPanel.setGyroYVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.GYRO_Z_KEY))
        {
            _plotPanel.setGyroZVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.GYRO_KEY))
        {
            _plotPanel.setGyroXVisible(selected);
            _plotPanel.setGyroYVisible(selected);
            _plotPanel.setGyroZVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.ACCEL_X_KEY))
        {
            _plotPanel.setAccelXVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.ACCEL_Y_KEY))
        {
            _plotPanel.setAccelYVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.ACCEL_Z_KEY))
        {
            _plotPanel.setAccelZVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.ACCEL_KEY))
        {
            _plotPanel.setAccelXVisible(selected);
            _plotPanel.setAccelYVisible(selected);
            _plotPanel.setAccelZVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.MAG_X_KEY))
        {
            _plotPanel.setMagXVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.MAG_Y_KEY))
        {
            _plotPanel.setMagYVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.MAG_Z_KEY))
        {
            _plotPanel.setMagZVisible(selected);
        }
        else if (key.equals(ISensorsSelectionTree.MAG_KEY))
        {
            _plotPanel.setMagXVisible(selected);
            _plotPanel.setMagYVisible(selected);
            _plotPanel.setMagZVisible(selected);
        }
        else
        {
            System.err.println("unsuported tree key");
        }
    }
}
