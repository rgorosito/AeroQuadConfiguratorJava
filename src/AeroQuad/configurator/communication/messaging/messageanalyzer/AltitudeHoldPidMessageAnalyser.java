package AeroQuad.configurator.communication.messaging.messageanalyzer;


import AeroQuad.configurator.messagesdispatcher.AltitudeHoldPidData;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.PIDData;

public class AltitudeHoldPidMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public AltitudeHoldPidMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");

            final PIDData altitudeHoldPid = new PIDData(splittedData[0], splittedData[1], splittedData[2]);
            final PIDData zDampeningPid = new PIDData(splittedData[7], splittedData[8], splittedData[9]);

            final AltitudeHoldPidData altitudeHoldPidData = new AltitudeHoldPidData(altitudeHoldPid,
                                                                                    splittedData[4],
                                                                                    splittedData[5],
                                                                                    splittedData[6],
                                                                                    zDampeningPid);

            _messageDispatcher.dispatchMessage(IMessageDispatcher.ALTITUDE_HOLD_PID_KEY, altitudeHoldPidData);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }
}
