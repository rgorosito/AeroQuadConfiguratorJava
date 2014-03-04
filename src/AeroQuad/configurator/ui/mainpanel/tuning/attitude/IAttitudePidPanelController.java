package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IAttitudePidPanelController extends IPidPanelController
{
    final static String DEFAULT_PID_ACCEL_P = "pid.attitude.accel.default.p";
    final static String DEFAULT_PID_ACCEL_I = "pid.attitude.accel.default.i";
    final static String DEFAULT_PID_ACCEL_D = "pid.attitude.accel.default.d";
    final static String DEFAULT_PID_GYRO_P = "pid.attitude.gyro.default.p";
    final static String DEFAULT_PID_GYRO_I = "pid.attitude.gyro.default.i";
    final static String DEFAULT_PID_GYRO_D = "pid.attitude.gyro.default.d";

    void setPanel(IAttitudePidPanel panel);

    void userGyroRollPidChanged(PIDData pid);

    void userGyroPitchPidChanged(PIDData pid);

    void userAccelRollPidChanged(PIDData pid);

    void userAccelPitchPidChanged(PIDData pid);
}
