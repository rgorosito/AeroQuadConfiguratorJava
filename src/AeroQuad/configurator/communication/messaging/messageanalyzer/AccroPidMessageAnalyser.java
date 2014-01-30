package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;

public class AccroPidMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public AccroPidMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");

            final PIDData rollPidData = new PIDData(splittedData[0], splittedData[1], splittedData[2]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ACCRO_ROLL_PID_KEY, rollPidData);

            final PIDData pichPidData = new PIDData(splittedData[3], splittedData[4], splittedData[5]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ACCRO_PITCH_PID_KEY, pichPidData);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.STICK_SCALING_KEY, splittedData[6]);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
