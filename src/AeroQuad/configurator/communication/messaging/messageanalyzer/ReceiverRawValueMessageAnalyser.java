package AeroQuad.configurator.communication.messaging.messageanalyzer;


import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class ReceiverRawValueMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;
    private final int _nbChannel;

    public ReceiverRawValueMessageAnalyser(final IMessageDispatcher messageDispatcher, final int nbChannel)
    {
        _messageDispatcher = messageDispatcher;
        _nbChannel = nbChannel;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");

            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_ROLL_STATE_CHANGE, splittedData[0]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_PITCH_STATE_CHANGE, splittedData[1]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_YAW_STATE_CHANGE, splittedData[2]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_THROTTLE_STATE_CHANGE, splittedData[3]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_MODE_STATE_CHANGE, splittedData[4]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_AUX1_STATE_CHANGE, splittedData[5]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_AUX2_STATE_CHANGE, splittedData[6]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_AUX3_STATE_CHANGE, splittedData[7]);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
