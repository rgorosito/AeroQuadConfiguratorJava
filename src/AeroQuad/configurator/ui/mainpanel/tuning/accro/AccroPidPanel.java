package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;
import AeroQuad.configurator.ui.mainpanel.tuning.pidpanel.PidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.singleparamconfigpanel.SingleParamConfigPanel;
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


public class AccroPidPanel extends JPanel implements IAccroPidPanel
{
    private final IAccroPidPanelController _controller;

    private final PidPanel _rollPidPanel = new PidPanel("Roll");
    private final PidPanel _pitchPidPanel = new PidPanel("Pitch");
    private final JButton _resetDefaultButton = new JButton("<HTML><CENTER>Reset<BR>Default</CENTER></HTML>");
    private final SingleParamConfigPanel _stickScalingPanel = new SingleParamConfigPanel("<HTML><CENTER>Stick<BR>Scaling</CENTER></HTML>");
    private final SingleParamConfigPanel _throttlePIDAdjustmentPanel = new SingleParamConfigPanel("<HTML><CENTER>TPA</CENTER></HTML>");
    private final SyncedStatePanel _syncStatePanel = new SyncedStatePanel();
    private JPanel _centerPanel;
    private UserLevel _userLevel = UserLevel.Beginner;

    public AccroPidPanel(final IAccroPidPanelController accroPanelController)
    {
        _controller = accroPanelController;
        _controller.setPanel(this);

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

        _centerPanel = new JPanel(new GridLayout(1,6));

        mainPanel.add(_centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);

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

        _stickScalingPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userStickScalingChanged(_stickScalingPanel.getText());
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
        _throttlePIDAdjustmentPanel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.userThrottlePIDAdjustmentChanged(_throttlePIDAdjustmentPanel.getText());
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
    public void setRollPid(final PIDData pid)
    {
        _rollPidPanel.setPid(pid);
    }

    @Override
    public void setPitchPid(final PIDData pid)
    {
        _pitchPidPanel.setPid(pid);
    }

    @Override
    public void setStickScaling(final String stickScalling)
    {
        _stickScalingPanel.setText(stickScalling);
    }

    @Override
    public void setSinced(final boolean synced)
    {
        _syncStatePanel.setSynced(synced);
    }

    @Override
    public void setThrottlePIDAdjustment(final String throttlePIDAdjustment)
    {
        _throttlePIDAdjustmentPanel.setText(throttlePIDAdjustment);
    }

    private void updateCenterPanelFromUserLevel()
    {
        _centerPanel.removeAll();
        _centerPanel.add(_rollPidPanel);
        if (_userLevel == UserLevel.Beginner)
        {
            _rollPidPanel.setHeader("Both Axe");
        }
        else if (_userLevel == UserLevel.Intermediate)
        {
            _rollPidPanel.setHeader("Roll");
            _centerPanel.add(_pitchPidPanel);
        }
        else if (_userLevel == UserLevel.Advanced)
        {
            _centerPanel.add(_pitchPidPanel);
            _centerPanel.add(_stickScalingPanel);
            _centerPanel.add(_throttlePIDAdjustmentPanel);
        }
        _centerPanel.add(_resetDefaultButton);
        _centerPanel.add(_syncStatePanel);
    }
}
