package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IBatteryMonitorPidPanelController extends IPidPanelController
{
    final static String DEFAULT_ALARM_VOLTAGE_PROPERTY_KEY = "pid.batteryMonitor.alarmvoltage";
    final static String DEFAULT_THROTTLE_TARGET_PROPERTY_KEY = "pid.batteryMonitor.throttletarget";
    final static String DEFAULT_GOIN_DOWN_TIME_PROPERTY_KEY = "pid.batteryMonitor.goindowntime";

    void setPanel(IBatteryMonitorPidPanel panel);

    void userAlarmVoltageChanged(String value);

    void userThrottleTargetChanged(String value);

    void userGoingDownTimeChanged(String value);
}
