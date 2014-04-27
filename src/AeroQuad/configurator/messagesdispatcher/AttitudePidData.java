package AeroQuad.configurator.messagesdispatcher;

public class AttitudePidData
{
    private PIDData _accelRollPid = new PIDData();
    private PIDData _accelPitchPid = new PIDData();

    public AttitudePidData()
    {

    }

    public AttitudePidData(final PIDData accelRollPid,
                           final PIDData accelPitchPid)
    {
        _accelRollPid = accelRollPid;
        _accelPitchPid = accelPitchPid;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof AttitudePidData))
        {
            return false;
        }
        final AttitudePidData other = (AttitudePidData)obj;
        if (!_accelRollPid.equals(other._accelRollPid))
        {
            return false;
        }
        if (!_accelPitchPid.equals(other._accelPitchPid))
        {
            return false;
        }
        return true;
    }

    public PIDData getAccelPitchPid()
    {
        return _accelPitchPid;
    }

    public PIDData getAccelRollPid()
    {
        return _accelRollPid;
    }

    public void setAccelRollPid(final PIDData accelRollPid)
    {
        _accelRollPid = accelRollPid;
    }

    public void setAccelPitchPid(final PIDData accelPitchPid)
    {
        _accelPitchPid = accelPitchPid;
    }

    public AttitudePidData getCopy()
    {
        return new AttitudePidData(_accelRollPid.getCopy(),
                                   _accelPitchPid.getCopy());
    }
}
