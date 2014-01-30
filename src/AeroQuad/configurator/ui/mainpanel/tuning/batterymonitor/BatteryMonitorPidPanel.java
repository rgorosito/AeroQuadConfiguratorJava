package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class BatteryMonitorPidPanel extends JPanel implements IBatteryMonitorPidPanel
{
    private final IBatteryMonitorPidPanelController _controller;

    public BatteryMonitorPidPanel(final IBatteryMonitorPidPanelController batteryMonitorPidPanelController)
    {
        _controller = batteryMonitorPidPanelController;

        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());
        final JLabel headerLabel = new JLabel("Battey Monitoring");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        add(headerLabel);
    }

}
