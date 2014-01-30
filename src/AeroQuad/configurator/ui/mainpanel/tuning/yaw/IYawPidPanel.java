package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IYawPidPanel
{
    void setUserLevel(UserLevel userLevel);

    void setYawPid(PIDData pid);

    void setHeadingHoldPid(PIDData pid);

    void setSinced(boolean sinced);
}
