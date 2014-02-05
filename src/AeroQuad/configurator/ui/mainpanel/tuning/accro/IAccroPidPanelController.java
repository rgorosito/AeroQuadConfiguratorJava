package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IAccroPidPanelController extends IPidPanelController
{
    final static String DEFAULT_PID_ROLL_P = "pid.accro.default.roll.p";
    final static String DEFAULT_PID_ROLL_I = "pid.accro.default.roll.i";
    final static String DEFAULT_PID_ROLL_D = "pid.accro.default.roll.d";
    final static String DEFAULT_PID_PITCH_P = "pid.accro.default.pitch.p";
    final static String DEFAULT_PID_PITCH_I = "pid.accro.default.pitch.i";
    final static String DEFAULT_PID_PITCH_D = "pid.accro.default.pitch.d";
    final static String DEFAULT_PID_STICK_SCALING = "pid.accro.default.stickScaling";

    void setPanel(IAccroPidPanel panel);

    void userRollPidChanged(PIDData pid);

    void userPitchPidChanged(PIDData pid);

    void userStickScalingChanged(String text);
}
