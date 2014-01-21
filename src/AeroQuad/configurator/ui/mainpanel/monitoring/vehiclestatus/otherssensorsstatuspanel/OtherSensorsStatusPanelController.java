package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel;


import AeroQuad.configurator.messageDispatcher.FlightMode;
import AeroQuad.configurator.messageDispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OtherSensorsStatusPanelController implements IOtherSensorsStatusPanelController
{
    private IOtherSensorsStatusPanel _panel;

    public OtherSensorsStatusPanelController(final IMessageDispatcher messageDispatcher)
    {
        messageDispatcher.addListener(IMessageDispatcher.BAROMETER_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setBaroEnabled((Boolean) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.BATTERY_MONITOR_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setBatteryMonitorEnabled((Boolean) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.MOTOR_ARMED_STATE_CHANGED, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setMotorArmedState((Boolean) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.VEHICLE_ALTITUDE_HOLD_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setAltitudeHoldState((Boolean) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.VEHICLE_ALTITUDE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setVehicleAltitude((Float) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.VEHICLE_FLIGHT_MODE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setFlightMode((FlightMode) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.VEHICLE_VOLTAGE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setVoltage((Float) evt.getNewValue());
            }
        });
    }

    @Override
    public void setPanel(final IOtherSensorsStatusPanel panel)
    {
        _panel = panel;
    }
}
