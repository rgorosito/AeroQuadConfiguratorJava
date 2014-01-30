package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IYawPidPanelController extends IPidPanelController
{
    void setPanel(IYawPidPanel panel);
}
