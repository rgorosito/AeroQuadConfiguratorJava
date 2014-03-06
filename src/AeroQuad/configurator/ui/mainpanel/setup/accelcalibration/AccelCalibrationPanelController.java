package AeroQuad.configurator.ui.mainpanel.setup.accelcalibration;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.AccelRawValueRequest;
import AeroQuad.configurator.messagesdispatcher.AccelRawData;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// @todo, clean reset panel when cancel or deactivate
public class AccelCalibrationPanelController implements IAccelCalibrationPanelController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IAccelCalibrationPanel _panel;

    private CalibrationStep _currentCalibrationStep = CalibrationStep.FLAT_UP;
    private int _readingSample;

    private final float _rawAccelSumValue[] = new float[6];

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
            _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
        }
        else
        {
            resetInitialState();
            for (int i = 0; i < 6; i++)
            {
                _rawAccelSumValue[i] = 0.0F;
            }
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
                break;
            case FINISHED:
                processFinishCalibrationProcess();
        }
    }

    @Override
    public void cancelButtonPressed()
    {
        _currentCalibrationStep = CalibrationStep.FLAT_UP;
        _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
        _readingSample = 0;
        _panel.setCancelButtonEnabled(false);
        _panel.setNextButtonEnabeld(true);
        _panel.setProgress(0);
        _panel.setCurrentCalibrationStep(_currentCalibrationStep);
        for (int i = 0; i < 6; i++)
        {
            _rawAccelSumValue[i] = 0.0F;
        }
    }

    @Override
    public void calibrateLevel()
    {
        _communicator.sendCommand("L");
    }

    private void processAccelRawDataReceived(final AccelRawData accelRawData)
    {
        _readingSample++;
        switch(_currentCalibrationStep)
        {
            case FLAT_UP:
            case FLAT_DOWN:
                _rawAccelSumValue[_currentCalibrationStep.ordinal()] += Integer.valueOf(accelRawData.getZ());
                break;
            case LEFT_SIDE_DOWN:
            case RIGHT_SIDE_DOWN:
                _rawAccelSumValue[_currentCalibrationStep.ordinal()] += Integer.valueOf(accelRawData.getY());
                break;
            case NOSE_UP:
            case NOSE_DOWN:
                _rawAccelSumValue[_currentCalibrationStep.ordinal()] += Integer.valueOf(accelRawData.getX());
                break;

        }

        _panel.setProgress(((_readingSample*100)/NB_ACCEL_SAMPLE_TO_READ));
        if (_readingSample >= NB_ACCEL_SAMPLE_TO_READ)
        {
            processReadingSampleTerminated();
        }
    }


    private void processReadingSampleTerminated()
    {
        _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
        _panel.setNextButtonEnabeld(true);
        _readingSample = 0;
        _panel.setProgress(0);
        _currentCalibrationStep = CalibrationStep.getNextStep(_currentCalibrationStep);
        _panel.setCurrentCalibrationStep(_currentCalibrationStep);
    }

    private void processFinishCalibrationProcess()
    {
        final float tempZ = (Math.abs(_rawAccelSumValue[0]) + Math.abs(_rawAccelSumValue[1])) / 2;
        final float tempY = (Math.abs(_rawAccelSumValue[2]) + Math.abs(_rawAccelSumValue[3])) / 2;
        final float tempX = (Math.abs(_rawAccelSumValue[4]) + Math.abs(_rawAccelSumValue[5])) / 2;

        float zScaleFactor = ONE_G / (tempZ / NB_ACCEL_SAMPLE_TO_READ);
        float yScaleFactor = ONE_G / (tempY / NB_ACCEL_SAMPLE_TO_READ);
        float xScaleFactor = ONE_G / (tempX / NB_ACCEL_SAMPLE_TO_READ);

        if (_rawAccelSumValue[1] < 0)
        {
            zScaleFactor = zScaleFactor * -1;
        }

        if (_rawAccelSumValue[2] < 0)
        {
            yScaleFactor = yScaleFactor * -1;
        }
        if (_rawAccelSumValue[4] < 0)
        {
            xScaleFactor = xScaleFactor * -1;
        }

        StringBuffer buffer = new StringBuffer(1);
        buffer.append("K ");
        buffer.append(xScaleFactor).append(";").append(0).append(";");
        buffer.append(yScaleFactor).append(";").append(0).append(";");
        buffer.append(zScaleFactor).append(";").append(0).append(";");

        _communicator.sendCommand(buffer.toString());


        resetInitialState();
    }

    private void resetInitialState()
    {
        _currentCalibrationStep = CalibrationStep.FLAT_UP;
        _readingSample = 0;
        _panel.setCancelButtonEnabled(false);
        _panel.setNextButtonEnabeld(true);
        _panel.setProgress(0);
        _panel.setCurrentCalibrationStep(_currentCalibrationStep);
    }

}

