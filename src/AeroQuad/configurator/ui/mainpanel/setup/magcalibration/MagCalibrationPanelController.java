package AeroQuad.configurator.ui.mainpanel.setup.magcalibration;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
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

    private double _xMin = 0.0;
    private double _xMax = 0.0;
    private double _yMin = 0.0;
    private double _yMax = 0.0;
    private double _zMin = 0.0;
    private double _zMax = 0.0;


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
            _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
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
        _currentCalibrationState = MagCalibrationState.STANDBY;
        _panel.setCancelEnable(false);
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
            _panel.setCancelEnable(true);
        }
        else
        {
            _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
            updateCalValueToBoard();
            setStandbyState();
        }
    }

    @Override
    public void cancelCalibration()
    {
        _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
        setStandbyState();
    }

    private void processReceivedMagRawData(final MagRawData magRawData)
    {
        final String xValue = magRawData.getX();
        double x = Double.valueOf(xValue);
        _xMin = Math.min(x,_xMin);
        _xMax = Math.max(x, _xMax);
        _panel.setXSliderValue((int)x);
        _panel.setXMinValue(Integer.toString((int)_xMin));
        _panel.setXMaxValue(Integer.toString((int)_xMax));

        final String yValue = magRawData.getY();
        double y = Double.valueOf(yValue);
        _yMin = Math.min(y,_yMin);
        _yMax = Math.max(y, _yMax);
        _panel.setYSliderValue((int)y);
        _panel.setYMinValue(Integer.toString((int)_yMin));
        _panel.setYMaxValue(Integer.toString((int)_yMax));

        final String zValue = magRawData.getZ();
        double z = Double.valueOf(zValue);
        _zMin = Math.min(z,_zMin);
        _zMax = Math.max(z, _zMax);
        _panel.setZSliderValue((int)z);
        _panel.setZMinValue(Integer.toString((int)_zMin));
        _panel.setZMaxValue(Integer.toString((int)_zMax));
    }

    // AQ Conf = 5.000000,-93.000000,-21.500000 // maison
    // AQ Conf = 158.500000,-63.000000,-19.000000 // JOb!?!?

    private void updateCalValueToBoard()
    {
        final double xMagBias = -((_xMin + _xMax) / 2);
        final double yMagBias = -((_yMin + _yMax) / 2);
        final double zMagBias = -((_zMin + _zMax) / 2);

        final StringBuffer magBiasSetCommand = new StringBuffer();
        magBiasSetCommand.append("M ");
        magBiasSetCommand.append(xMagBias).append(";");
        magBiasSetCommand.append(yMagBias).append(";");
        magBiasSetCommand.append(zMagBias).append(";");
        _communicator.sendCommand(magBiasSetCommand.toString());
    }
}
