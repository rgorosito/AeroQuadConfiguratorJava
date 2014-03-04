package AeroQuad.configurator.ui.mainpanel.tuning;

import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuningPanel extends JPanel implements ITuningPanel
{
    private final ButtonGroup _userLevelButtonGroup = new ButtonGroup();
    private final JToggleButton _beginnerButton = new JToggleButton(UserLevel.Beginner.name());
    private final JToggleButton _intermediateButton = new JToggleButton(UserLevel.Intermediate.name());
    private final JToggleButton _advancedButton = new JToggleButton(UserLevel.Advanced.name());

    private final ITuningPanelController _controller;

    private final GridLayout _gridLayout = new GridLayout(6,1);
    private JPanel _gpsPanel;
    private JPanel _altitudePanel;
    private JPanel _batteryMonitorPanel;


    public TuningPanel(final ITuningPanelController tuningPanelController)
    {
        _controller = tuningPanelController;

        setLayout(new BorderLayout());

        tuningPanelController.setPanel(this);

        init();

        bindUserLevelButtonAction();
    }

    private void init()
    {
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel topPanel = new JPanel(new BorderLayout());

        final JPanel resetEepromPanel = new JPanel(new BorderLayout());
        resetEepromPanel.setBorder(new LineBorder(Color.black,1));
        final JLabel resetEepromLabel = new JLabel("Reset EEPROM, All values will be reset to default and all calibration will need to be done");
        resetEepromPanel.add(resetEepromLabel,BorderLayout.WEST);
        final JButton resetEepromButton = new JButton("Reset EEPROM");
        resetEepromButton.setPreferredSize(new Dimension(200, 50));
        resetEepromPanel.add(resetEepromButton,BorderLayout.EAST);
        topPanel.add(resetEepromPanel,BorderLayout.NORTH);

        final JPanel buttonLevelPanel = new JPanel(new GridLayout(1,3));
        buttonLevelPanel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        buttonLevelPanel.add(_beginnerButton);
        _beginnerButton.setSelected(true);
        buttonLevelPanel.add(_intermediateButton);
        buttonLevelPanel.add(_advancedButton);
        topPanel.add(buttonLevelPanel,BorderLayout.SOUTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        _userLevelButtonGroup.add(_beginnerButton);
        _userLevelButtonGroup.add(_intermediateButton);
        _userLevelButtonGroup.add(_advancedButton);

        final JPanel centerPanel = new JPanel(_gridLayout);
        centerPanel.add(_controller.getAccroPanel());
        centerPanel.add(_controller.getAttitudePanel());
        centerPanel.add(_controller.getYawPanel());
        _altitudePanel = _controller.getAltitudePanel();
        centerPanel.add(_altitudePanel);
        _batteryMonitorPanel = _controller.getBatteryMonitorPanel();
        centerPanel.add(_batteryMonitorPanel);
        _gpsPanel = _controller.getGpsPanel();
        centerPanel.add(_gpsPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        final JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);


        resetEepromButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.resetEeprom();
            }
        });
    }

    private void bindUserLevelButtonAction()
    {
        _beginnerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.setUserLevel(UserLevel.Beginner);
            }
        });
        _intermediateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.setUserLevel(UserLevel.Intermediate);
            }
        });
        _advancedButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.setUserLevel(UserLevel.Advanced);
            }
        });
    }

    @Override
    public void setGpsPanelVisible(final boolean visible)
    {
        _gpsPanel.setVisible(visible);
    }

    @Override
    public void setBatteryMonitorVisible(final boolean visible)
    {
        _batteryMonitorPanel.setVisible(visible);
    }

    @Override
    public void setAltitudeHoldVisible(final boolean visible)
    {
        _altitudePanel.setVisible(visible);
    }
}
