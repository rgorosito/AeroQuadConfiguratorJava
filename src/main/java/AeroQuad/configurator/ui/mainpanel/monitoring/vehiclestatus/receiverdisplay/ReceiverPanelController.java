package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.receiverdisplay;

import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ReceiverPanelController implements IReceiverDisplayPanelController
{
    private IReceiverDisplayPanel _panel;

    public ReceiverPanelController(final IMessageDispatcher messageDispatcher)
    {
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_ROLL_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setRollValue((String) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_PITCH_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setPitchValue((String) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_YAW_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setYawValue((String) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_THROTTLE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setThrottleValue((String) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_MODE_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setModeValue((String) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX1_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setAux1Value((String) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX2_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setAux2Value((String) evt.getNewValue());
            }
        });
        messageDispatcher.addListener(IMessageDispatcher.RECEIVER_AUX3_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _panel.setAux3Value((String) evt.getNewValue());
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
    }

    @Override
    public void setPanel(final IReceiverDisplayPanel panel)
    {
        _panel = panel;
    }
}
