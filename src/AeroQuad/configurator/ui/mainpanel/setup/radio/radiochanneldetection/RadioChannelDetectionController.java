package AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.ReceiverRawValueRequest;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class RadioChannelDetectionController implements IRadioChannelDetectionController
{
    private final int NOT_DETECTING = 0;
    private final int DETECTING = 1;

    private final IMessageDispatcher _messageDispatcher;
    private final ISerialCommunicator _communicator;
    private IRadioChannelDetectionPanel _panel;
    private int _nbChannels = 5;

    private final Map<Integer,Integer> _offsetMap = new HashMap<Integer,Integer>(1);

    private ReceiverDetectionState _currentDetectingChannel= ReceiverDetectionState.THROTTLE;

    private int _currentDetectingState = NOT_DETECTING;

    public RadioChannelDetectionController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _messageDispatcher = messageDispatcher;
        _communicator = communicator;

        _messageDispatcher.addListener(IMessageDispatcher.NB_RECEIVER_CHANNEL_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _nbChannels = (int)evt.getNewValue();
                updateChannelsToDisplay(_nbChannels);
            }
        });

        _messageDispatcher.addListener(IMessageDispatcher.RECEIVER_ALL_RAW_DATA_CHANGED, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final String rawData = (String)evt.getNewValue();
                detectChannelsFromRawData(rawData);
            }
        });
    }

    private void updateChannelsToDisplay(final int nbChannels)
    {
        _panel.setAux1Visible(nbChannels >= 6);
        _panel.setAux2Visible(nbChannels >= 7);
        _panel.setAux3Visible(nbChannels >= 8);
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
        _panel.setCancelButtonEnabled(true);
        _panel.setStartButtonEnabled(false);
        _currentDetectingChannel = ReceiverDetectionState.THROTTLE;
        _panel.setCurrentChannelDetected(_currentDetectingChannel);
        _currentDetectingState = DETECTING;
        final Thread _uiFeedbackThread = new Thread(new UiDetectionFeedbackThread());
        _uiFeedbackThread.start();
        _communicator.sendRequest(new ReceiverRawValueRequest(_messageDispatcher));
    }

    @Override
    public void cancelDetection()
    {
        _panel.setCancelButtonEnabled(false);
        _panel.setStartButtonEnabled(true);
        _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
    }

    @Override
    public void setPanel(final IRadioChannelDetectionPanel panel)
    {
        _panel = panel;
    }


    private void detectChannelsFromRawData(final String rawData)
    {
        System.out.println(rawData);
    }


    class UiDetectionFeedbackThread implements Runnable
    {
        private int _cpt = 0;

        @Override
        public void run()
        {
            while (_currentDetectingState == DETECTING)
            {
                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                }
                _cpt++;
                if (_cpt > 5)
                {
                    _cpt = 0;
                }
                synchronized (_panel)
                {
                    _panel.updateUserFeedback(_currentDetectingChannel,_cpt);
                }
            }
        }
    }
}
