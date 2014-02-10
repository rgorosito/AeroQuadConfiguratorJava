package AeroQuad.configurator.messagedispatcher;


public class AltitudeHoldPidData
{
    private PIDData _altitudeHoldPid = new PIDData();
    private String _throttleBump = "";
    private String _throttlePanic = "";
    private String _minThrottleAdjust = "";
    private String _maxThrottleAdjust = "";
    private String _smoothFactor = "";
    private PIDData _zDampeningPid = new PIDData();

    public AltitudeHoldPidData()
    {

    }

    public AltitudeHoldPidData(final PIDData altitudeHoldPid,
                               final String throttleBump,
                               final String throttlePanic,
                               final String minThrottleAdjust,
                               final String maxThrottleAdjust,
                               final String smoothFactor,
                               final PIDData zDampeningPid)
    {
        _altitudeHoldPid = altitudeHoldPid;
        _throttleBump = throttleBump;
        _throttlePanic = throttlePanic;
        _minThrottleAdjust = minThrottleAdjust;
        _maxThrottleAdjust = maxThrottleAdjust;
        _smoothFactor = smoothFactor;
        _zDampeningPid = zDampeningPid;
    }

    public PIDData getAltitudePid()
    {
        return _altitudeHoldPid;
    }

    public String getThrottleBump()
    {
        return _throttleBump;
    }

    public String getThrottlePanic()
    {
        return _throttlePanic;
    }

    public String getMinThrottleAdjust()
    {
        return _minThrottleAdjust;
    }

    public String getMaxThrottleAdjust()
    {
        return _maxThrottleAdjust;
    }

    public String getSmoothFactor()
    {
        return _smoothFactor;
    }

    public PIDData getZDampeningPid()
    {
        return _zDampeningPid;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof AltitudeHoldPidData))
        {
            return false;
        }
        final AltitudeHoldPidData other = (AltitudeHoldPidData)obj;
        if (!_altitudeHoldPid.equals(other._altitudeHoldPid))
        {
            return false;
        }
        if (!_throttleBump.equals(other._throttleBump))
        {
            return false;
        }
        if (!_throttlePanic.equals(other._throttlePanic))
        {
            return false;
        }
        if (!_minThrottleAdjust.equals(other._minThrottleAdjust))
        {
            return false;
        }
        if (!_maxThrottleAdjust.equals(other._maxThrottleAdjust))
        {
            return false;
        }
        if (!_smoothFactor.equals(other._smoothFactor))
        {
            return false;
        }
        if (!_zDampeningPid.equals(other._zDampeningPid))
        {
            return false;
        }

        return true;
    }

    public void setAltitudeHoldPid(final PIDData altitudeHoldPid)
    {
        _altitudeHoldPid = altitudeHoldPid;
    }

    public void setThrottleBump(final String throttleBump)
    {
        _throttleBump = throttleBump;
    }

    public void setThrottlePanic(final String throttlePanic)
    {
        _throttlePanic = throttlePanic;
    }

    public void setMinThrottleAdjust(final String minThrottleAdjust)
    {
        _minThrottleAdjust = minThrottleAdjust;
    }

    public void setMaxThrottleAsjust(final String maxThrottleAsjust)
    {
        _maxThrottleAdjust = maxThrottleAsjust;
    }

    public void setSmoothFactor(final String smoothFactor)
    {
        _smoothFactor = smoothFactor;
    }

    public void setZDampeningPid(final PIDData zDampeningPid)
    {
        _zDampeningPid = zDampeningPid;
    }

    public AltitudeHoldPidData getCopy()
    {
        return new AltitudeHoldPidData(_altitudeHoldPid.getCopy(),
                                       _throttleBump,
                                       _throttlePanic,
                                       _minThrottleAdjust,
                                       _maxThrottleAdjust,
                                       _smoothFactor,
                                       _zDampeningPid.getCopy());
    }
}
