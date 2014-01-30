package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class AttitudePidPanelController implements IAttitudePidPanelController
{
    private IAttitudePidPanel _panel;

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
    public void setPanel(final IAttitudePidPanel panel)
    {
        _panel = panel;
    }
}
