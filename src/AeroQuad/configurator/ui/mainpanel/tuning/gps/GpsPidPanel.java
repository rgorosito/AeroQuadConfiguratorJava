package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import javax.swing.JPanel;

public class GpsPidPanel extends JPanel implements IGpsPidPanel
{
    private final IGpsPidPanelController _controller;

    public GpsPidPanel(final IGpsPidPanelController gpsPidPanelController)
    {
        _controller = gpsPidPanelController;
    }
}
