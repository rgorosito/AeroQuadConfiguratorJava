package AeroQuad.configurator.communication.messaging.messageanalyzer;


import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;

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

            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_GYRO_X_VALUE_CHANGE, splittedData[0]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_GYRO_Y_VALUE_CHANGE, splittedData[1]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_GYRO_Z_VALUE_CHANGE, splittedData[2]);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_ACCEL_X_VALUE_CHANGE, splittedData[3]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_ACCEL_Y_VALUE_CHANGE, splittedData[4]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_ACCEL_Z_VALUE_CHANGE, splittedData[5]);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_MAG_X_VALUE_CHANGE, splittedData[6]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_MAG_X_VALUE_CHANGE, splittedData[7]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.SENSOR_MAG_X_VALUE_CHANGE, splittedData[8]);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
