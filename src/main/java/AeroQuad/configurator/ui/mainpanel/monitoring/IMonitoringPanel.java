package AeroQuad.configurator.ui.mainpanel.monitoring;

public interface IMonitoringPanel
{
    final String SENSORS = "SENSORS";
    final String MOTORS = "MOTORS";
    final String VEHICLE = "VEHICLE";

    void setConnectedState(boolean connected);

    void showPanel(String panelId);

    void performVehicleMonitoringSelection();
}
