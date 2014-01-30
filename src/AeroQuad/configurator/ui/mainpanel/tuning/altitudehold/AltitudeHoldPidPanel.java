package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.ui.mainpanel.tuning.pidpanel.PidPanel;
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

public class AltitudeHoldPidPanel extends JPanel implements IAltitudeHoldPidPanel
{
    private final IAltitudeHoldPidPanelController _controller;

    private final PidPanel _rollPidPanel = new PidPanel("Altitude");
    private final SingleParamConfigPanel _windupGuardPanel = new SingleParamConfigPanel("<HTML><CENTER>Windup<BR>Guard</CENTER></HTML>");
    private final SingleParamConfigPanel _throttleBumpPanel = new SingleParamConfigPanel("<HTML><CENTER>Throttle<BR>Bump</CENTER></HTML>");
    private final SingleParamConfigPanel _throttlePanicPanel = new SingleParamConfigPanel("<HTML><CENTER>Throttle<BR>Panic</CENTER></HTML>");
    private final SingleParamConfigPanel _minThrottleAdjustPanel = new SingleParamConfigPanel("<HTML><CENTER>Min Throttle<BR>Adjust</CENTER></HTML>");
    private final SingleParamConfigPanel _maxThrottleAdjustPanel = new SingleParamConfigPanel("<HTML><CENTER>Max Throttle<BR>Adjust</CENTER></HTML>");
    private final SingleParamConfigPanel _smoothFactorPanel = new SingleParamConfigPanel("<HTML><CENTER>Smooth<BR>Factor</CENTER></HTML>");
    private final PidPanel _zDampening = new PidPanel("Z Dampening");
    private final JButton _resetDefaultButton = new JButton("<HTML><CENTER>Reset<BR>Default</CENTER></HTML>");

    public AltitudeHoldPidPanel(final IAltitudeHoldPidPanelController altitudeHoldPidPanelController)
    {
        _controller = altitudeHoldPidPanelController;

        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,4));

        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JLabel headerLabel = new JLabel("Altitude Hold");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        headerLabel.setBorder(new LineBorder(Color.black, 1));

        final JPanel centerPanel = new JPanel(new GridLayout(1,10));
        centerPanel.add(_rollPidPanel);
        centerPanel.add(_windupGuardPanel);
        centerPanel.add(_throttleBumpPanel);
        centerPanel.add(_throttlePanicPanel);
        centerPanel.add(_minThrottleAdjustPanel);
        centerPanel.add(_maxThrottleAdjustPanel);
        centerPanel.add(_smoothFactorPanel);
        centerPanel.add(_smoothFactorPanel);
        centerPanel.add(_zDampening);
        centerPanel.add(_resetDefaultButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);
    }
}
