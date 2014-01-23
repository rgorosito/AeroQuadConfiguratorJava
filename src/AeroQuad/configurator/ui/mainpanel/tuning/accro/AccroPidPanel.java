package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.ui.ConfiguratorPanel;
import AeroQuad.configurator.ui.IConfiguratorController;


public class AccroPidPanel extends ConfiguratorPanel implements IAccroPidPanel
{
    private final IAccroPidPanelController _controller;

    public AccroPidPanel(final IAccroPidPanelController accroPanelController)
    {
        _controller = accroPanelController;
    }

    @Override
    public IConfiguratorController getController()
    {
        return _controller;
    }
}
