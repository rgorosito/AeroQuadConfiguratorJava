package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.ui.ConfiguratorPanel;
import AeroQuad.configurator.ui.IConfiguratorController;


public class AttitudePidPanel extends ConfiguratorPanel implements IAttitudePidPanel
{
    private final IAttitudePidPanelController _controller;

    public AttitudePidPanel(final IAttitudePidPanelController attitudePanelController)
    {
        _controller = attitudePanelController;
    }

    @Override
    public IConfiguratorController getController()
    {
        return _controller;
    }
}
