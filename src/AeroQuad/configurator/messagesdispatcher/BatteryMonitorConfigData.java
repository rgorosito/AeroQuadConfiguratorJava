package AeroQuad.configurator.messagesdispatcher;

public class BatteryMonitorConfigData
{

    private String _alarmVoltage;
    private String _throttelTarget;
    private String _goingDownTime;

    public BatteryMonitorConfigData(final String alarmVoltage, final String throttelTarget, final String goingDownTime)
    {
        _alarmVoltage = alarmVoltage;
        _throttelTarget = throttelTarget;
        _goingDownTime = goingDownTime;
    }

    public BatteryMonitorConfigData()
    {
        this("0", "0", "0");
    }

    public BatteryMonitorConfigData getCopy()
    {
        return new BatteryMonitorConfigData(_alarmVoltage, _throttelTarget, _goingDownTime);
    }

    public String getAlarmVoltage()
    {
        return _alarmVoltage;
    }

    public String getThrottleTarget()
    {
        return _throttelTarget;
    }

    public String getGoingDownTime()
    {
        return _goingDownTime;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof BatteryMonitorConfigData))
        {
            return false;
        }
        final BatteryMonitorConfigData other = (BatteryMonitorConfigData)obj;
        if (!_alarmVoltage.equals(other._alarmVoltage))
        {
            return false;
        }
        if (!_throttelTarget.equals(other._throttelTarget))
        {
            return false;
        }
        if (!_goingDownTime.equals(other._goingDownTime))
        {
            return false;
        }
        return true;
    }

    public void setAlarmVoltage(final String alarmVoltage)
    {
        _alarmVoltage = alarmVoltage;
    }

    public void setThrottleTarget(final String throttleTarget)
    {
        _throttelTarget = throttleTarget;
    }

    public void setGoingDownTime(final String goingDownTime)
    {
        _goingDownTime = goingDownTime;
    }
}
