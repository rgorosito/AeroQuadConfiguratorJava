package AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class RadioChannelDetectionController implements IRadioChannelDetectionController
{
    private final IMessageDispatcher _messageDispatcher;
    private final ISerialCommunicator _communicator;

    public RadioChannelDetectionController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _messageDispatcher = messageDispatcher;
        _communicator = communicator;
    }

    @Override
    public void setActivated(final boolean activated)
    {

    }

    @Override
    public void startDetection()
    {

    }

    @Override
    public void cancelDetection()
    {

    }
}
