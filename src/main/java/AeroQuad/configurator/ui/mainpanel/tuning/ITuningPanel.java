package AeroQuad.configurator.ui.mainpanel.tuning;

public interface ITuningPanel
{
    final String ACCRO = "ACCRO";
    final String ATTITUDE = "ATTITUDE";
    final String YAW = "YAW";
    final String ALTITUDE = "ALTITUDE HOLD";
    final String BATTERY = "BATTERY";
    final String GPS = "GPS";

    void setGpsPanelVisible(boolean visible);

    void setBatteryMonitorVisible(boolean visible);

    void setAltitudeHoldVisible(boolean visible);
}
