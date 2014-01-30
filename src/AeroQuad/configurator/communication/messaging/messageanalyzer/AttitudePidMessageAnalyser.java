package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;

public class AttitudePidMessageAnalyser implements IMessageAnalyser
{

    private final IMessageDispatcher _messageDispatcher;

    public AttitudePidMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");

            final PIDData accelRollPid = new PIDData(splittedData[0], splittedData[1], splittedData[2]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ATTITUDE_ACCEL_ROLL_PID_KEY, accelRollPid);
            final PIDData accelPitchPid = new PIDData(splittedData[3], splittedData[4], splittedData[5]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ATTITUDE_ACCEL_PITCH_PID_KEY, accelPitchPid);
            final PIDData gyroRollPid = new PIDData(splittedData[6], splittedData[7], splittedData[8]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ATTITUDE_GYRO_ROLL_PID_KEY, gyroRollPid);
            final PIDData gyroPitchPid = new PIDData(splittedData[9], splittedData[10], splittedData[11]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ATTITUDE_GYRO_PITCH_PID_KEY, gyroPitchPid);

        }
        catch (final Exception e)
        {
            return false;
        }

        return true;
    }
}
