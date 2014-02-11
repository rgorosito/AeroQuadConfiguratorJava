package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayController;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayPanel;

public class MotorMonitoringPanelController implements IMotorMonitoringPanelController, IMotorDisplayController
{

    public MotorMonitoringPanelController(final IMessageDispatcher messageDispatcher,
                                          final ISerialCommunicator communicator)
    {
    }

    @Override
    public void setActivated(final boolean activated)
    {

    }


    @Override
    public void setPanel(final IMotorDisplayPanel panel)
    {

    }
}
