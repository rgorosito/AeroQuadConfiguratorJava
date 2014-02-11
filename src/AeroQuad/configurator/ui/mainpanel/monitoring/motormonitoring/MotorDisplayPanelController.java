package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.MotorDisplayController;

public class MotorDisplayPanelController extends MotorDisplayController implements IMotorDisplayPanelController
{
    public MotorDisplayPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        super(messageDispatcher, communicator);
    }
}
