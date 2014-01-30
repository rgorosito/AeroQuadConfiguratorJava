package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.ui.IConfiguratorController;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IAltitudeHoldPidPanelController extends IConfiguratorController
{
    void setUserLevel(UserLevel userLevel);

    void setPanel(IAltitudeHoldPidPanel panel);
}
