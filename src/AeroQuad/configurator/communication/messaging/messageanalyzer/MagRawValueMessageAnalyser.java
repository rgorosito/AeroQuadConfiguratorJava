package AeroQuad.configurator.communication.messaging.messageanalyzer;


import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.MagRawData;

public class MagRawValueMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public MagRawValueMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");
            final MagRawData magRawData = new MagRawData(splittedData[0],splittedData[1],splittedData[2]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MAG_RAW_DATA_KEY, magRawData);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
