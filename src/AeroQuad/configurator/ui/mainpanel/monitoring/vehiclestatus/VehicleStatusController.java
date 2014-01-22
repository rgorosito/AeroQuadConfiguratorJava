package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.VehicleStatusRequest;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.VehicleAttitude;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VehicleStatusController implements IVehicleStatusController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IVehicleStatusPanel _panel;

    public VehicleStatusController(final IMessageDispatcher aeroQuadModel,final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = aeroQuadModel;

        _messageDispatcher.addListener(IMessageDispatcher.VEHICLE_ATTITUDE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final VehicleAttitude vehicleAttitude = (VehicleAttitude) evt.getNewValue();
                _panel.setVehicleAttitude(vehicleAttitude);
            }
        });
    }

    @Override
    public void setActivated(final boolean activated)
    {
        _communicator.sendRequest(new VehicleStatusRequest(_messageDispatcher,activated));
    }

    @Override
    public IMessageDispatcher getMessageDispatcher()
    {
        return _messageDispatcher;
    }

    @Override
    public void setPanel(final IVehicleStatusPanel panel)
    {
        _panel = panel;
    }
}
