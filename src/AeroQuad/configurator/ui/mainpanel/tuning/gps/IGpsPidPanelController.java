package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.ui.IConfiguratorController;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IGpsPidPanelController extends IConfiguratorController
{
    void setUserLevel(UserLevel userLevel);

    void setPanel(IGpsPidPanel panel);
}
