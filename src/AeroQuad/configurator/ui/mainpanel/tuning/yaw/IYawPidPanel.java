package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IYawPidPanel
{
    void setUserLevel(UserLevel userLevel);

    void setYawPid(PIDData pid);

    void setYawingSpeedFactor(String yawingSpeedFactor);

    void setSinced(boolean sinced);
}
