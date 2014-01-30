package AeroQuad.configurator.ui.mainpanel.tuning;

import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.*;
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
        final JPanel topPanel = new JPanel(new GridLayout(1,3));
        topPanel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        topPanel.add(_beginnerButton);
        _beginnerButton.setSelected(true);
        topPanel.add(_intermediateButton);
        topPanel.add(_advancedButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        _userLevelButtonGroup.add(_beginnerButton);
        _userLevelButtonGroup.add(_intermediateButton);
        _userLevelButtonGroup.add(_advancedButton);

        final JPanel centerPanel = new JPanel(_gridLayout);
        centerPanel.add(_controller.getAccroPanel());
        centerPanel.add(_controller.getAttitudePanel());
        centerPanel.add(_controller.getYawPanel());
        centerPanel.add(_controller.getAltitudePanel());
        centerPanel.add(_controller.getBatteryMonitorPanel());
        centerPanel.add(_controller.getGpsPanel());
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        final JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
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



}
