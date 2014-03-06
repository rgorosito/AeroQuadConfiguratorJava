package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

public interface IMotorMonitoringPanel
{
    void setMotorValue(int motor, Integer value);

    void setSendEnabled(boolean enabled);

    void setNbMotors(int nbMotors);
}
