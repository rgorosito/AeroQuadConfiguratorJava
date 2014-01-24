package AeroQuad.configurator.ui.mainpanel.tuning;

import AeroQuad.configurator.ui.mainpanel.tuning.accro.AccroPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.accro.AccroPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.accro.IAccroPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.altitudehold.AltitudeHoldPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.altitudehold.AltitudeHoldPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.altitudehold.IAltitudeHoldPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.attitude.AttitudePidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.attitude.AttitudePidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.attitude.IAttitudePidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor.BatteryMonitorPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor.BatteryMonitorPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor.IBatteryMonitorPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.gps.GpsPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.gps.GpsPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.gps.IGpsPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.yaw.IYawPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.yaw.YawPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.yaw.YawPidPanelController;

import javax.swing.JPanel;

public class TuningPanelController implements ITuningPanelController
{
    private ITuningPanel _panel;

    private JPanel _accroPanel;
    private JPanel _attitudePanel;
    private JPanel _yawPanel;
    private JPanel _altitudePanel;
    private JPanel _batteryMonitorPanel;
    private JPanel _gpsPanel;

    public TuningPanelController()
    {
        buildChildscontrollersAndPanels();
    }

    private void buildChildscontrollersAndPanels()
    {
        final IAccroPidPanelController accroPanelController = new AccroPidPanelController();
        _accroPanel = new AccroPidPanel(accroPanelController);
        final IAttitudePidPanelController attitudePanelController = new AttitudePidPanelController();
        _attitudePanel = new AttitudePidPanel(attitudePanelController);
        final IYawPidPanelController yawPidPanelController = new YawPidPanelController();
        _yawPanel = new YawPidPanel(yawPidPanelController);
        final IAltitudeHoldPidPanelController altitudeHoldPidPanelController = new AltitudeHoldPidPanelController();
        _altitudePanel = new AltitudeHoldPidPanel(altitudeHoldPidPanelController);
        final IBatteryMonitorPidPanelController batteryMonitorPidPanelController = new BatteryMonitorPidPanelController();
        _batteryMonitorPanel = new BatteryMonitorPidPanel(batteryMonitorPidPanelController);
        final IGpsPidPanelController gpsPidPanelController = new GpsPidPanelController();
        _gpsPanel = new GpsPidPanel(gpsPidPanelController);
    }

    @Override
    public void setPanel(final ITuningPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void setUserLevel(final String beginner)
    {

    }

    @Override
    public void setActivated(final boolean activated)
    {

    }

    @Override
    public JPanel getAccroPanel()
    {
        return _accroPanel;
    }

    @Override
    public JPanel getAttitudePanel()
    {
        return _attitudePanel;
    }

    @Override
    public JPanel getYawPanel()
    {
        return _yawPanel;
    }

    @Override
    public JPanel getAltitudePanel()
    {
        return _altitudePanel;
    }

    @Override
    public JPanel getBatteryMonitorPanel()
    {
        return _batteryMonitorPanel;
    }

    @Override
    public JPanel getGpsPanel()
    {
        return _gpsPanel;
    }
}
