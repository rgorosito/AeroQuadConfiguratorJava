package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import javax.swing.JPanel;

public class BatteryMonitorPidPanel extends JPanel implements IBatteryMonitorPidPanel
{
    private final IBatteryMonitorPidPanelController _controller;

    public BatteryMonitorPidPanel(final IBatteryMonitorPidPanelController batteryMonitorPidPanelController)
    {
        _controller = batteryMonitorPidPanelController;
    }
}
