package AeroQuad.configurator.ui.mainpanel.setup.vehiclesetup;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.VehicleInfoRequest;
import AeroQuad.configurator.messagesdispatcher.FlightConfigType;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.ReceiverType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VehicleSetupController implements IVehicleSetupController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IVehicleSetupPanel _panel;
    private int _nbChannels = 0;

    public VehicleSetupController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;

        messageDispatcher.addListener(IMessageDispatcher.FLIGHT_CONFIG_PROPERTY_KEY, new PropertyChangeListener()
        {
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final FlightConfigType flightConfigType = FlightConfigType.fromOrdinal(Integer.parseInt((String)evt.getNewValue()));
                _panel.selectFlightConfigType(flightConfigType);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_TYPE_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final ReceiverType receiverType = ReceiverType.fromOrdinal(Integer.parseInt((String)evt.getNewValue()));
                _panel.setReceiverType(receiverType);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.YAW_DIRECTION_KET, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final int reversed = Integer.parseInt((String)evt.getNewValue());
                _panel.setYawIsReversed(reversed == -1);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.NB_RECEIVER_CHANNEL_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _nbChannels = (int)evt.getNewValue();
                updateOptionVisibilityFromChannelCount(_nbChannels);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.BATTERY_MONITOR_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean batterieMonitorEnabled = (boolean)evt.getNewValue();
                System.out.println(batterieMonitorEnabled);
                _panel.setBatterieMonitorSelected(batterieMonitorEnabled);
            }
        });
    }

    @Override
    public void setPanel(final IVehicleSetupPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void pwmReceiverSelected()
    {
        _communicator.sendCommand("J " + ReceiverType.PWM.ordinal() + ";");
        if (_nbChannels == 5)
        {
            _panel.selectQuadX();
        }
    }

    @Override
    public void ppmReceiverSelected()
    {
        _communicator.sendCommand("J " + ReceiverType.PPM.ordinal() + ";");
    }

    @Override
    public void sbusReceiverSelected()
    {
        _communicator.sendCommand("J " + ReceiverType.SBUS.ordinal() + ";");
    }

    @Override
    public void triConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.TRI.ordinal() + ";");
    }

    @Override
    public void quadXConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.QUAD_X.ordinal() + ";");
    }

    @Override
    public void quadPlusConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.QUAD_PLUS.ordinal() + ";");
    }

    @Override
    public void quadY4ConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.QUAD_Y4.ordinal() + ";");
    }

    @Override
    public void hexY6ConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.HEX_Y6.ordinal() + ";");
        if (_nbChannels == 5)
        {
            _panel.selectPpmReceiver();
        }
    }

    @Override
    public void hexXConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.HEX_X.ordinal() + ";");
        if (_nbChannels == 5)
        {
            _panel.selectPpmReceiver();
        }
    }

    @Override
    public void hexPlusConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.HEX_PLUS.ordinal() + ";");
        if (_nbChannels == 5)
        {
            _panel.selectPpmReceiver();
        }
    }

    @Override
    public void octoX8ConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.OCTO_X8.ordinal() + ";");
    }

    @Override
    public void octoXConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.OCTO_X.ordinal() + ";");
    }

    @Override
    public void octoPlusConfigSelected()
    {
        _communicator.sendCommand("Y " + FlightConfigType.OCTO_PLUS.ordinal() + ";");
    }

    @Override
    public void reverseYawSelected(final boolean reversed)
    {
        int reversedInt = reversed ? -1 : 1;
        _communicator.sendCommand("Z " + Integer.toString(reversedInt) + ";");
    }

    @Override
    public void batterieMonitorSelected(final boolean selected)
    {
        int selectedInt = selected ? 1 : 0;
        _communicator.sendCommand("E " + Integer.toString(selectedInt) + ";");
    }

    private void updateOptionVisibilityFromChannelCount(final int nbChannels)
    {
        _panel.setSbusVisible(nbChannels != 5);
        _panel.setOctoX8Visible(nbChannels != 5);
        _panel.setOctoXVisible(nbChannels != 5);
        _panel.setOctoPlusVisible(nbChannels != 5);
        _panel.setBatterieMonitorEnabled(nbChannels != 5);
    }
}
