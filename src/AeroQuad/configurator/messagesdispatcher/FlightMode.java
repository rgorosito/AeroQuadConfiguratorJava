package AeroQuad.configurator.messagesdispatcher;


public enum FlightMode
{
    Acrobatic,
    Horizon,
    Stable;

    public static FlightMode fromOrdinal(final int ordinal)
    {
        switch (ordinal)
        {
            case 0: return Acrobatic;
            case 1: return Horizon;
            case 2: return Stable;
        }
        return Acrobatic;
    }

}
