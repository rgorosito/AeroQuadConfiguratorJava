package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.ui.IConfiguratorController;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IAccroPidPanelController extends IConfiguratorController
{
    void setUserLevel(UserLevel userLevel);

    void setPanel(IAccroPidPanel panel);
}
