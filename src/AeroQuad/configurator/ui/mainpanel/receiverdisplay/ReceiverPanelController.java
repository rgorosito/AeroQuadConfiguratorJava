package AeroQuad.configurator.ui.mainpanel.receiverdisplay;

import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ReceiverPanelController implements IReceiverDisplayPanelController
{
    private IReceiverDisplayPanel _panel;
    private boolean _enabled = true;

    public ReceiverPanelController(final IMessageDispatcher messageDispatcher)
    {
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_ROLL_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_enabled)
                {
                    _panel.setRollValue((String) evt.getNewValue());
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_PITCH_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_enabled)
                {
                    _panel.setPitchValue((String) evt.getNewValue());
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_YAW_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_enabled)
                {
                    _panel.setYawValue((String) evt.getNewValue());
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_THROTTLE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_enabled)
                {
                    _panel.setThrottleValue((String) evt.getNewValue());
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_MODE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_enabled)
                {
                    _panel.setModeValue((String) evt.getNewValue());
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX1_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_enabled)
                {
                    _panel.setAux1Value((String) evt.getNewValue());
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX2_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_enabled)
                {
                    _panel.setAux2Value((String) evt.getNewValue());
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX3_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if (_enabled)
                {
                    _panel.setAux3Value((String) evt.getNewValue());
                }
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.NB_RECEIVER_CHANNEL_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setNbChannel((Integer) evt.getNewValue());
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.CONNECTION_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean isConnected = (boolean)evt.getNewValue();
                if (!isConnected)
                {
                    _panel.setDisconnected();
                }
            }
        });
    }

    @Override
    public void setPanel(final IReceiverDisplayPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void setEnabled(final boolean enabled)
    {
        _enabled = enabled;
        _panel.setRollValue("1500");
        _panel.setPitchValue("1500");
        _panel.setYawValue("1500");
        _panel.setThrottleValue("1000");
        _panel.setModeValue("1000");
        _panel.setAux1Value("1000");
        _panel.setAux2Value("1000");
        _panel.setAux3Value("1000");
    }
}
