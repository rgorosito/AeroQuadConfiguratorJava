package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MotorMonitoringPanel extends JPanel implements IMotorMonitoringPanel
{
    private final IMotorMonitoringPanelController _controller;

    public MotorMonitoringPanel(final IMotorMonitoringPanelController controller, final JPanel motorDisplayPanel)
    {
        _controller = controller;

        _controller.setPanel(this);

        init(motorDisplayPanel);
    }

    private void init(final JPanel motorDisplayPanel)
    {
        setLayout(new BorderLayout());
        add(motorDisplayPanel, BorderLayout.CENTER);
    }
}
