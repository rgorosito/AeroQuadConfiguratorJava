package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import javax.swing.JPanel;


public class YawPidPanel extends JPanel implements IYawPidPanel
{
    private final IYawPidPanelController _controller;

    public YawPidPanel(final IYawPidPanelController yawPidPanelController)
    {
        _controller = yawPidPanelController;
    }
}
