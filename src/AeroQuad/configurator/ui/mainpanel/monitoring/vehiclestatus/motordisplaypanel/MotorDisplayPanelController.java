package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.motordisplaypanel;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.MotorDisplayController;

public class MotorDisplayPanelController extends MotorDisplayController
{
    public MotorDisplayPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        super(messageDispatcher, communicator);

        connectMotorCommandListeners();
    }

    @Override
    public void setPanel(final IMotorDisplayPanel panel)
    {
        super.setPanel(panel);
        panel.setEditable(false);
    }
}
