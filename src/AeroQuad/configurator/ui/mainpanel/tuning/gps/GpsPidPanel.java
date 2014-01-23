package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.ui.ConfiguratorPanel;
import AeroQuad.configurator.ui.IConfiguratorController;

public class GpsPidPanel extends ConfiguratorPanel implements IGpsPidPanel
{
    private final IGpsPidPanelController _controller;

    public GpsPidPanel(final IGpsPidPanelController gpsPidPanelController)
    {
        _controller = gpsPidPanelController;
    }

    @Override
    public IConfiguratorController getController()
    {
        return _controller;
    }
}
