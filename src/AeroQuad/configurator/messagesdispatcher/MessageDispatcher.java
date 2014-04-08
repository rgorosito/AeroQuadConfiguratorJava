package AeroQuad.configurator.messagesdispatcher;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class MessageDispatcher implements IMessageDispatcher
{
    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    private VehicleAttitude _vehicleAttitude = new VehicleAttitude(0, 0, 0);

    @Override
    public void addListener(final String propertyName, final PropertyChangeListener listener)
    {
        _propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void dispatchMessage(final String messageId, final Object value)
    {
        _propertyChangeSupport.firePropertyChange(messageId, null, value);
    }

}
