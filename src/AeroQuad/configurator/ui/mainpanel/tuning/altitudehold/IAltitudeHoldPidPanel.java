package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IAltitudeHoldPidPanel
{
    void setUserLevel(UserLevel userLevel);

    void setSinced(boolean synced);

    void setAltitudePid(PIDData pid);

    void setThrottleBump(String altitudeBump);

    void setThrottlePanic(String throttlePanic);

    void setMinThrottleAdjust(String minThrottleAdjust);

    void setMaxThrottleAdjust(String maxThrottleAdjust);

    void setSmoothFactor(String smoothFactor);
}
