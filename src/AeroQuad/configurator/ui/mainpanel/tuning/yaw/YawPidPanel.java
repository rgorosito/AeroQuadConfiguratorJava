package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.ui.ConfiguratorPanel;
import AeroQuad.configurator.ui.IConfiguratorController;


public class YawPidPanel extends ConfiguratorPanel implements IYawPidPanel
{
    private final IYawPidPanelController _controller;

    public YawPidPanel(final IYawPidPanelController yawPidPanelController)
    {
        _controller = yawPidPanelController;
    }

    @Override
    public IConfiguratorController getController()
    {
        return _controller;
    }
}
