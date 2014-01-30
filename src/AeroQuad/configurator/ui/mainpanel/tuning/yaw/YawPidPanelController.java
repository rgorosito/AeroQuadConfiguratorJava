package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class YawPidPanelController implements IYawPidPanelController
{
    private IYawPidPanel _panel;

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
    public void setPanel(final IYawPidPanel panel)
    {
        _panel = panel;
    }
}
