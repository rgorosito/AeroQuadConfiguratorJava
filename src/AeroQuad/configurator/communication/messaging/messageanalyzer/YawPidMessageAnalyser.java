package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.messagedispatcher.YawPidData;

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
            final PIDData headingHoldPidData = new PIDData(splittedData[3], splittedData[4], splittedData[5]);

            final YawPidData yawPidData = new YawPidData(yawPid, headingHoldPidData);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.YAW_PID_KEY, yawPidData);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
