package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IBatteryMonitorPidPanelController extends IPidPanelController
{
    void setPanel(IBatteryMonitorPidPanel panel);
}
