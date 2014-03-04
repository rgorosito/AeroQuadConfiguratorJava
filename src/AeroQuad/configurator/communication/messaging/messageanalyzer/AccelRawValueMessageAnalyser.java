package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagesdispatcher.AccelRawData;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class AccelRawValueMessageAnalyser  implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public AccelRawValueMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");

            final AccelRawData accelRawData = new AccelRawData(splittedData[0],splittedData[1],splittedData[2]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ACCEL_RAW_DATA_KEY, accelRawData);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
