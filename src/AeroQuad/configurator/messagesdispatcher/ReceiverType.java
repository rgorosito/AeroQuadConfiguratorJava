package AeroQuad.configurator.messagesdispatcher;

public enum ReceiverType
{
    PPM,
    PWM,
    SBUS;

    public static ReceiverType fromOrdinal(final int intType)
    {
        switch(intType)
        {
            case 0:
                return PPM;
            case 1:
                return PWM;
            case 2:
                return SBUS;
        }
        return PWM;
    }
}
