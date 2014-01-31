package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagedispatcher.AccroPidData;
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
            final PIDData pichPidData = new PIDData(splittedData[3], splittedData[4], splittedData[5]);
            final AccroPidData accroPidData = new AccroPidData(rollPidData, pichPidData, splittedData[6]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ACCRO_PID_DATA_KEY, accroPidData);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
