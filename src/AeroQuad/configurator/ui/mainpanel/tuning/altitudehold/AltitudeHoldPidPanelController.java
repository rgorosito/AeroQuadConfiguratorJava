package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class AltitudeHoldPidPanelController implements IAltitudeHoldPidPanelController
{
    private IAltitudeHoldPidPanel _panel;

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
    public void setPanel(final IAltitudeHoldPidPanel panel)
    {
        _panel = panel;
    }
}
