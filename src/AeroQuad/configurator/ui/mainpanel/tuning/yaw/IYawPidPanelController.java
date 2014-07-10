package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IYawPidPanelController extends IPidPanelController
{
    final static String DEFAULT_YAW_P = "pid.yaw.default.p";
    final static String DEFAULT_YAW_I = "pid.yaw.default.i";
    final static String DEFAULT_YAW_D = "pid.yaw.default.d";
    final static String YAWING_SPEED_FACTOR = "pid.yaw.yawingSpeedFactor";

    void setPanel(IYawPidPanel panel);

    void userYawPidChanged(PIDData pid);

    void yawingSpeedChanged(String text);
}
