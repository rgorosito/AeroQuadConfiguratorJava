package AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection;


public enum ReceiverChannel
{
    THROTTLE,
    ROLL,
    PITCH,
    YAW,
    MODE,
    AUX1,
    AUX2,
    AUX3,
    UNDEFINED;

    public static ReceiverChannel getNext(final ReceiverChannel channel)
    {
        switch (channel)
        {
            case THROTTLE:
                return ROLL;
            case ROLL:
                return PITCH;
            case PITCH:
                return YAW;
            case YAW:
                return MODE;
            case MODE:
                return AUX1;
            case AUX1:
                return AUX2;
            case AUX2:
                return AUX3;
            case AUX3:
            default:
                return UNDEFINED;
        }
    }
}
