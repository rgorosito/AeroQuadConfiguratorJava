package AeroQuad.configurator.communication.communicationstatistics;


import AeroQuad.configurator.communication.ISerialCommunicator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CommunicationStatisticsProcessor implements ICommunicationStatisticsProcessor
{
    private int _nbCharSent;
    private int _nbCharReceived;

    public CommunicationStatisticsProcessor(final ISerialCommunicator communicator)
    {
        communicator.addListener(ISerialCommunicator.RAW_DATA_MESSAGE_SENT, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final String sentString = (String)evt.getNewValue();
                _nbCharSent += sentString.length();
            }
        });
        communicator.addListener(ISerialCommunicator.RAW_DATA_MESSAGE_RECEIVED, new PropertyChangeListener()
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
        final float percentUsed = 100 * (((_nbCharReceived + _nbCharSent) * 8F) / 115200F);
        System.out.println(percentUsed);
        _nbCharReceived = 0;
        _nbCharSent = 0;
    }
}
