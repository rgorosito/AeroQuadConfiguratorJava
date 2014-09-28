package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.messagesdispatcher.PIDData;
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

public class GpsPidPanel extends JPanel implements IGpsPidPanel
{
    private final IGpsPidPanelController _controller;

    private final PidPanel _rollPidPanel = new PidPanel("Roll");
    private final PidPanel _pitchPidPanel = new PidPanel("Pitch");
    private final PidPanel _yawPidPanel = new PidPanel("Yaw");
    private final JButton _resetDefaultButton = new JButton("<HTML><CENTER>Reset<BR>Default</CENTER></HTML>");
    private final SyncedStatePanel _syncStatePanel = new SyncedStatePanel();
    private JPanel _centerPanel;
    private UserLevel _userLevel = UserLevel.Beginner;


    public GpsPidPanel(final IGpsPidPanelController gpsPidPanelController)
    {
        _controller = gpsPidPanelController;

        _controller.setPanel(this);

        initPanel();

        connectListener();
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

        _centerPanel = new JPanel(new GridLayout(1,5));

        mainPanel.add(_centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);
    }

    private void connectListener()
    {
        _rollPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userRollPidChanged(_rollPidPanel.getPid());
            }
        });
        _pitchPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userPitchPidChanged(_pitchPidPanel.getPid());
            }
        });
        _yawPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userYawPidChanged(_yawPidPanel.getPid());
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
    public void setSinced(final boolean sinced)
    {
        _syncStatePanel.setSynced(sinced);
    }

    @Override
    public void setRollPid(final PIDData rollPid)
    {
        _rollPidPanel.setPid(rollPid);
    }

    @Override
    public void setPitchPid(final PIDData pitchPid)
    {
        _pitchPidPanel.setPid(pitchPid);
    }

    @Override
    public void setYawPid(final PIDData yawPid)
    {
        _yawPidPanel.setPid(yawPid);
    }

    private void updateCenterPanelFromUserLevel()
    {
        _centerPanel.removeAll();
        _centerPanel.add(_rollPidPanel);
        if (_userLevel == UserLevel.Beginner)
        {
            _rollPidPanel.setHeader("Both Axe");
            _rollPidPanel.setIVisible(false);
            _rollPidPanel.setDVisible(false);
            _centerPanel.add(_yawPidPanel);
            _yawPidPanel.setIVisible(false);
            _yawPidPanel.setDVisible(false);
        }
        else if (_userLevel == UserLevel.Intermediate)
        {
            _rollPidPanel.setHeader("Roll");
            _rollPidPanel.setIVisible(false);
            _rollPidPanel.setDVisible(false);
            _centerPanel.add(_pitchPidPanel);
            _pitchPidPanel.setIVisible(false);
            _pitchPidPanel.setDVisible(false);
            _centerPanel.add(_yawPidPanel);
            _yawPidPanel.setIVisible(false);
            _yawPidPanel.setDVisible(false);
        }
        else if (_userLevel == UserLevel.Advanced)
        {
            _rollPidPanel.setHeader("Roll");
            _rollPidPanel.setIVisible(true);
            _rollPidPanel.setDVisible(true);
            _centerPanel.add(_pitchPidPanel);
            _pitchPidPanel.setIVisible(true);
            _pitchPidPanel.setDVisible(true);
            _centerPanel.add(_yawPidPanel);
            _yawPidPanel.setIVisible(true);
            _yawPidPanel.setDVisible(true);
        }
        _centerPanel.add(_resetDefaultButton);
        _centerPanel.add(_syncStatePanel);
    }
}
