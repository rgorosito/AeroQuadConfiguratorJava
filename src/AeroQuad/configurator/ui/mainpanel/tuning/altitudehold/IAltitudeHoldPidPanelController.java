package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IAltitudeHoldPidPanelController extends IPidPanelController
{
    final static String DEFAULT_ALTITUDE_HOLD_P = "pid.altitudehold.pid.p";
    final static String DEFAULT_ALTITUDE_HOLD_I = "pid.altitudehold.pid.i";
    final static String DEFAULT_ALTITUDE_HOLD_D = "pid.altitudehold.pid.d";
    final static String DEFAULT_ALTITUDE_BUMP = "pid.altitudehold.bump";
    final static String DEFAULT_ALTITUDE_PANIC = "pid.altitudehold.panic";
    final static String DEFAULT_ALTITUDE_MIN_ADJUST = "pid.altitudehold.minadjust";
    final static String DEFAULT_ALTITUDE_MAX_ADJUST = "pid.altitudehold.maxadjust";
    final static String DEFAULT_ALTITUDE_SMOOTH_FACTOR = "pid.altitudehold.smoothfactor";
    final static String DEFAULT_ALTITUDE_ZDAMPENING_P = "pid.zdampening.pid.p";
    final static String DEFAULT_ALTITUDE_ZDAMPENING_I = "pid.zdampening.pid.i";
    final static String DEFAULT_ALTITUDE_ZDAMPENING_D = "pid.zdampening.pid.d";

    void setPanel(IAltitudeHoldPidPanel panel);

    void userAltitudeHoldPidChanged(PIDData pid);

    void userThrottleBumpValueChanged(String text);

    void userThrottlePanicValueChanged(String text);

    void userMinThrottleAdjustValueChanged(String text);

    void userMaxThrottleAdjustValueChanged(String text);

    void userSmoothFactorValueChanged(String text);

    void userZDampeningPidChanged(PIDData pid);
}
