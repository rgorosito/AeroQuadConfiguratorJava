package AeroQuad.configurator.communication.messaging.messageanalyzer;


import AeroQuad.configurator.messageDispatcher.IMessageDispatcher;

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

            _messageDispatcher.setGyroXValue(splittedData[0]);
            _messageDispatcher.setGyroYValue(splittedData[1]);
            _messageDispatcher.setGyroZValue(splittedData[2]);

            _messageDispatcher.setAccelXValue(splittedData[3]);
            _messageDispatcher.setAccelYValue(splittedData[4]);
            _messageDispatcher.setAccelZValue(splittedData[5]);

            _messageDispatcher.setMagXValue(splittedData[6]);
            _messageDispatcher.setMagYValue(splittedData[7]);
            _messageDispatcher.setMagZValue(splittedData[8]);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
