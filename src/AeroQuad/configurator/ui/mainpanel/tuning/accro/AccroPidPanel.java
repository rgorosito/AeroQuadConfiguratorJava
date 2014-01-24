package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import javax.swing.JPanel;


public class AccroPidPanel extends JPanel implements IAccroPidPanel
{
    private final IAccroPidPanelController _controller;

    public AccroPidPanel(final IAccroPidPanelController accroPanelController)
    {
        _controller = accroPanelController;
    }
}
