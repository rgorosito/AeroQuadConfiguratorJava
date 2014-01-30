package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class GpsPidPanelController implements IGpsPidPanelController
{
    private IGpsPidPanel _panel;

    public GpsPidPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {

    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        _panel.setUserLevel(userLevel);
    }

    @Override
    public boolean isSyncked()
    {
        return true;
    }

    @Override
    public void processSyncing()
    {

    }

    @Override
    public void setPanel(final IGpsPidPanel panel)
    {
        _panel = panel;
    }
}
