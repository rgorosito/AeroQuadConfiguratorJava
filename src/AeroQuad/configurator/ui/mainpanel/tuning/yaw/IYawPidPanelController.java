package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.ui.IConfiguratorController;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IYawPidPanelController extends IConfiguratorController
{
    void setUserLevel(UserLevel userLevel);

    void setPanel(IYawPidPanel panel);
}
