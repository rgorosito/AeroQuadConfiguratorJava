package AeroQuad.configurator.ui.mainpanel.tuning;

import AeroQuad.configurator.communication.ISerialCommunicator;

public class TuningPanelController implements ITuningPanelController
{
    private ITuningPanel _panel;

    public TuningPanelController(ISerialCommunicator communicator)
    {

    }

    @Override
    public void setPanel(final ITuningPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void setUserLevel(final String beginner)
    {

    }
}
