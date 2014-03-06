package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IGpsPidPanelController extends IPidPanelController
{
    final static String DEFAULT_ROLL_PID_P = "pid.gps.roll.default.p";
    final static String DEFAULT_ROLL_PID_I = "pid.gps.roll.default.i";
    final static String DEFAULT_ROLL_PID_D = "pid.gps.roll.default.d";
    final static String DEFAULT_PITCH_PID_P = "pid.gps.pitch.default.p";
    final static String DEFAULT_PITCH_PID_I = "pid.gps.pitch.default.i";
    final static String DEFAULT_PITCH_PID_D = "pid.gps.pitch.default.d";
    final static String DEFAULT_YAW_PID_P = "pid.gps.yaw.default.p";
    final static String DEFAULT_YAW_PID_I = "pid.gps.yaw.default.i";
    final static String DEFAULT_YAW_PID_D = "pid.gps.yaw.default.d";

    void setPanel(IGpsPidPanel panel);

    void userRollPidChanged(PIDData pid);

    void userPitchPidChanged(PIDData pid);

    void userYawPidChanged(PIDData pid);
}
