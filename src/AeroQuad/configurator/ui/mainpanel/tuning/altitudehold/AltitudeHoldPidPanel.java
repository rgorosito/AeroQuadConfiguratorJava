package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.ui.ConfiguratorPanel;
import AeroQuad.configurator.ui.IConfiguratorController;

public class AltitudeHoldPidPanel extends ConfiguratorPanel implements IAltitudeHoldPidPanel
{
    private final IAltitudeHoldPidPanelController _controller;

    public AltitudeHoldPidPanel(final IAltitudeHoldPidPanelController altitudeHoldPidPanelController)
    {
        _controller = altitudeHoldPidPanelController;
    }

    @Override
    public IConfiguratorController getController()
    {
        return _controller;
    }
}
