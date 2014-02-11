package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.MotorDisplayController;

public class MotorMonitoringDisplayPanelController extends MotorDisplayController implements IMotorDisplayPanelController
{
    public MotorMonitoringDisplayPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        super(messageDispatcher, communicator);
    }

    @Override
    public void setPanel(final IMotorDisplayPanel panel)
    {
        super.setPanel(panel);
    }
}
