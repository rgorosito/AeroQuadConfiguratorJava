package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IAccroPidPanelController extends IPidPanelController
{
    final static String DEFAULT_PID_P = "pid.accro.default.p";
    final static String DEFAULT_PID_I = "pid.accro.default.i";
    final static String DEFAULT_PID_D = "pid.accro.default.d";
    final static String DEFAULT_PID_STICK_SCALING = "pid.accro.default.stickScaling";
    final static String DEFAULT_THROTTLE_PID_ADJUSTMENT = "pid.accro.default.throttlePIDadjustment";

    void setPanel(IAccroPidPanel panel);

    void userRollPidChanged(PIDData pid);

    void userPitchPidChanged(PIDData pid);

    void userStickScalingChanged(String text);

    void userThrottlePIDAdjustmentChanged(String text);
}
