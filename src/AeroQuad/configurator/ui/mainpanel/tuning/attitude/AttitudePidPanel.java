package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import javax.swing.JPanel;


public class AttitudePidPanel extends JPanel implements IAttitudePidPanel
{
    private final IAttitudePidPanelController _controller;

    public AttitudePidPanel(final IAttitudePidPanelController attitudePanelController)
    {
        _controller = attitudePanelController;
    }
}
