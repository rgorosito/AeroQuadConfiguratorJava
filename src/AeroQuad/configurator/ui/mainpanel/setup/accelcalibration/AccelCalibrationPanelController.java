package AeroQuad.configurator.ui.mainpanel.setup.accelcalibration;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.AccelRawValueRequest;
import AeroQuad.configurator.messagedispatcher.AccelRawData;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccelCalibrationPanelController implements IAccelCalibrationPanelController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IAccelCalibrationPanel _panel;

    private CalibrationStep _currentCalibrationStep = CalibrationStep.FLAT_UP;
    private int _readingSample;

    public AccelCalibrationPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.ACCEL_RAW_DATA_KEY,new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final AccelRawData accelRawData = (AccelRawData)evt.getNewValue();
                processAccelRawDataReceived(accelRawData);
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
            _currentCalibrationStep = CalibrationStep.FLAT_UP;
            _readingSample = 0;
            _panel.setCancelButtonEnabled(false);
            _panel.setNextButtonEnabeld(true);
            _panel.setProgress(0);
            _panel.setCurrentCalibrationStep(_currentCalibrationStep);
        }
    }

    @Override
    public void setPanel(final IAccelCalibrationPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void nextButtonPressed()
    {
        switch(_currentCalibrationStep)
        {
            case FLAT_UP:
            case FLAT_DOWN:
            case LEFT_SIDE_DOWN:
            case RIGHT_SIDE_DOWN:
            case NOSE_UP:
            case NOSE_DOWN:
                _panel.setCancelButtonEnabled(true);
                _panel.setNextButtonEnabeld(false);
                _communicator.sendRequest(new AccelRawValueRequest(_messageDispatcher));
        }
    }

    @Override
    public void cancelButtonPressed()
    {
        _currentCalibrationStep = CalibrationStep.FLAT_UP;
        _communicator.sendCommand(ISerialCommunicator.REQUEST_STOP_SENDING);
        _readingSample = 0;
        _panel.setCancelButtonEnabled(false);
        _panel.setNextButtonEnabeld(true);
        _panel.setProgress(0);
        _panel.setCurrentCalibrationStep(_currentCalibrationStep);
    }

    public String getTextStep()
    {
        switch(_currentCalibrationStep)
        {
            case FLAT_UP:
                return IAccelCalibrationPanel.FLAT_UP_TEXT;
            case FLAT_DOWN:
                return IAccelCalibrationPanel.FLAT_DOWN_TEXT;
            case LEFT_SIDE_DOWN:
                return IAccelCalibrationPanel.LEFT_SIDE_DOWN_TEXT;
            case RIGHT_SIDE_DOWN:
                return IAccelCalibrationPanel.RIGHT_SIDE_DOWN_TEXT;
            case NOSE_UP:
                return IAccelCalibrationPanel.NOSE_UP_TEXT;
            case NOSE_DOWN:
                return IAccelCalibrationPanel.NOSE_DOWN_TEXT;
        }
        return "";
    }

    private void processAccelRawDataReceived(final AccelRawData accelRawData)
    {
        _readingSample++;
        _panel.setProgress(((_readingSample*100)/NB_ACCEL_SAMPLE_TO_READ));
        if (_readingSample >= NB_ACCEL_SAMPLE_TO_READ)
        {
            processReadingSampleTerminated();
        }
    }


    private void processReadingSampleTerminated()
    {
        _communicator.sendCommand(ISerialCommunicator.REQUEST_STOP_SENDING);
        _panel.setNextButtonEnabeld(true);
        _readingSample = 0;
        _panel.setProgress(0);
        _currentCalibrationStep = CalibrationStep.getNextStep(_currentCalibrationStep);
        _panel.setCurrentCalibrationStep(_currentCalibrationStep);
    }
}
