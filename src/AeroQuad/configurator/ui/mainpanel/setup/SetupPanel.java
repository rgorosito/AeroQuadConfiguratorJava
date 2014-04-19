package AeroQuad.configurator.ui.mainpanel.setup;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupPanel extends JPanel implements ISetupPanel
{
    private final CardLayout _cardLayout = new CardLayout();
    private final JPanel _cardLayoutPanel = new JPanel(_cardLayout);

    private final JToggleButton _vehicleConfigButton = new JToggleButton(VEHICLE_CONFIG);
    private final JToggleButton _accelCalibrationButton = new JToggleButton(ACCEL_CALIBRATION);
    private final JToggleButton _radioCalibrationButton = new JToggleButton(RADIO_CALIBRATION);
    private final JToggleButton _magCalibrationButton = new JToggleButton(MAG_CALIBRATION);
    private final JToggleButton _escCalibrationButton = new JToggleButton(ESC_CALIBRATION);

    private final ButtonGroup _buttonGroup = new ButtonGroup();
    private final ISetupPanelController _controller;

    public SetupPanel(final ISetupPanelController controller)
    {
        _controller = controller;

        setLayout(new BorderLayout());

        _controller.setPanel(this);

        initButtonPanel();

        initSubPanels();
    }

    private void initButtonPanel()
    {
        final JPanel monitoringButtonPanel = new JPanel(new GridLayout(5,1));
        monitoringButtonPanel.add(_vehicleConfigButton);
        monitoringButtonPanel.add(_escCalibrationButton);
        monitoringButtonPanel.add(_radioCalibrationButton);
        monitoringButtonPanel.add(_accelCalibrationButton);
        monitoringButtonPanel.add(_magCalibrationButton);
        add(monitoringButtonPanel,BorderLayout.WEST);
        _buttonGroup.add(_vehicleConfigButton);
        _buttonGroup.add(_accelCalibrationButton);
        _buttonGroup.add(_radioCalibrationButton);
        _buttonGroup.add(_escCalibrationButton);
        _buttonGroup.add(_magCalibrationButton);
        _escCalibrationButton.doClick();

        _magCalibrationButton.setVisible(false);

        _vehicleConfigButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.vehicleConfigButtonPressed();
            }
        });
        _accelCalibrationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.accelCalibrationButtonPressed();
            }
        });
        _radioCalibrationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.radioCalibrationButtonPressed();
            }
        });
        _escCalibrationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.escCalibrationButtonPressed();
            }
        });
        _magCalibrationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.magCalibrationButtonPressed();
            }
        });
    }

    private void initSubPanels()
    {
        _cardLayoutPanel.add(_controller.getVehicleSetupPanel(), VEHICLE_CONFIG);
        _cardLayoutPanel.add(_controller.getAccelCalibrationPanel(), ACCEL_CALIBRATION);
        _cardLayoutPanel.add(_controller.getRadioCalibrationPanel(), RADIO_CALIBRATION);
        _cardLayoutPanel.add(_controller.getEscCalibrationPanel(), ESC_CALIBRATION);
        _cardLayoutPanel.add(_controller.getMagCalibrationPanel(), MAG_CALIBRATION);
        add(_cardLayoutPanel,BorderLayout.CENTER);
    }

    @Override
    public void showPanel(final String panelId)
    {
        _cardLayout.show(_cardLayoutPanel, panelId);
    }

    @Override
    public void setMagBalenEnable(final boolean enable)
    {
        _magCalibrationButton.setVisible(enable);
    }

    @Override
    public void performVehicleSetupSelection()
    {
        _vehicleConfigButton.doClick();
    }
}
