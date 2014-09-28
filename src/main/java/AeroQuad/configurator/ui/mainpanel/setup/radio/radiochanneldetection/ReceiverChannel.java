package AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection;


public enum ReceiverChannel
{
    ROLL,
    PITCH,
    YAW,
    THROTTLE,
    MODE,
    AUX1,
    AUX2,
    AUX3,
    UNDEFINED;

    public static ReceiverChannel getNext(final ReceiverChannel channel)
    {
        switch (channel)
        {
            case ROLL:
                return PITCH;
            case PITCH:
                return YAW;
            case YAW:
                return THROTTLE;
            case THROTTLE:
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
