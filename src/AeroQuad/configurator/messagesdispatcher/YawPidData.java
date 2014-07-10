package AeroQuad.configurator.messagesdispatcher;

public class YawPidData
{
    private PIDData _yawPid = new PIDData();
    private String _yawingSpeedFactor = "";

    public YawPidData()
    {
    }


    public YawPidData(final PIDData yawPid,
                      final String yawingSpeedFactor)
    {
        _yawPid = yawPid;
        _yawingSpeedFactor = yawingSpeedFactor;
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
        final double yawingSpeed1 = Double.parseDouble(_yawingSpeedFactor);
        final double yawingSpeed2 = Double.parseDouble(other._yawingSpeedFactor);
        if (yawingSpeed1 != yawingSpeed2)
        {
            return false;
        }
        return true;
    }

    public PIDData getYawPid()
    {
        return _yawPid;
    }

    public String getYawingSpeedFactor()
    {
        return _yawingSpeedFactor;
    }

    public YawPidData getCopy()
    {
        return new YawPidData(_yawPid.getCopy(), _yawingSpeedFactor);
    }

    public void setYawPid(final PIDData yawPid)
    {
        _yawPid = yawPid;
    }

    public void setYawingSpeedFactor(final String yawingSpeedFactor)
    {
        _yawingSpeedFactor = yawingSpeedFactor;
    }
}
