package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.gpsstatepanel;


public enum GpsState
{
    GPS_DETECTING,
    GPS_NOFIX,
    GPS_FIX2D,
    GPS_FIX3D,
    GPS_FIX3DD;

    public static String fromInt(final int value)
    {
        if (value == GPS_NOFIX.ordinal())
        {
            return "No fix";
        }
        else if (value == GPS_FIX2D.ordinal())
        {
            return "2D fix";
        }
        else if (value == GPS_FIX3D.ordinal())
        {
            return "3D fix";
        }
        else if (value == GPS_FIX3DD.ordinal())
        {
            return "3DD fix";
        }
        return "Detecting";
    }
}
