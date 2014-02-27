package AeroQuad.configurator.ui.mainpanel.setup.esccalibration;

import AeroQuad.configurator.communication.ISerialCommunicator;

public class EscCalibrationPanelController implements IEscCalibrationPanelController
{
    private final ISerialCommunicator _communicator;

    public EscCalibrationPanelController(final ISerialCommunicator communicator)
    {
        _communicator = communicator;
    }

    @Override
    public void setActivated(final boolean activated)
    {
        if (activated)
        {
            _communicator.sendCommand(ISerialCommunicator.REQUEST_STOP_SENDING);
        }
    }

    @Override
    public void setHighThrottleCommand()
    {
        _communicator.sendCommand("1 123.45");
    }

    @Override
    public void setLowThrottleCommand()
    {
        _communicator.sendCommand("2 123.45");
    }
}
