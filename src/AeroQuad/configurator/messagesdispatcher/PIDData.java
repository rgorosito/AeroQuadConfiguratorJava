package AeroQuad.configurator.messagesdispatcher;

public class PIDData
{
    private final String _p;
    private final String _i;
    private final String _d;

    public PIDData()
    {
        _p = "0";
        _i = "0";
        _d = "0";
    }

    public PIDData(final String p, final String i, final String d)
    {
        _p = p;
        _i = i;
        _d = d;
    }


    public String getP()
    {
        try
        {
            final String ret = Integer.toString(Integer.valueOf(_p));
            return ret;
        }
        catch (NumberFormatException e)
        {
        }
        return Float.toString(Float.valueOf(_p));
    }

    public String getI()
    {
        try
        {
            final String ret = Integer.toString(Integer.valueOf(_i));
            return ret;
        }
        catch (NumberFormatException e)
        {
        }
        return Float.toString(Float.valueOf(_i));
    }

    public String getD()
    {
        try
        {
            final String ret = Integer.toString(Integer.valueOf(_d));
            return ret;
        }
        catch (NumberFormatException e)
        {
        }
        return Float.toString(Float.valueOf(_d));
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof PIDData))
        {
            return false;
        }
        final PIDData other = (PIDData)obj;
        if (!areStringToDoubleEquals(other._p, _p))
        {
            return false;
        }
        if (!areStringToDoubleEquals(other._i, _i))
        {
            return false;
        }
        return areStringToDoubleEquals(other._d,_d);
    }

    private boolean areStringToDoubleEquals(final String number1String, final String number2String)
    {
        try
        {
            final double number1 = Double.valueOf(number1String);
            final double number2 = Double.valueOf(number2String);
            if (number1 == number2)
            {
                return true;
            }
        }
        catch (final Exception e)
        {
        }
        return false;
    }

    public PIDData getCopy()
    {
        return new PIDData(_p, _i, _d);
    }

    @Override
    public String toString()
    {
        return _p + "," + _i + "," + _d;
    }
}
