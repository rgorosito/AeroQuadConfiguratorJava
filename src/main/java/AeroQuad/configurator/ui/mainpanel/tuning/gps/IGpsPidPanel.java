package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IGpsPidPanel
{
    void setUserLevel(UserLevel userLevel);

    void setSinced(boolean sinced);

    void setRollPid(PIDData rollPid);

    void setPitchPid(PIDData pitchPid);

    void setYawPid(PIDData yawPid);
}
