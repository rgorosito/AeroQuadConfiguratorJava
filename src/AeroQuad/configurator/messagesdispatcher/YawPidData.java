package AeroQuad.configurator.messagesdispatcher;

public class YawPidData
{
    private PIDData _yawPid = new PIDData();
    private PIDData _headingHoldPid = new PIDData();

    public YawPidData()
    {

    }


    public YawPidData(final PIDData yawPid,
                      final PIDData headingHoldPidData)
    {
        _yawPid = yawPid;
        _headingHoldPid = headingHoldPidData;
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
            return false;
        }
        if (!_headingHoldPid.equals(other._headingHoldPid))
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
        return _headingHoldPid;
    }

    public YawPidData getCopy()
    {
        return new YawPidData(_yawPid.getCopy(), _headingHoldPid.getCopy());
    }

    public void setYawPid(final PIDData yawPid)
    {
        _yawPid = yawPid;
    }

    public void setHeadingHoldPid(final PIDData headingHoldPid)
    {
        _headingHoldPid = headingHoldPid;
    }
}
