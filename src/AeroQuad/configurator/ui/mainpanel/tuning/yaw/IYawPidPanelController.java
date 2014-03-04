package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IYawPidPanelController extends IPidPanelController
{
    final static String DEFAULT_YAW_P = "pid.yaw.default.p";
    final static String DEFAULT_YAW_I = "pid.yaw.default.i";
    final static String DEFAULT_YAW_D = "pid.yaw.default.d";

    final static String DEFAULT_HEADING_HOLD_P = "pid.headingHold.default.p";
    final static String DEFAULT_HEADING_HOLD_I = "pid.headingHold.default.i";
    final static String DEFAULT_HEADING_HOLD_D = "pid.headingHold.default.d";


    void setPanel(IYawPidPanel panel);

    void userYawPidChanged(PIDData pid);

    void headingHoldPidChanged(PIDData pid);
}
