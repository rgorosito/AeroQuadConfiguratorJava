package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IAccroPidPanelController extends IPidPanelController
{
    void setPanel(IAccroPidPanel panel);

    void userRollPidChanged(PIDData pid);

    void userPitchPidChanged(PIDData pid);

    void userStickScalingChanged(String text);
}
