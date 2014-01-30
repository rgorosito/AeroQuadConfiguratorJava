package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import AeroQuad.configurator.ui.mainpanel.tuning.singleparamconfigpanel.SingleParamConfigPanel;
import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class BatteryMonitorPidPanel extends JPanel implements IBatteryMonitorPidPanel
{
    private final IBatteryMonitorPidPanelController _controller;

    private final SingleParamConfigPanel _alarmVoltagePanel = new SingleParamConfigPanel("<HTML><CENTER>Alarm<BR>Voltage</CENTER></HTML>");
    private final SingleParamConfigPanel _throttleTargetPanel = new SingleParamConfigPanel("<HTML><CENTER>Throttle<BR>Target</CENTER></HTML>");
    private final SingleParamConfigPanel _goingDownTimePanel = new SingleParamConfigPanel("<HTML><CENTER>Going Down<BR>Time</CENTER></HTML>");

    private final JButton _resetDefaultButton = new JButton("<HTML><CENTER>Reset<BR>Default</CENTER></HTML>");

    public BatteryMonitorPidPanel(final IBatteryMonitorPidPanelController batteryMonitorPidPanelController)
    {
        _controller = batteryMonitorPidPanelController;

        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,4));

        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JLabel headerLabel = new JLabel("Accro");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        headerLabel.setBorder(new LineBorder(Color.black, 1));

        _alarmVoltagePanel.setPreferredSize(new Dimension(152,0));

        final JPanel centerPanel = new JPanel(new GridLayout(1,4));
        centerPanel.add(_alarmVoltagePanel);
        centerPanel.add(_throttleTargetPanel);
        centerPanel.add(_goingDownTimePanel);
        centerPanel.add(_resetDefaultButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);
    }}
