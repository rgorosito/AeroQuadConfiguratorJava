package AeroQuad.configurator.ui.mainpanel.tuning;

import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuningPanel extends JPanel implements ITuningPanel
{
    private final ButtonGroup _userLevelButtonGroup = new ButtonGroup();
    private final JToggleButton _beginnerButton = new JToggleButton(BEGINNER);
    private final JToggleButton _intermediateButton = new JToggleButton(INTERMEDIATE);
    private final JToggleButton _advancedButton = new JToggleButton(ADVANCED);

    private final JButton _resetDefault = new JButton(RESET_DEFAULT);
    private final ITuningPanelController _controller;

    private final GridLayout _gridLayout = new GridLayout(3,1);


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
        final JPanel topPanel = new JPanel(new GridLayout(1,7));
        topPanel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        topPanel.add(_beginnerButton);
        _beginnerButton.setSelected(true);
        topPanel.add(_intermediateButton);
        topPanel.add(_advancedButton);
        add(topPanel, BorderLayout.NORTH);
        _userLevelButtonGroup.add(_beginnerButton);
        _userLevelButtonGroup.add(_intermediateButton);
        _userLevelButtonGroup.add(_advancedButton);

        final JPanel centerPanel = new JPanel(_gridLayout);
        centerPanel.add(_controller.getAccroPanel());
        centerPanel.add(_controller.getAttitudePanel());
        centerPanel.add(_controller.getYawPanel());
        add(centerPanel, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new GridLayout(1,5));
        bottomPanel.add(new JLabel());
        bottomPanel.add(new JLabel());
        bottomPanel.add(new JLabel());
        bottomPanel.add(new JLabel());
        bottomPanel.add(_resetDefault);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void bindUserLevelButtonAction()
    {
        _beginnerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.setUserLevel(BEGINNER);
            }
        });
        _intermediateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.setUserLevel(INTERMEDIATE);
            }
        });
        _advancedButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.setUserLevel(ADVANCED);
            }
        });
    }



}
