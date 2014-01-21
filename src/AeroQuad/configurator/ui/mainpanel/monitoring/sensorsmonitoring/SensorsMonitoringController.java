package AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.SensorsValueRequest;
import AeroQuad.configurator.messageDispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SensorsMonitoringController implements ISensorsMonitoringController
{

    private ISensorsMonitoringPanel _panel;
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;

    public SensorsMonitoringController(final IMessageDispatcher messageDispatcher,final ISerialCommunicator communicator)
    {
        _messageDispatcher = messageDispatcher;
        _communicator = communicator;
        messageDispatcher.addListener(IMessageDispatcher.MAGNETOMETER_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setHaveMagnetometer((Boolean) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.BAROMETER_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                // need a message change, will wait for 3.2 to be released
//                _panel.setHaveBarometer((Boolean) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_GYRO_X_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setGyroX((String) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_GYRO_Y_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setGyroY((String) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_GYRO_Z_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setGyroZ((String) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_ACCEL_X_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setAccelX((String) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_ACCEL_Y_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setAccelY((String) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_ACCEL_Z_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setAccelZ((String) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_MAG_X_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setMagX((String) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_MAG_Y_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setMagY((String) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.SENSOR_MAG_Z_VALUE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setMagZ((String) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.VEHICLE_ALTITUDE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setBaroAltitude((Float) evt.getNewValue());
            }
        });
    }

    @Override
    public void setActivated(final boolean activated)
    {
        if (activated)
        {
            _communicator.sendRequest(new SensorsValueRequest(_messageDispatcher,activated));
        }
    }

    @Override
    public void setPanel(final ISensorsMonitoringPanel panel)
    {
        _panel = panel;
    }
}
