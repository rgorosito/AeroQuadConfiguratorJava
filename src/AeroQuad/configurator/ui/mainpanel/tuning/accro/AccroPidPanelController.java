package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class AccroPidPanelController implements IAccroPidPanelController
{
    private IAccroPidPanel _panel;

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
    public void setPanel(final IAccroPidPanel panel)
    {
        _panel = panel;
    }
}
