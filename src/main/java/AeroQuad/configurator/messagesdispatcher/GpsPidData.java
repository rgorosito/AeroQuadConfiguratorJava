package AeroQuad.configurator.messagesdispatcher;


public class GpsPidData
{
    private PIDData _rollPidData = new PIDData();
    private PIDData _pichPidData = new PIDData();
    private PIDData _yawPidData = new PIDData();

    public GpsPidData(final PIDData rollPid, final PIDData pitchPid, final PIDData yawPid)
    {
        _rollPidData = rollPid;
        _pichPidData = pitchPid;
        _yawPidData = yawPid;
    }

    public GpsPidData()
    {
    }

    public PIDData getRollPid()
    {
        return _rollPidData;
    }

    public PIDData getPitchPid()
    {
        return _pichPidData;
    }

    public PIDData getYawPid()
    {
        return _yawPidData;
    }

    public void setRollPid(final PIDData rollPid)
    {
        _rollPidData = rollPid;
    }

    public void setPitchPid(final PIDData pitchPid)
    {
        _pichPidData = pitchPid;
    }

    public void setYawPid(final PIDData yawPid)
    {
        _yawPidData = yawPid;
    }

    public GpsPidData getCopy()
    {
        return new GpsPidData(_rollPidData.getCopy(), _pichPidData.getCopy(), _yawPidData.getCopy());
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof GpsPidData))
        {
            return false;
        }
        final GpsPidData other = (GpsPidData)obj;
        if (!_rollPidData.equals(other._rollPidData))
        {
            return false;
        }
        if (!_pichPidData.equals(other._pichPidData))
        {
            return false;
        }
        if (!_yawPidData.equals(other._yawPidData))
        {
            return false;
        }
        return true;
    }
}
