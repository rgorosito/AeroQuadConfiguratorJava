package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

public class BatteryMonitorPidPanelController implements IBatteryMonitorPidPanelController
{
    private IBatteryMonitorPidPanel _panel;

    @Override
    public void setActivated(final boolean activated)
    {

    }


    @Override
    public void setPanel(final IBatteryMonitorPidPanel panel)
    {
        _panel = panel;
    }
}
