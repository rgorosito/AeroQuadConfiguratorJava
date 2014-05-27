package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public interface IAltitudeHoldPidPanel
{
    void setUserLevel(UserLevel userLevel);

    void setSinced(boolean synced);

    void setAltitudePid(PIDData pid);

    void setThrottleBump(String altitudeBump);

    void setThrottlePanic(String throttlePanic);

    void setSmoothFactor(String smoothFactor);

    void setZDampening(PIDData zDampeningPid);
}
