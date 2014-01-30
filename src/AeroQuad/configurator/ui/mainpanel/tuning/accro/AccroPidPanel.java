package AeroQuad.configurator.ui.mainpanel.tuning.accro;

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


public class AccroPidPanel extends JPanel implements IAccroPidPanel
{
    private final IAccroPidPanelController _controller;

    private final PidPanel _rollPidPanel = new PidPanel("Roll");
    private final PidPanel _pitchPidPanel = new PidPanel("Pitch");
    private final JButton _resetDefaultButton = new JButton("<HTML><CENTER>Reset<BR>Default</CENTER></HTML>");
    private final SingleParamConfigPanel _stickScalingPanel = new SingleParamConfigPanel("<HTML><CENTER>Stick<BR>Scaling</CENTER></HTML>");
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

        _centerPanel = new JPanel(new GridLayout(1,5));

        mainPanel.add(_centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);
    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        _userLevel = userLevel;
        updateCenterPanelFromUserLevel();
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
        }

        _centerPanel.add(_resetDefaultButton);
        _centerPanel.add(_syncStatePanel);
    }
}
