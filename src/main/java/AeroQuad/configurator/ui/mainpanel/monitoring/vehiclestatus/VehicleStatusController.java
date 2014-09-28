package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.VehicleStatusRequest;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.VehicleAttitude;

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
        _messageDispatcher.addListener(IMessageDispatcher.GPS_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean haveGps = (boolean) evt.getNewValue();
                _panel.setHaveGps(haveGps);
            }
        });
    }

    @Override
    public void setActivated(final boolean activated)
    {
        if (activated)
        {
            final Thread startRequestThread = new Thread(new RequestStartThread());
            startRequestThread.start();
        }
    }

    private class RequestStartThread implements Runnable
    {

        @Override
        public void run()
        {
            try
            {
                Thread.sleep(300);
                _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
                Thread.sleep(300);
                _communicator.sendRequest(new VehicleStatusRequest(_messageDispatcher));
            }
            catch (InterruptedException e)
            {

            }
        }
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
