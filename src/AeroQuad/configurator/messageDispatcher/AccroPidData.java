package AeroQuad.configurator.messagedispatcher;

public class AccroPidData
{
    private PIDData _rollPidData = new PIDData();
    private PIDData _pichPidData = new PIDData();
    private String _stickScaling = "";

    public AccroPidData()
    {

    }

    public AccroPidData(final PIDData rollPidData, final PIDData pichPidData, final String stickScaling)
    {
        _rollPidData = rollPidData;
        _pichPidData = pichPidData;
        _stickScaling = stickScaling;
    }



    public PIDData getRollPid()
    {
        return _rollPidData;
    }

    public PIDData getPitchPid()
    {
        return _pichPidData;
    }

    public String getStickScaling()
    {
        return _stickScaling;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof AccroPidData))
        {
            return false;
        }
        final AccroPidData other = (AccroPidData)obj;
        if (!_rollPidData.equals(other._rollPidData))
        {
            return true;
        }
        if (!_pichPidData.equals(other._pichPidData))
        {
            return false;
        }
        if (!_stickScaling.equals(other._stickScaling))
        {
            return false;
        }
        return true;
    }
}
