package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

public interface IBatteryMonitorPidPanel
{
    void setSinced(boolean sinced);

    void setAlarmVoltage(String alarmVoltage);

    void setThrottleTarget(String throttleTarget);

    void setGoingDownTime(String goingDownTime);
}
