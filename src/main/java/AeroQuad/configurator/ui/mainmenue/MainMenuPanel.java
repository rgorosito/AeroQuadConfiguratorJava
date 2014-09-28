package AeroQuad.configurator.ui.mainmenue;


import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements IMainMenuPanel
{
    private final JToggleButton _setupButton = new JToggleButton(SETUP);
    private final JToggleButton _monitoringButton = new JToggleButton(MONITORING);
    private final JToggleButton _tuningButton = new JToggleButton(TUNING);

    private final IMainMenuController _controller;

    private final ButtonGroup _buttonGroup = new ButtonGroup();

    public MainMenuPanel(final IMainMenuController controller)
    {
        _controller = controller;
        _controller.setPanel(this);
        setLayout(new GridLayout(4, 0));
        init();
        setConnected(false);
    }

    private void init()
    {
        add(_setupButton);
        add(_monitoringButton);
        add(_tuningButton);
        add(new JLabel());

        _setupButton.setEnabled(false);
        _monitoringButton.setEnabled(false);
        _tuningButton.setEnabled(false);

        _monitoringButton.setOpaque(true);
        _setupButton.setOpaque(true);
        _tuningButton.setOpaque(true);


        _monitoringButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _controller.processMonitoringButtonPressed();
            }
        });

        _setupButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _controller.processSetupButtonPressed();
            }
        });

        _tuningButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _controller.processTuningButtonPressed();
            }
        });

        _buttonGroup.add(_setupButton);
        _buttonGroup.add(_monitoringButton);
        _buttonGroup.add(_tuningButton);

        //setBorder(new LineBorder(Color.BLACK,3));
    }

    @Override
    public void setConnected(boolean connected)
    {
        _setupButton.setEnabled(connected);
        _monitoringButton.setEnabled(connected);
        _tuningButton.setEnabled(connected);

        if (connected)
        {
            _monitoringButton.doClick();
        }
    }

    @Override
    public void setButtonsEnabled(final boolean enabled)
    {
        _setupButton.setEnabled(enabled);
        _monitoringButton.setEnabled(enabled);
        _tuningButton.setEnabled(enabled);
    }
}
