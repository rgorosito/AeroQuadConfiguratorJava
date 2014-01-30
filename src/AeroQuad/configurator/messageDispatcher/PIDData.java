package AeroQuad.configurator.messagedispatcher;

public class PIDData
{
    private final String _p;
    private final String _i;
    private final String _d;

    public PIDData()
    {
        _p = "";
        _i = "";
        _d = "";
    }

    public PIDData(final String p, final String i, final String d)
    {
        _p = p;
        _i = i;
        _d = d;
    }


    public String getP()
    {
        return _p;
    }

    public String getI()
    {
        return _i;
    }

    public String getD()
    {
        return _d;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof PIDData))
        {
            return false;
        }
        final PIDData other = (PIDData)obj;
        if (!other._p.equals(_p))
        {
            return false;
        }
        if (!other._i.equals(_i))
        {
            return false;
        }
        if (!other._d.equals(_d))
        {
            return false;
        }
        return true;
    }
}
