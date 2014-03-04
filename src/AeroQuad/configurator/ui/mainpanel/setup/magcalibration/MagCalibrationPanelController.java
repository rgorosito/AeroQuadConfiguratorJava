package AeroQuad.configurator.ui.mainpanel.setup.magcalibration;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.MagRawValueRequest;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.MagRawData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MagCalibrationPanelController implements IMagCalibrationPanelController
{
    private final IMessageDispatcher _messageDispatcher;

    enum MagCalibrationState
    {
        GATTERING_DATA,
        STANDBY
    };

    private MagCalibrationState _currentCalibrationState = MagCalibrationState.STANDBY;

    private IMagCalibrationPanel _panel;
    private final ISerialCommunicator _communicator;

    private int _xMin = 0;
    private int _xMax = 0;
    private int _yMin = 0;
    private int _yMax = 0;
    private int _zMin = 0;
    private int _zMax = 0;


    public MagCalibrationPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.MAG_RAW_DATA_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final MagRawData magRawData = (MagRawData)evt.getNewValue();
                processReceivedMagRawData(magRawData);
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
        _xMin = 0;
        _xMax = 0;
        _yMin = 0;
        _yMax = 0;
        _zMin = 0;
        _zMax = 0;

        _panel.setXMinValue("0");
        _panel.setXMaxValue("0");
        _panel.setXSliderValue(0);
        _panel.setYMinValue("0");
        _panel.setYMaxValue("0");
        _panel.setYSliderValue(0);
        _panel.setZMinValue("0");
        _panel.setZMaxValue("0");
        _panel.setZSliderValue(0);
        _panel.setButtonText("Start");
        _communicator.sendCommand(ISerialCommunicator.REQUEST_STOP_SENDING);
        _currentCalibrationState = MagCalibrationState.STANDBY;
    }

    @Override
    public void setPanel(final IMagCalibrationPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void buttonPressed()
    {
        if (_currentCalibrationState == MagCalibrationState.STANDBY)
        {
            _communicator.sendRequest(new MagRawValueRequest(_messageDispatcher));
            _panel.setButtonText("Finish");
            _currentCalibrationState = MagCalibrationState.GATTERING_DATA;
        }
        else
        {
            updateCalValueToBoard();
            setStandbyState();
        }
    }

    private void processReceivedMagRawData(final MagRawData magRawData)
    {
        final String xValue = magRawData.getX();
        int x = Integer.valueOf(xValue);
        _xMin = Math.min(x,_xMin);
        _xMax = Math.max(x, _xMax);
        _panel.setXSliderValue(x);
        _panel.setXMinValue(Integer.toString(_xMin));
        _panel.setXMaxValue(Integer.toString(_xMax));

        final String yValue = magRawData.getY();
        int y = Integer.valueOf(yValue);
        _yMin = Math.min(y,_yMin);
        _yMax = Math.max(y, _yMax);
        _panel.setYSliderValue(y);
        _panel.setYMinValue(Integer.toString(_yMin));
        _panel.setYMaxValue(Integer.toString(_yMax));

        final String zValue = magRawData.getZ();
        int z = Integer.valueOf(zValue);
        _zMin = Math.min(z,_zMin);
        _zMax = Math.max(z, _zMax);
        _panel.setZSliderValue(z);
        _panel.setZMinValue(Integer.toString(_zMin));
        _panel.setZMaxValue(Integer.toString(_zMax));
    }

    private void updateCalValueToBoard()
    {
        final double xMagBias = -((double)(_xMin +_xMax) / 2);
        final double yMagBias = -((double)(_yMin +_yMax) / 2);
        final double zMagBias = -((double)(_zMin +_zMax) / 2);

        final StringBuffer magBiasSetCommand = new StringBuffer();
        magBiasSetCommand.append("M ");
        magBiasSetCommand.append(xMagBias).append(";");
        magBiasSetCommand.append(yMagBias).append(";");
        magBiasSetCommand.append(zMagBias).append(";");
        _communicator.sendCommand(magBiasSetCommand.toString());
    }
}
