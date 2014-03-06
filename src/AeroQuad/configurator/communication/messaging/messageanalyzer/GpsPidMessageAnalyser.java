package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagesdispatcher.GpsPidData;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.PIDData;

public class GpsPidMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public GpsPidMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");

            final PIDData rollPid = new PIDData(splittedData[0], splittedData[1], splittedData[2]);
            final PIDData pitchPid = new PIDData(splittedData[3], splittedData[4], splittedData[5]);
            final PIDData yawPid = new PIDData(splittedData[6], splittedData[7], splittedData[8]);

            final GpsPidData pidData = new GpsPidData(rollPid, pitchPid, yawPid);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.GPS_PID_KEY, pidData);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
