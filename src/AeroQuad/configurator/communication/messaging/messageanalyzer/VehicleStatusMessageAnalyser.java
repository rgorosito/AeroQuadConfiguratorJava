package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagesdispatcher.*;

public class VehicleStatusMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public VehicleStatusMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");

            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR_ARMED_STATE_CHANGED, !splittedData[0].equals("0"));

            final float xAxisAngle = Float.parseFloat(splittedData[1]);
            final float yAxisAngle = Float.parseFloat(splittedData[2]);
            final float zAxisAngle = Float.parseFloat(splittedData[3]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.VEHICLE_ATTITUDE_STATE_CHANGE, new VehicleAttitude(xAxisAngle, yAxisAngle, zAxisAngle));

            final float altitude = Float.parseFloat(splittedData[4]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.VEHICLE_ALTITUDE_STATE_CHANGE, altitude);
            final float zVelocity = Float.parseFloat(splittedData[5]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.VEHICLE_ZVELOCITY_STATE_CHANGE, zVelocity);
            final boolean altitudeHoldState = splittedData[6].equals("0") ? false : true;
            _messageDispatcher.dispatchMessage(IMessageDispatcher.VEHICLE_ALTITUDE_HOLD_STATE_CHANGE, altitudeHoldState);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_ROLL_STATE_CHANGE, splittedData[7]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_PITCH_STATE_CHANGE, splittedData[8]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_YAW_STATE_CHANGE, splittedData[9]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_THROTTLE_STATE_CHANGE, splittedData[10]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_MODE_STATE_CHANGE, splittedData[11]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_AUX1_STATE_CHANGE, splittedData[12]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_AUX2_STATE_CHANGE, splittedData[13]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_AUX3_STATE_CHANGE, splittedData[14]);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR1_THROTTLE_STATE_CHANGE, splittedData[15]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR2_THROTTLE_STATE_CHANGE, splittedData[16]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR3_THROTTLE_STATE_CHANGE, splittedData[17]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR4_THROTTLE_STATE_CHANGE, splittedData[18]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR5_THROTTLE_STATE_CHANGE, splittedData[19]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR6_THROTTLE_STATE_CHANGE, splittedData[20]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR7_THROTTLE_STATE_CHANGE, splittedData[21]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MOTOR8_THROTTLE_STATE_CHANGE, splittedData[22]);

            final float currentVoltage = Float.parseFloat(splittedData[23]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.VEHICLE_VOLTAGE_STATE_CHANGE, currentVoltage);

            final FlightMode flightMode = splittedData[24].equals("1") ? FlightMode.Stable : FlightMode.Acrobatic;
            _messageDispatcher.dispatchMessage(IMessageDispatcher.VEHICLE_FLIGHT_MODE_STATE_CHANGE, flightMode);

            final GpsDatas gpsDatas = new GpsDatas(splittedData[25], splittedData[26], splittedData[27], splittedData[28], splittedData[29], splittedData[30], splittedData[31]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.GPS_INFOS_UPDATED, gpsDatas);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
