package AeroQuad.configurator.ui.vehicleinfosfeedback;


import AeroQuad.configurator.messagesdispatcher.FlightConfigType;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VehicleInfoFeedbackController implements IVehicleInfoFeedbackController
{
    private IVehicleInfoFeedbackPanel _panel;

    public VehicleInfoFeedbackController(final IMessageDispatcher messageDispatcher)
    {
        messageDispatcher.addListener(IMessageDispatcher.BOARD_TYPE_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final String boardType = (String)evt.getNewValue();
                _panel.setBoardType(boardType);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.GYROSCOPE_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean detected = (boolean) evt.getNewValue();
                _panel.setGyroscopeDetected(detected);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.ACCELEROMETER_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean detected = (boolean) evt.getNewValue();
                _panel.setAccelDetected(detected);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.BAROMETER_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean detected = (boolean) evt.getNewValue();
                _panel.setBarometerDetected(detected);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.MAGNETOMETER_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean detected = (boolean) evt.getNewValue();
                _panel.setMagDetected(detected);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.NB_RECEIVER_CHANNEL_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final int nbChannel = (int) evt.getNewValue();
                _panel.setNbChannel(nbChannel);

                updateSensorsAvailabilityFromNbChannels(nbChannel);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.FLIGHT_CONFIG_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final String flightConfig = (String)evt.getNewValue();
                final FlightConfigType configType = FlightConfigType.fromOrdinal(Integer.parseInt(flightConfig));
                _panel.setFlightConfig(configType.toString());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.CONNECTION_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean isConnected = (boolean)evt.getNewValue();
                updatePanelFromConnectionState(isConnected);
            }
        });
    }

    private void updatePanelFromConnectionState(final boolean isConnected)
    {
        if (!isConnected)
        {
            _panel.setBoardType("CONNECTING");
            _panel.setGyroscopeDetected(false);
            _panel.setAccelDetected(false);
            _panel.setBarometerDetected(false);
            _panel.setBaroVisible(false);
            _panel.setMagDetected(false);
            _panel.setMagVisible(false);
            _panel.setNbChannel(0);
            _panel.setFlightConfig("?");

        }
    }

    private void updateSensorsAvailabilityFromNbChannels(final int nbChannel)
    {
        _panel.setBaroVisible(nbChannel == 8);
        _panel.setMagVisible(nbChannel == 8);
    }

    @Override
    public void setPanel(final IVehicleInfoFeedbackPanel panel)
    {
        _panel = panel;
    }
}
