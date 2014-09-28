package AeroQuad.configurator.ui.mainpanel.monitoring;

import javax.swing.JPanel;

public interface IMonitoringPanelController
{
    void setPanel(IMonitoringPanel monitoringPanel);

    JPanel getVehicleStatusPanel();

    JPanel getSensorsMonitoringPanel();

    JPanel getMotorCommandPanel();

    void sensorsMonitoringButtonPressed();

    void motorsMonitoringButtonPressed();

    void vehicleMonitoringButtonPressed();

    void setActivated(boolean activated);
}
