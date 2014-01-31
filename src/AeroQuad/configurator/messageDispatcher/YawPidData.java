package AeroQuad.configurator.messagedispatcher;

public class YawPidData
{
    private PIDData _yawPid = new PIDData();
    private PIDData _headingHoldPidData = new PIDData();

    public YawPidData()
    {

    }


    public YawPidData(final PIDData yawPid,
                      final PIDData headingHoldPidData)
    {
        _yawPid = yawPid;
        _headingHoldPidData = headingHoldPidData;
    }


    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof YawPidData))
        {
            return false;
        }
        final YawPidData other = (YawPidData)obj;
        if (!_yawPid.equals(other._yawPid))
        {
            return true;
        }
        if (!_headingHoldPidData.equals(other._headingHoldPidData))
        {
            return false;
        }
        return true;
    }

    public PIDData getYawPid()
    {
        return _yawPid;
    }

    public PIDData getHeadingHoldPid()
    {
        return _headingHoldPidData;
    }
}
