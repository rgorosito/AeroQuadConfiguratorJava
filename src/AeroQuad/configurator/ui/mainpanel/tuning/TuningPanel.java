package AeroQuad.configurator.ui.mainpanel.tuning;

import AeroQuad.configurator.ui.ConfiguratorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuningPanel extends JPanel implements ITuningPanel
{
    private final CardLayout _cardLayout = new CardLayout();
    private final JPanel _cardLayoutPanel = new JPanel(_cardLayout);

    private final ButtonGroup _pidMenuButtonGroup = new ButtonGroup();
    private final JToggleButton _accroPidButton = new JToggleButton(ACCRO);
    private final JToggleButton _attitudePidButton = new JToggleButton(ATTITUDE);
    private final JToggleButton _yawPidButton = new JToggleButton(YAW);
    private final JToggleButton _altitudePidButton = new JToggleButton(ALTITUDE);
    private final JToggleButton _batteryPidButton = new JToggleButton(BATTERY);
    private final JToggleButton _gpsPidButton = new JToggleButton(GPS);

    private final ButtonGroup _userLevelButtonGroup = new ButtonGroup();
    private final JToggleButton _beginnerButton = new JToggleButton(BEGINNER);
    private final JToggleButton _intermediateButton = new JToggleButton(INTERMEDIATE);
    private final JToggleButton _advancedButton = new JToggleButton(ADVANCED);

    private final JButton _resetDefault = new JButton(RESET_DEFAULT);
    private final ITuningPanelController _controller;


    public TuningPanel(final ITuningPanelController tuningPanelController,
                       final ConfiguratorPanel accroPanel,
                       final ConfiguratorPanel attitudePanel,
                       final ConfiguratorPanel yawPanel,
                       final ConfiguratorPanel altitudePanel,
                       final ConfiguratorPanel batteryMonitorPanel,
                       final ConfiguratorPanel gpsPanel)
    {
        _controller = tuningPanelController;

        setLayout(new BorderLayout());

        tuningPanelController.setPanel(this);

        init(accroPanel, attitudePanel, yawPanel, altitudePanel, batteryMonitorPanel, gpsPanel);

        bindUserLevelButtonAction();

        bindTuningMenuButtonAction(accroPanel, attitudePanel, yawPanel, altitudePanel, batteryMonitorPanel, gpsPanel);
    }

    private void init(final ConfiguratorPanel accroPanel,
                      final ConfiguratorPanel attitudePanel,
                      final ConfiguratorPanel yawPanel,
                      final ConfiguratorPanel altitudePanel,
                      final ConfiguratorPanel batteryMonitorPanel,
                      final ConfiguratorPanel gpsPanel)
    {
        final JPanel topPanel = new JPanel(new GridLayout(1,3));
        topPanel.add(_beginnerButton);
        _beginnerButton.setSelected(true);
        topPanel.add(_intermediateButton);
        topPanel.add(_advancedButton);
        add(topPanel, BorderLayout.NORTH);
        _userLevelButtonGroup.add(_beginnerButton);
        _userLevelButtonGroup.add(_intermediateButton);
        _userLevelButtonGroup.add(_advancedButton);

        final JPanel leftPanel = new JPanel(new GridLayout(6,1));
        leftPanel.add(_accroPidButton);
        _accroPidButton.setSelected(true);
        leftPanel.add(_attitudePidButton);
        leftPanel.add(_yawPidButton);
        leftPanel.add(_altitudePidButton);
        _altitudePidButton.setVisible(false);
        leftPanel.add(_batteryPidButton);
        _batteryPidButton.setVisible(false);
        leftPanel.add(_gpsPidButton);
        _gpsPidButton.setVisible(false);
        add(leftPanel, BorderLayout.WEST);
        _pidMenuButtonGroup.add(_accroPidButton);
        _pidMenuButtonGroup.add(_attitudePidButton);
        _pidMenuButtonGroup.add(_yawPidButton);
        _pidMenuButtonGroup.add(_altitudePidButton);
        _pidMenuButtonGroup.add(_batteryPidButton);
        _pidMenuButtonGroup.add(_gpsPidButton);

        _cardLayoutPanel.add(accroPanel, ACCRO);
        _cardLayoutPanel.add(attitudePanel, ATTITUDE);
        _cardLayoutPanel.add(yawPanel, YAW);
        _cardLayoutPanel.add(altitudePanel, ALTITUDE);
        _cardLayoutPanel.add(batteryMonitorPanel, BATTERY);
        _cardLayoutPanel.add(gpsPanel, GPS);
        add(_cardLayoutPanel,BorderLayout.CENTER);

        _cardLayout.show(_cardLayoutPanel, ACCRO);

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


    private void bindTuningMenuButtonAction(final ConfiguratorPanel accroPanel,
                                            final ConfiguratorPanel attitudePanel,
                                            final ConfiguratorPanel yawPanel,
                                            final ConfiguratorPanel altitudePanel,
                                            final ConfiguratorPanel batteryMonitorPanel,
                                            final ConfiguratorPanel gpsPanel)
    {
        _accroPidButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                accroPanel.getController().setActivated(true);
                attitudePanel.getController().setActivated(false);
                yawPanel.getController().setActivated(false);
                altitudePanel.getController().setActivated(false);
                accroPanel.getController().setActivated(false);
                batteryMonitorPanel.getController().setActivated(false);
                gpsPanel.getController().setActivated(false);
                _cardLayout.show(_cardLayoutPanel, ACCRO);
            }
        });


        _attitudePidButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                accroPanel.getController().setActivated(false);
                attitudePanel.getController().setActivated(true);
                yawPanel.getController().setActivated(false);
                altitudePanel.getController().setActivated(false);
                accroPanel.getController().setActivated(false);
                batteryMonitorPanel.getController().setActivated(false);
                gpsPanel.getController().setActivated(false);
                _cardLayout.show(_cardLayoutPanel, ACCRO);
            }
        });

        _yawPidButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                accroPanel.getController().setActivated(false);
                attitudePanel.getController().setActivated(false);
                yawPanel.getController().setActivated(true);
                altitudePanel.getController().setActivated(false);
                accroPanel.getController().setActivated(false);
                batteryMonitorPanel.getController().setActivated(false);
                gpsPanel.getController().setActivated(false);
                _cardLayout.show(_cardLayoutPanel, ACCRO);
            }
        });

        _altitudePidButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                accroPanel.getController().setActivated(false);
                attitudePanel.getController().setActivated(false);
                yawPanel.getController().setActivated(false);
                altitudePanel.getController().setActivated(true);
                accroPanel.getController().setActivated(false);
                batteryMonitorPanel.getController().setActivated(false);
                gpsPanel.getController().setActivated(false);
                _cardLayout.show(_cardLayoutPanel, ACCRO);
            }
        });

        _batteryPidButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                accroPanel.getController().setActivated(false);
                attitudePanel.getController().setActivated(false);
                yawPanel.getController().setActivated(false);
                altitudePanel.getController().setActivated(false);
                accroPanel.getController().setActivated(false);
                batteryMonitorPanel.getController().setActivated(true);
                gpsPanel.getController().setActivated(false);
                _cardLayout.show(_cardLayoutPanel, ACCRO);
            }
        });

        _gpsPidButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                accroPanel.getController().setActivated(false);
                attitudePanel.getController().setActivated(false);
                yawPanel.getController().setActivated(false);
                altitudePanel.getController().setActivated(false);
                accroPanel.getController().setActivated(false);
                batteryMonitorPanel.getController().setActivated(false);
                gpsPanel.getController().setActivated(true);
                _cardLayout.show(_cardLayoutPanel, ACCRO);
            }
        });






    }
}
