package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

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


public class YawPidPanel extends JPanel implements IYawPidPanel
{
    private final IYawPidPanelController _controller;

    private final PidPanel _yawPidPanel = new PidPanel("Yaw");
    private final PidPanel _headingHoldPidPanel = new PidPanel("Heading Hold");
    private final JButton _resetDefaultButton = new JButton("Reset Default");


    public YawPidPanel(final IYawPidPanelController yawPidPanelController)
    {
        _controller = yawPidPanelController;

        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,3));

        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JLabel headerLabel = new JLabel("Yaw");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        headerLabel.setBorder(new LineBorder(Color.black, 1));

        final JPanel centerPanel = new JPanel(new GridLayout(1,3));
        centerPanel.add(_yawPidPanel);
        centerPanel.add(_headingHoldPidPanel);
        centerPanel.add(_resetDefaultButton);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);
    }
}
