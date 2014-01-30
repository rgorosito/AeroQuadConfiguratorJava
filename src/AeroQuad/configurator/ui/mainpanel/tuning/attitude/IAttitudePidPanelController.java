package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.ui.IConfiguratorController;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IAttitudePidPanelController extends IConfiguratorController
{
    void setUserLevel(UserLevel userLevel);

    void setPanel(IAttitudePidPanel panel);
}
