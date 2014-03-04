package AeroQuad.configurator.ui.mainpanel.setup.radiocalibration;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.ReceiverRawValueRequest;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.IReceiverDisplayPanelController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RadioCalibrationPanelController implements IRadioCalibrationPanelController
{


    enum RadioCalibrationState
    {
        GATTERING_DATA,
        STANDBY
    };

    private final IMessageDispatcher _messageDispatcher;
    private IRadioCalibrationPanel _panel;
    private final ISerialCommunicator _communicator;
    private final IReceiverDisplayPanelController _receiverDisplayPanelController;
    private int _nbChannel = 6;

    private RadioCalibrationState _currentCalibrationState = RadioCalibrationState.STANDBY;

    public RadioCalibrationPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator, final IReceiverDisplayPanelController receiverDisplayPanelController)
    {
        _messageDispatcher = messageDispatcher;
        _communicator = communicator;
        _receiverDisplayPanelController = receiverDisplayPanelController;

        _receiverDisplayPanelController.setEnabled(false);

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
            _communicator.sendCommand(ISerialCommunicator.REQUEST_STOP_SENDING);
        }
        else
        {
            setStandbyState();
        }
    }

    private void setStandbyState()
    {
        _panel.setButtonText("Start");
        _receiverDisplayPanelController.setEnabled(false);
        _currentCalibrationState = RadioCalibrationState.STANDBY;
    }

    @Override
    public void setPanel(final IRadioCalibrationPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void buttonPressed()
    {
        if (_currentCalibrationState == RadioCalibrationState.STANDBY)
        {
            _panel.setButtonText("Finish");
            _currentCalibrationState = RadioCalibrationState.GATTERING_DATA;
            _communicator.sendRequest(new ReceiverRawValueRequest(_messageDispatcher, _nbChannel));
        }
        else
        {
            _communicator.sendCommand(ISerialCommunicator.REQUEST_STOP_SENDING);
            setStandbyState();
        }
    }
}
