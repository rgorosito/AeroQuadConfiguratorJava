package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

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


public class AttitudePidPanel extends JPanel implements IAttitudePidPanel
{
    private final IAttitudePidPanelController _controller;

    private final PidPanel _gyroRollPidPanel = new PidPanel("Gyro Roll");
    private final PidPanel _accelRollPidPanel = new PidPanel("Accel Roll");
    private final PidPanel _gyroPichPidPanel = new PidPanel("Gyro Pitch");
    private final PidPanel _accelPitchPidPanel = new PidPanel("Accel Pitch");
    private final JButton _resetDefaultButton = new JButton("Reset Default");


    public AttitudePidPanel(final IAttitudePidPanelController attitudePanelController)
    {
        _controller = attitudePanelController;
        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,3));

        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JLabel headerLabel = new JLabel("Attitude");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        headerLabel.setBorder(new LineBorder(Color.black, 1));

        final JPanel centerPanel = new JPanel(new GridLayout(1,5));
        centerPanel.add(_gyroRollPidPanel);
        centerPanel.add(_accelRollPidPanel);
        centerPanel.add(_gyroPichPidPanel);
        centerPanel.add(_accelPitchPidPanel);
        centerPanel.add(_resetDefaultButton);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);
    }

}
