package AeroQuad.configurator.ui.connectionpanel;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConnectionStatusPanelController implements IConnectionStatusPanelController
{
    private final ISerialCommunicator _communicator;
    private IConnectionStatusPanel _panel;

    public ConnectionStatusPanelController(final ISerialCommunicator communicator, final IMessageDispatcher messageDispatcher)
    {
        _communicator = communicator;

        messageDispatcher.addListener(IMessageDispatcher.CONNECTION_STATE_CHANGE,new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent event)
            {
                final boolean connected = ((Boolean)event.getNewValue()).booleanValue();
                updateConnectionState(connected);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.COMMUNICATION_USAGE_VALUE_CAHNGED, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                final double usage = (double)evt.getNewValue();
                _panel.setUsage(usage);
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.BAUD_RATE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                final int boadRate = (int)evt.getNewValue();
                _panel.setBaudRate(Integer.toString(boadRate));
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.COMM_PORT_OPENED, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                final String comPort = (String)evt.getNewValue();
                _panel.setCommPortOpen(comPort);
            }
        });

    }

    private void updateConnectionState(final boolean connected)
    {
        _panel.setConnected(connected);
    }


    public void setPanel(IConnectionStatusPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void useWireless(boolean useWireless)
    {
        _communicator.setUseWireless(useWireless);
    }
}
