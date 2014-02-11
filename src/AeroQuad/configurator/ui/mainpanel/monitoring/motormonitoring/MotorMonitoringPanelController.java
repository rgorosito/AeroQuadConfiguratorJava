package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;

public class MotorMonitoringPanelController implements IMotorMonitoringPanelController
{
    private final IMotorDisplayPanelController _motorDisplayController;

    public MotorMonitoringPanelController(final IMessageDispatcher messageDispatcher,
                                          final ISerialCommunicator communicator,
                                          final IMotorDisplayPanelController motorDisplayController)
    {
        _motorDisplayController = motorDisplayController;
    }

    @Override
    public void setActivated(final boolean activated)
    {

    }

    @Override
    public void setPanel(final MotorMonitoringPanel panel)
    {

    }
}
