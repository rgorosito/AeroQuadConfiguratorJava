package AeroQuad.configurator.communication.messaging;

public class IMessageDefinition
{
    public static String VEHICLE_STATE_REQUEST_MESSAGE = "#";
    public static String REQUEST_STOP_SENDING = "X";

    public static String ACCRO_PID_SET_COMMAND = "A ";
    public static String ATTITUDE_PID_SET_COMMAND = "B ";
    public static String YAW_PID_SET_COMMAND = "C ";
    public static String ALTITUDE_HOLD_PID_SET_COMMAND = "D ";
    public static String OVERRIDE_MOTOR_COMMAND = "5 ";
    public static String BATTERY_CONFIG_SET_COMMAND = "N";
    public static String GPS_PID_SET_COMMAND = "V";

    public static String RESET_RECEIVER_CALIBRATION = "H";
}
