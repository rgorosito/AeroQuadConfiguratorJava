package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;

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

            final PIDData yawPidData = new PIDData(splittedData[0], splittedData[1], splittedData[2]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.YAW_PID_KEY, yawPidData);

            final PIDData headingHoldPidData = new PIDData(splittedData[3], splittedData[4], splittedData[5]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.HEADING_HOLD_PID_KEY, headingHoldPidData);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
