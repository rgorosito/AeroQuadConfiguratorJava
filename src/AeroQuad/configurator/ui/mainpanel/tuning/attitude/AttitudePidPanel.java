package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;
import AeroQuad.configurator.ui.mainpanel.tuning.pidpanel.PidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.syncedstate.SyncedStatePanel;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AttitudePidPanel extends JPanel implements IAttitudePidPanel
{
    private final IAttitudePidPanelController _controller;

    private final PidPanel _gyroRollPidPanel = new PidPanel("Gyro Roll");
    private final PidPanel _accelRollPidPanel = new PidPanel("Accel Roll");
    private final PidPanel _gyroPicthPidPanel = new PidPanel("Gyro Pitch");
    private final PidPanel _accelPitchPidPanel = new PidPanel("Accel Pitch");
    private final JButton _resetDefaultButton = new JButton("<HTML><CENTER>Reset<BR>Default</CENTER></HTML>");
    private final SyncedStatePanel _syncStatePanel = new SyncedStatePanel();
    private UserLevel _userLevel = UserLevel.Beginner;
    private JPanel _centerPanel;


    public AttitudePidPanel(final IAttitudePidPanelController attitudePanelController)
    {
        _controller = attitudePanelController;
        _controller.setPanel(this);
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

        _centerPanel = new JPanel(new GridLayout(1,6));


        mainPanel.add(_centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);

        _gyroRollPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userGyroRollPidChanged(_gyroRollPidPanel.getPid());
            }
        });

        _gyroPicthPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userGyroPitchPidChanged(_gyroPicthPidPanel.getPid());
            }
        });

        _accelRollPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userAccelRollPidChanged(_accelRollPidPanel.getPid());
            }
        });

        _accelPitchPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userAccelPitchPidChanged(_accelPitchPidPanel.getPid());
            }
        });
        _resetDefaultButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userDefaultButtonPressed();
            }
        });
    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        _userLevel = userLevel;
        updateCenterPanelFromUserLevel();
    }

    @Override
    public void setGyroRollPid(final PIDData pid)
    {
        _gyroRollPidPanel.setPid(pid);
    }

    @Override
    public void setAccelRollPid(final PIDData pid)
    {
        _accelRollPidPanel.setPid(pid);
    }

    @Override
    public void setGyroPitchPid(final PIDData pid)
    {
        _gyroPicthPidPanel.setPid(pid);
    }

    @Override
    public void setAccelPitchPid(final PIDData pid)
    {
        _accelPitchPidPanel.setPid(pid);
    }

    @Override
    public void setSinced(final boolean synced)
    {
        _syncStatePanel.setSynced(synced);
    }

    private void updateCenterPanelFromUserLevel()
    {
        _centerPanel.removeAll();
        _centerPanel.add(_accelRollPidPanel);
        _centerPanel.add(_gyroRollPidPanel);

        if (_userLevel == UserLevel.Beginner)
        {
            _gyroRollPidPanel.setHeader("Gyro");
            _gyroRollPidPanel.setIVisible(false);
            _accelRollPidPanel.setHeader("Accel");
            _accelRollPidPanel.setIVisible(false);
            _accelRollPidPanel.setDVisible(false);
        }
        else if (_userLevel == UserLevel.Intermediate)
        {
            _accelRollPidPanel.setHeader("Accel Roll");
            _accelRollPidPanel.setIVisible(false);
            _accelRollPidPanel.setDVisible(false);
            _gyroRollPidPanel.setHeader("Gyro Roll");
            _gyroRollPidPanel.setIVisible(false);

            _centerPanel.add(_accelPitchPidPanel);
            _centerPanel.add(_gyroPicthPidPanel);

            _accelPitchPidPanel.setIVisible(false);
            _accelPitchPidPanel.setDVisible(false);
            _gyroPicthPidPanel.setIVisible(false);
        }
        else if (_userLevel == UserLevel.Advanced)
        {
            _centerPanel.add(_accelPitchPidPanel);
            _centerPanel.add(_gyroPicthPidPanel);

            _gyroRollPidPanel.setHeader("Gyro Roll");
            _gyroRollPidPanel.setDVisible(true);
            _gyroRollPidPanel.setIVisible(true);
            _accelRollPidPanel.setHeader("Accel Roll");
            _accelRollPidPanel.setIVisible(true);
            _accelRollPidPanel.setDVisible(true);

            _gyroPicthPidPanel.setDVisible(true);
            _gyroPicthPidPanel.setIVisible(true);
            _accelPitchPidPanel.setIVisible(true);
            _accelPitchPidPanel.setDVisible(true);
        }

        _centerPanel.add(_resetDefaultButton);
        _centerPanel.add(_syncStatePanel);
    }
}
