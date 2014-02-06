package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

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


public class YawPidPanel extends JPanel implements IYawPidPanel
{
    private final IYawPidPanelController _controller;

    private final PidPanel _yawPidPanel = new PidPanel("Yaw");
    private final PidPanel _headingHoldPidPanel = new PidPanel("Heading Hold");
    private final JButton _resetDefaultButton = new JButton("<HTML><CENTER>Reset<BR>Default</CENTER></HTML>");
    private final SyncedStatePanel _syncStatePanel = new SyncedStatePanel();
    private UserLevel _userLevel = UserLevel.Beginner;
    private JPanel _centerPanel;


    public YawPidPanel(final IYawPidPanelController yawPidPanelController)
    {
        _controller = yawPidPanelController;

        _controller.setPanel(this);

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

        _centerPanel = new JPanel(new GridLayout(1,4));

        mainPanel.add(_centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);

        _yawPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userYawPidChanged(_yawPidPanel.getPid());
            }
        });
        _headingHoldPidPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.headingHoldPidChanged(_headingHoldPidPanel.getPid());
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
    public void setYawPid(final PIDData pid)
    {
        _yawPidPanel.setPid(pid);
    }

    @Override
    public void setHeadingHoldPid(final PIDData pid)
    {
        _headingHoldPidPanel.setPid(pid);
    }

    @Override
    public void setSinced(final boolean sinced)
    {
        _syncStatePanel.setSynced(sinced);
    }

    private void updateCenterPanelFromUserLevel()
    {
        _centerPanel.removeAll();
        _centerPanel.add(_yawPidPanel);
        _yawPidPanel.setDVisible(false);
        if (_userLevel == UserLevel.Intermediate)
        {
            _centerPanel.add(_headingHoldPidPanel);
            _headingHoldPidPanel.setDVisible(false);
        }
        else if (_userLevel == UserLevel.Advanced)
        {
            _centerPanel.add(_headingHoldPidPanel);
            _yawPidPanel.setDVisible(true);
            _headingHoldPidPanel.setDVisible(true);
        }

        _centerPanel.add(_resetDefaultButton);
        _centerPanel.add(_syncStatePanel);
    }
}
