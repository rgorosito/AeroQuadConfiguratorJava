package AeroQuad.configurator.communication.messaging.messageanalyzer;


import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class SensorsValuesMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public SensorsValuesMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {

        try
        {

            final String splittedData[] = rawData.split(",");

            final String gyroX = splittedData[0];
            final String gyroY = splittedData[1];
            final String gyroZ = splittedData[2];

            final String accelX = splittedData[3];
            final String accelY = splittedData[4];
            final String accelZ = splittedData[5];

            final String magX = splittedData[6];
            final String magY = splittedData[7];
            final String magZ = splittedData[8];

            final String baroAltitude = splittedData[9];

            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_GYRO_X_VALUE_CHANGE, gyroX);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_GYRO_Y_VALUE_CHANGE, gyroY);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_GYRO_Z_VALUE_CHANGE, gyroZ);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_ACCEL_X_VALUE_CHANGE, accelX);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_ACCEL_Y_VALUE_CHANGE, accelY);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_ACCEL_Z_VALUE_CHANGE, accelZ);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_MAG_X_VALUE_CHANGE, magX);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_MAG_Y_VALUE_CHANGE, magY);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_MAG_Z_VALUE_CHANGE, magZ);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_BARO_ALTITUDE_VALUE_CHANGE, baroAltitude);

        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
