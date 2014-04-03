package AeroQuad.configurator.ui.mainpanel.setup.radio.radiocalibration;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
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

    private int _minRoll = 1500;
    private int _maxRoll = 1500;
    private int _minPitch = 1500;
    private int _maxPitch = 1500;
    private int _minYaw = 1500;
    private int _maxYaw = 1500;
    private int _minThrottle = 1500;
    private int _maxThrottle = 1500;
    private int _minMode = 1500;
    private int _maxMode = 1500;
    private int _minAux1 = 1500;
    private int _maxAux1 = 1500;
    private int _minAux2 = 1500;
    private int _maxAux2 = 1500;
    private int _minAux3 = 1500;
    private int _maxAux3 = 1500;


    public RadioCalibrationPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator, final IReceiverDisplayPanelController receiverDisplayPanelController)
    {
        _messageDispatcher = messageDispatcher;
        _communicator = communicator;
        _receiverDisplayPanelController = receiverDisplayPanelController;

        _receiverDisplayPanelController.setEnabled(false);

        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_ROLL_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_currentCalibrationState == RadioCalibrationState.GATTERING_DATA)
                {
                    final String value = (String) evt.getNewValue();
                    final int roll = Integer.valueOf(value);
                    _minRoll = Math.min(roll, _minRoll);
                    _maxRoll = Math.max(roll, _maxRoll);
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_PITCH_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_currentCalibrationState == RadioCalibrationState.GATTERING_DATA)
                {
                    final String value = (String) evt.getNewValue();
                    final int pitch = Integer.valueOf(value);
                    _minPitch = Math.min(pitch, _minPitch);
                    _maxPitch = Math.max(pitch, _maxPitch);

                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_YAW_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_currentCalibrationState == RadioCalibrationState.GATTERING_DATA)
                {
                    final String value = (String) evt.getNewValue();
                    final int yaw = Integer.valueOf(value);
                    _minYaw = Math.min(yaw, _minYaw);
                    _maxYaw = Math.max(yaw, _maxYaw);
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_THROTTLE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_currentCalibrationState == RadioCalibrationState.GATTERING_DATA)
                {
                    final String value = (String) evt.getNewValue();
                    final int throttle = Integer.valueOf(value);
                    _minThrottle = Math.min(throttle, _minThrottle);
                    _maxThrottle = Math.max(throttle, _maxThrottle);
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_MODE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_currentCalibrationState == RadioCalibrationState.GATTERING_DATA)
                {
                    final String value = (String) evt.getNewValue();
                    final int mode = Integer.valueOf(value);
                    _minMode = Math.min(mode, _minMode);
                    _maxMode = Math.max(mode, _maxMode);
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX1_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_currentCalibrationState == RadioCalibrationState.GATTERING_DATA)
                {
                    final String value = (String) evt.getNewValue();
                    final int aux1 = Integer.valueOf(value);
                    _minAux1 = Math.min(aux1, _minAux1);
                    _maxAux1 = Math.max(aux1, _maxAux1);
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX2_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_currentCalibrationState == RadioCalibrationState.GATTERING_DATA)
                {
                    final String value = (String) evt.getNewValue();
                    final int aux2 = Integer.valueOf(value);
                    _minAux2 = Math.min(aux2, _minAux2);
                    _maxAux2 = Math.max(aux2, _maxAux2);
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX3_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_currentCalibrationState == RadioCalibrationState.GATTERING_DATA)
                {
                    final String value = (String) evt.getNewValue();
                    final int aux3 = Integer.valueOf(value);
                    _minAux3 = Math.min(aux3, _minAux3);
                    _maxAux3 = Math.max(aux3, _maxAux3);
                }
            }
        });

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
        else
        {
            setStandbyState();
        }
    }

    private void setStandbyState()
    {
        _panel.setButtonText("Start");
        _panel.setCancelEnable(false);
        _receiverDisplayPanelController.setEnabled(false);
        _currentCalibrationState = RadioCalibrationState.STANDBY;

        _minRoll = 1500;
        _maxRoll = 1500;
        _minPitch = 1500;
        _maxPitch = 1500;
        _minYaw = 1500;
        _maxYaw = 1500;
        _minThrottle = 1500;
        _maxThrottle = 1500;
        _minMode = 1500;
        _maxMode = 1500;
        _minAux1 = 1500;
        _maxAux1 = 1500;
        _minAux2 = 1500;
        _maxAux2 = 1500;
        _minAux3 = 1500;
        _maxAux3 = 1500;

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
            _receiverDisplayPanelController.setEnabled(true);
            _communicator.sendRequest(new ReceiverRawValueRequest(_messageDispatcher, _nbChannel));
            _panel.setCancelEnable(true);
        }
        else
        {
            _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
            sendRadioCalValueCommand();
        }
    }

    @Override
    public void cancel()
    {
        _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
        setStandbyState();
    }

    private void sendRadioCalValueCommand()
    {
        final Thread sendingThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                sendValueToAeroquad();
            }
        });
        sendingThread.start();

    }

    private void sendValueToAeroquad()
    {
        _communicator.sendCommand(getMinSetCommand().toString());
        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException e)
        {

        }
        _communicator.sendCommand(getMaxSetCommand().toString());
        setStandbyState();
    }

    private StringBuffer getMinSetCommand()
    {
        final StringBuffer minSetCommand = new StringBuffer();
        minSetCommand.append("G ");
        minSetCommand.append(_minRoll).append(";");
        minSetCommand.append(_minPitch).append(";");
        minSetCommand.append(_minYaw).append(";");
        minSetCommand.append(_minThrottle).append(";");
        minSetCommand.append(_minMode).append(";");
        minSetCommand.append(_minAux1).append(";");
        if (_nbChannel > 6)
        {
            minSetCommand.append(_minAux2).append(";");
            minSetCommand.append(_minAux3).append(";");
        }
        return minSetCommand;
    }

    private StringBuffer getMaxSetCommand()
    {
        final StringBuffer minSetCommand = new StringBuffer();
        minSetCommand.append("H ");
        minSetCommand.append(_maxRoll).append(";");
        minSetCommand.append(_maxPitch).append(";");
        minSetCommand.append(_maxYaw).append(";");
        minSetCommand.append(_maxThrottle).append(";");
        minSetCommand.append(_maxMode).append(";");
        minSetCommand.append(_maxAux1).append(";");
        if (_nbChannel > 6)
        {
            minSetCommand.append(_maxAux2).append(";");
            minSetCommand.append(_maxAux3).append(";");
        }
        return minSetCommand;
    }
}
