package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.messagesdispatcher.YawPidData;

public class YawPidMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public YawPidMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");

            final PIDData yawPid = new PIDData(splittedData[0], splittedData[1], splittedData[2]);
            final String yawingSpeedFactor = splittedData[3];

            final YawPidData yawPidData = new YawPidData(yawPid, yawingSpeedFactor);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.YAW_PID_KEY, yawPidData);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
