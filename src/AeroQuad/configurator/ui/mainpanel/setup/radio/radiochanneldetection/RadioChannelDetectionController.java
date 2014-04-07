package AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.ReceiverRawValueRequest;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RadioChannelDetectionController implements IRadioChannelDetectionController
{
    private final IMessageDispatcher _messageDispatcher;
    private final ISerialCommunicator _communicator;

    private int _nbChannel = 6;

    public RadioChannelDetectionController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _messageDispatcher = messageDispatcher;
        _communicator = communicator;

        _messageDispatcher.addListener(IMessageDispatcher.NB_RECEIVER_CHANNEL_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _nbChannel = (int)evt.getNewValue();
            }
        });
    }

    @Override
    public void setActivated(final boolean activated)
    {
        if (activated)
        {
            _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
        }

    }

    @Override
    public void startDetection()
    {
        _communicator.sendRequest(new ReceiverRawValueRequest(_messageDispatcher));
    }

    @Override
    public void cancelDetection()
    {

    }
}
