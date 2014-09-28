package AeroQuad.configurator.messagesdispatcher;


public class MagRawData
{
    private final String _x;
    private final String _y;
    private final String _z;

    public MagRawData(final String x, final String y, final String z)
    {
        _x = x;
        _y = y;
        _z = z;
    }

    public String getX()
    {
        return _x;
    }

    public String getY()
    {
        return _y;
    }

    public String getZ()
    {
        return _z;
    }
}
