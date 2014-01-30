package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class GpsPidPanelController implements IGpsPidPanelController
{
    private IGpsPidPanel _panel;

    @Override
    public void setActivated(final boolean activated)
    {

    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        _panel.setUserLevel(userLevel);
    }

    @Override
    public void setPanel(final IGpsPidPanel panel)
    {
        _panel = panel;
    }
}
