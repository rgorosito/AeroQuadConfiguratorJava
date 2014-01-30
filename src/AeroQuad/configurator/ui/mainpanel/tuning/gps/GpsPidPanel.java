package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.ui.mainpanel.tuning.pidpanel.PidPanel;
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

public class GpsPidPanel extends JPanel implements IGpsPidPanel
{
    private final IGpsPidPanelController _controller;

    private final PidPanel _rollPidPanel = new PidPanel("Roll");
    private final PidPanel _pitchPidPanel = new PidPanel("Pitch");
    private final PidPanel _yawPidPanel = new PidPanel("Yaw");
    private final JButton _resetDefaultButton = new JButton("<HTML><CENTER>Reset<BR>Default</CENTER></HTML>");


    public GpsPidPanel(final IGpsPidPanelController gpsPidPanelController)
    {
        _controller = gpsPidPanelController;

        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,4));

        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JLabel headerLabel = new JLabel("GPS");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        headerLabel.setBorder(new LineBorder(Color.black, 1));

        final JPanel centerPanel = new JPanel(new GridLayout(1,4));
        centerPanel.add(_rollPidPanel);
        centerPanel.add(_pitchPidPanel);
        centerPanel.add(_yawPidPanel);
        centerPanel.add(_resetDefaultButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);
    }
}
