package AeroQuad.configurator.messagesdispatcher;

public enum FlightConfigType
{
    QUAD_X,
    QUAD_PLUS,
    HEX_PLUS,
    HEX_X,
    TRI,
    QUAD_Y4,
    HEX_Y6,
    OCTO_X8,
    OCTO_PLUS,
    OCTO_X;


    public static FlightConfigType fromOrdinal(final int config)
    {
        switch (config)
        {
            case 0:
                return QUAD_X;
            case 1:
                return QUAD_PLUS;
            case 2:
                return HEX_PLUS;
            case 3:
                return HEX_X;
            case 4:
                return TRI;
            case 5:
                return QUAD_Y4;
            case 6:
                return HEX_Y6;
            case 7:
                return OCTO_X8;
            case 8:
                return OCTO_PLUS;
            case 9:
                return OCTO_X;
        }
        return QUAD_X;
    }
}
