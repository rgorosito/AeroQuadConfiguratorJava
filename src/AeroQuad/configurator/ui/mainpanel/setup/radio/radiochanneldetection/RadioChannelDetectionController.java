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
    private final int NB_RESET_TO_COUNT_THRESHOLD = 20;
    private final int DETECTION_OFFSET_THRESHOLD = 300;
    private final int NOT_DETECTING = 0;
    private final int DETECTING = 1;
    private final int FINISHING = 2;

    private final IMessageDispatcher _messageDispatcher;
    private final ISerialCommunicator _communicator;
    private IRadioChannelDetectionPanel _panel;
    private int _nbChannels = 5;

    private final Map<Integer,Integer> _minOffsetMap = new HashMap<Integer,Integer>(8);
    private final Map<Integer,Integer> _maxOffsetMap = new HashMap<Integer,Integer>(8);

    private final Map<Integer,Integer> _detectedIndex = new HashMap<Integer,Integer>(8);

    private ReceiverChannel _currentDetectingChannel= ReceiverChannel.THROTTLE;
    private int _nbResetSampleTOCount;

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
        _currentDetectingChannel = ReceiverChannel.THROTTLE;
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
        _currentDetectingState = NOT_DETECTING;
    }

    @Override
    public void setPanel(final IRadioChannelDetectionPanel panel)
    {
        _panel = panel;
    }




    private void detectChannelsFromRawData(final String rawData)
    {
        if (_currentDetectingState != DETECTING)
        {
            return;
        }

        final String splittedData[] = rawData.split(",");
        int[] channelValues = getChannelsIntegerValues(splittedData);
        if (_nbResetSampleTOCount < NB_RESET_TO_COUNT_THRESHOLD)
        {
            for (int i = 0; i < _nbChannels; i++)
            {
                _minOffsetMap.put(i, channelValues[i]);
                _maxOffsetMap.put(i, channelValues[i]);
            }
            _nbResetSampleTOCount++;
            return;
        }

        updateOffsetsMaps(channelValues);
        final int currentDetectedIndex = detectChannelIndex();
        if (currentDetectedIndex == -1)
        {
            return;
        }

        _detectedIndex.put(_currentDetectingChannel.ordinal(),currentDetectedIndex);
        synchronized (_panel)
        {
            _panel.setChannelDetected(_currentDetectingChannel);
        }
        if (_currentDetectingChannel.ordinal() >= _nbChannels-1)
        {
            _currentDetectingState = FINISHING;
            _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
            _panel.setFinishing();
        }
        else
        {
            _currentDetectingChannel = ReceiverChannel.getNext(_currentDetectingChannel);
            _nbResetSampleTOCount = 0;
        }
    }

    private void updateOffsetsMaps(final int[] channelValues)
    {
        for (int i = 0; i < _nbChannels; i++)
        {
            _minOffsetMap.put(i, Math.min(_minOffsetMap.get(i), channelValues[i]));
            _maxOffsetMap.put(i, Math.max(_maxOffsetMap.get(i), channelValues[i]));
        }
    }

    private int[] getChannelsIntegerValues(final String[] splittedData)
    {
        int channelValues[] = new int[_nbChannels];
        for (int i = 0; i < _nbChannels; i++)
        {
            try
            {
                channelValues[i] = Integer.parseInt(splittedData[i]);
            }
            catch (Exception e)
            {
                channelValues[i] = 1500;
            }
        }
        return channelValues;
    }

    private int detectChannelIndex()
    {
        for (int i = 0; i < _nbChannels; i++)
        {
            final int offset = Math.abs(_maxOffsetMap.get(i) - _minOffsetMap.get(i));
            if (offset > DETECTION_OFFSET_THRESHOLD)
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void completeDetection()
    {
        sendDetectionValues();
        synchronized (_panel)
        {
            _panel.setStartButtonEnabled(true);
            _panel.setCancelButtonEnabled(false);
            _panel.resetWidgetInitialState();
        }

    }

    private void sendDetectionValues()
    {
        System.out.print("Index = ");
        for (int i = 0; i < _nbChannels; i++)
        {
            System.out.print(_detectedIndex.get(i) + ",");
        }
        System.out.printf("");
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
