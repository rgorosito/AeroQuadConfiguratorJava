package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messageDispatcher.*;

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

            _messageDispatcher.setMotorArmed(!splittedData[0].equals("0"));

            final float xAxisAngle = Float.parseFloat(splittedData[1]);
            final float yAxisAngle = Float.parseFloat(splittedData[2]);
            final float zAxisAngle = Float.parseFloat(splittedData[3]);
            _messageDispatcher.setVehicleAttitude(new VehicleAttitude(xAxisAngle, yAxisAngle, zAxisAngle));

            final float altitude = Float.parseFloat(splittedData[4]);
            _messageDispatcher.setCurrentAltitude(altitude);
            final boolean altitudeHoldState = splittedData[5].equals("0") ? false : true;
            _messageDispatcher.setAltitudeHoldState(altitudeHoldState);

            _messageDispatcher.setChannelValue(ReceiverChannel.ROLL, splittedData[6]);
            _messageDispatcher.setChannelValue(ReceiverChannel.PITCH, splittedData[7]);
            _messageDispatcher.setChannelValue(ReceiverChannel.YAW, splittedData[8]);
            _messageDispatcher.setChannelValue(ReceiverChannel.THROTTLE, splittedData[9]);
            _messageDispatcher.setChannelValue(ReceiverChannel.MODE, splittedData[10]);
            _messageDispatcher.setChannelValue(ReceiverChannel.AUX1, splittedData[11]);
            _messageDispatcher.setChannelValue(ReceiverChannel.AUX2, splittedData[12]);
            _messageDispatcher.setChannelValue(ReceiverChannel.AUX3, splittedData[13]);

            _messageDispatcher.setMotorCommandValue(MotorsIndex.MOTOR1, splittedData[14]);
            _messageDispatcher.setMotorCommandValue(MotorsIndex.MOTOR2, splittedData[15]);
            _messageDispatcher.setMotorCommandValue(MotorsIndex.MOTOR3, splittedData[16]);
            _messageDispatcher.setMotorCommandValue(MotorsIndex.MOTOR4, splittedData[17]);
            _messageDispatcher.setMotorCommandValue(MotorsIndex.MOTOR5, splittedData[18]);
            _messageDispatcher.setMotorCommandValue(MotorsIndex.MOTOR6, splittedData[19]);
            _messageDispatcher.setMotorCommandValue(MotorsIndex.MOTOR7, splittedData[20]);
            _messageDispatcher.setMotorCommandValue(MotorsIndex.MOTOR8, splittedData[21]);

            final float currentVoltage = Float.parseFloat(splittedData[22]);
            _messageDispatcher.setCurrentVoltage(currentVoltage);

            final FlightMode flightMode = splittedData[23].equals("1") ? FlightMode.Stable : FlightMode.Acrobatic;
            _messageDispatcher.setFlightMode(flightMode);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
