package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import AeroQuad.configurator.ui.ConfiguratorPanel;
import AeroQuad.configurator.ui.IConfiguratorController;

public class BatteryMonitorPidPanel extends ConfiguratorPanel implements IBatteryMonitorPidPanel
{
    private final IBatteryMonitorPidPanelController _controller;

    public BatteryMonitorPidPanel(final IBatteryMonitorPidPanelController batteryMonitorPidPanelController)
    {
        _controller = batteryMonitorPidPanelController;
    }

    @Override
    public IConfiguratorController getController()
    {
        return _controller;
    }
}
