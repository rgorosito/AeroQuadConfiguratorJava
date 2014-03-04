package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IAccroPidPanel
{
    void setUserLevel(UserLevel userLevel);

    void setRollPid(PIDData pid);

    void setPitchPid(PIDData pid);

    void setStickScaling(String stickScalling);

    void setSinced(boolean synced);
}
