package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayController;

public interface IMotorMonitoringPanelController extends IMotorDisplayController
{
    void setActivated(boolean activated);

    void showCraftConfugurationPage();

    void userMotorValueChanged(int motor, int value);

    void sendUserMotorCommand();

    void stopAllMotor();

    void overrideManualSendCommand(boolean selected);
}
