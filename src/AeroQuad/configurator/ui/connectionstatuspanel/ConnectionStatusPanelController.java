package AeroQuad.configurator.ui.connectionstatuspanel;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ConnectionStatusPanelController implements IConnectionStatusPanelController
{
    private final ISerialCommunicator _communicator;
    private IConnectionStatusPanel _panel;
    private List<String> _commPortList;

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

        messageDispatcher.addListener(IMessageDispatcher.COM_PORT_LIST_UPDATE_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final List<String> commPortList = (List<String>)evt.getNewValue();
                updateCommPortList(commPortList);
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

    private void updateCommPortList(final List<String> commPortList)
    {
        if (_commPortList == null)
        {
            if (commPortList.size() != 0)
            {
                _commPortList = commPortList;
                _panel.setCommPortList(commPortList);
            }
        }
        else
        {
            if (_commPortList.size() != commPortList.size())
            {
                _commPortList = commPortList;
                _panel.setCommPortList(commPortList);
            }
            else
            {
                for (final String commPort : commPortList)
                {
                    if (!_commPortList.contains(commPort))
                    {
                        _commPortList = commPortList;
                        _panel.setCommPortList(commPortList);
                        break;
                    }
                }
            }
        }
    }
}
