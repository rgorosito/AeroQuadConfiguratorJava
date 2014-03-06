package AeroQuad.configurator.communication.communicationstatistics;


import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CommunicationStatisticsProcessor implements ICommunicationStatisticsProcessor
{
    private final IMessageDispatcher _messageDispatcher;
    private int _nbCharSent;
    private int _nbCharReceived;

    public CommunicationStatisticsProcessor(final ISerialCommunicator communicator, IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
        messageDispatcher.addListener(IMessageDispatcher.RAW_DATA_MESSAGE_SENT, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final String sentString = (String)evt.getNewValue();
                _nbCharSent += sentString.length();
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RAW_DATA_MESSAGE_RECEIVED, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final String receivedString = (String)evt.getNewValue();
                _nbCharReceived += receivedString.length();
            }
        });
    }

    @Override
    public int getNbCharReceived()
    {
        return _nbCharReceived;
    }

    @Override
    public void processCommunicationStatistics()
    {
        final double percentUsed = 100 * (((_nbCharReceived + _nbCharSent) * 8F) / 115200F);
        _messageDispatcher.dispatchMessage(IMessageDispatcher.COMMUNICATION_USAGE_VALUE_CAHNGED, percentUsed);
        _nbCharReceived = 0;
        _nbCharSent = 0;
    }
}
