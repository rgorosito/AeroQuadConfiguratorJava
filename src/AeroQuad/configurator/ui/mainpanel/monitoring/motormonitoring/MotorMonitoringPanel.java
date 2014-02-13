package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IUserMotorValueChangedListenrer;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.MotorDisplayPanel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotorMonitoringPanel extends JPanel implements IMotorMonitoringPanel
{
    private final IMotorMonitoringPanelController _controller;

    final JButton _sendButton = new JButton("<html><center>SEND<br>COMMAND</center></html>");
    final JButton _stopAllButton = new JButton("<html><center>STOP<br>ALL</center></html>");
    final JCheckBox _directCommandCheckBox = new JCheckBox("Button command override");
    private final IMotorDisplayPanel _motorDisplayPanel;

    public MotorMonitoringPanel(final IMotorMonitoringPanelController controller, final MotorDisplayPanel motorDisplayPanel)
    {
        _motorDisplayPanel = motorDisplayPanel;
        _controller = controller;

        _controller.setPanel(this);

        init(motorDisplayPanel);

        initMotorDisplayPanel(motorDisplayPanel);
    }

    private void initMotorDisplayPanel(final MotorDisplayPanel motorDisplayPanel)
    {
        motorDisplayPanel.setMaxMotorsValue(1200);
        motorDisplayPanel.addUserMotorValueChangedByUser(new IUserMotorValueChangedListenrer()
        {
            @Override
            public void motorValueChangedByUser(final int motor, final int value)
            {
                _controller.userMotorValueChanged(motor,value);
            }
        });
    }

    private void init(final JPanel motorDisplayPanel)
    {
        setLayout(new BorderLayout());

        initTopPanel();

        add(motorDisplayPanel, BorderLayout.CENTER);

        initBottomPanel();
    }

    private void initTopPanel()
    {
        final JPanel topPanel = new JPanel(new BorderLayout());
        final JLabel topHeaderLabel = new JLabel("MOTORS TROUBLESHOOTING");
        topHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(topHeaderLabel, BorderLayout.NORTH);

        final JPanel centerPanel = new JPanel(new GridLayout(1,3));
        centerPanel.add(new JLabel());
        final JButton showConfigButton = new JButton("<HTML><Center>Click here to check your flight configuration");
        centerPanel.add(showConfigButton);
        centerPanel.add(new JLabel());
        topPanel.add(centerPanel, BorderLayout.CENTER);

        final JLabel bottomLabel = new JLabel("Connect the batterie and verify that the right motor turn in the right rotation way for each motor");
        bottomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(bottomLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        showConfigButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.showCraftConfugurationPage();
            }
        });
    }

    private void initBottomPanel()
    {
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        final JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(_sendButton);
        buttonPanel.add(_stopAllButton);
        bottomPanel.add(buttonPanel,BorderLayout.WEST);

        bottomPanel.add(_directCommandCheckBox,BorderLayout.CENTER);

        final JPanel leftPanel = new JPanel(new GridLayout(2,1));
        final StringBuffer buffer = new StringBuffer();
        buffer.append("<html>By checking this, you are aware that motor will rotate<br>as soon as you will move a slider<html>");
        leftPanel.add(new JLabel(buffer.toString()));
        final JLabel warningLabel = new JLabel("PLEASE, REMOVE ALL PROPELLER FOR YOUR OWN SAFETY");
        warningLabel.setForeground(Color.YELLOW);
        leftPanel.add(warningLabel);
        bottomPanel.add(leftPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        _sendButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.sendUserMotorCommand();
            }
        });
        _stopAllButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.stopAllMotor();
            }
        });
        _directCommandCheckBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.overrideManualSendCommand(_directCommandCheckBox.isSelected());
            }
        });

    }

    @Override
    public void setMotorValue(final int motor, final Integer value)
    {
        _motorDisplayPanel.setMotorValue(motor, value);
    }

    @Override
    public void setSendEnabled(final boolean enabled)
    {
        _sendButton.setEnabled(enabled);
    }
}
