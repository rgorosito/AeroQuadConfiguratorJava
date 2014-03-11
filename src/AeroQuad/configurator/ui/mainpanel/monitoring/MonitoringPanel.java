package AeroQuad.configurator.ui.mainpanel.monitoring;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitoringPanel extends JPanel implements IMonitoringPanel
{
    private final CardLayout _cardLayout = new CardLayout();
    private final JPanel _cardLayoutPanel = new JPanel(_cardLayout);

    private final JToggleButton _sensorsMonitoringButton = new JToggleButton(SENSORS);
    private final JToggleButton _motorsMonitoringButton = new JToggleButton(MOTORS);
    private final JToggleButton _vehicleMonitoringButton = new JToggleButton(VEHICLE);

    private final ButtonGroup _buttonGroup = new ButtonGroup();
    private final IMonitoringPanelController _controller;

    public MonitoringPanel(final IMonitoringPanelController monitoringPanelController)
    {
        _controller = monitoringPanelController;

        setLayout(new BorderLayout());

        monitoringPanelController.setPanel(this);

        init();
    }

    private void init()
    {
        final JPanel monitoringButtonPanel = new JPanel(new GridLayout(4,1));
        monitoringButtonPanel.add(_sensorsMonitoringButton);
        monitoringButtonPanel.add(_motorsMonitoringButton);
        monitoringButtonPanel.add(_vehicleMonitoringButton);
        add(monitoringButtonPanel,BorderLayout.WEST);
        _buttonGroup.add(_sensorsMonitoringButton);
        _buttonGroup.add(_motorsMonitoringButton);
        _buttonGroup.add(_vehicleMonitoringButton);

        _cardLayoutPanel.add(_controller.getVehicleStatusPanel(), VEHICLE);
        _cardLayoutPanel.add(_controller.getSensorsMonitoringPanel(), SENSORS);
        _cardLayoutPanel.add(_controller.getMotorCommandPanel(), MOTORS);
        add(_cardLayoutPanel,BorderLayout.CENTER);

        _sensorsMonitoringButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.sensorsMonitoringButtonPressed();
            }
        });
        _motorsMonitoringButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.motorsMonitoringButtonPressed();
            }
        });
        _vehicleMonitoringButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.vehicleMonitoringButtonPressed();
            }
        });

    }

    @Override
    public void setConnectedState(final boolean isConnected)
    {
        if (isConnected)
        {
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    _vehicleMonitoringButton.doClick();
                }
            });
        }
    }

    @Override
    public void showPanel(final String panelId)
    {
        _cardLayout.show(_cardLayoutPanel, panelId);
    }

    @Override
    public void performVehicleMonitoringSelection()
    {
        _vehicleMonitoringButton.doClick();
    }
}
