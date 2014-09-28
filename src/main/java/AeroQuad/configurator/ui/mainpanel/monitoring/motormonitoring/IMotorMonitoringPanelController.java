package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;


public interface IMotorMonitoringPanelController
{
    void setActivated(boolean activated);

    void showCraftConfugurationPage();

    void userMotorValueChanged(int motor, int value);

    void sendUserMotorCommand();

    void stopAllMotor();

    void overrideManualSendCommand(boolean selected);

    void setPanel(IMotorMonitoringPanel panel);
}
