package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel;


import AeroQuad.configurator.messagesdispatcher.FlightMode;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class OtherSensorsStatusPanel extends JPanel implements IOtherSensorsStatusPanel
{
    private final JLabel _motorStatusLabel = new JLabel();
    private final JLabel _flightModeLabel = new JLabel();
    private final JLabel _batteryVoltageLabel = new JLabel();
    private final JLabel _altitudeHoldStateLabel = new JLabel();
    private final JLabel _currentVehicleAltitudeLabel = new JLabel();
    private final JLabel _currentZVelocityLabel = new JLabel();

    private final JPanel _altitudeHoldPanel = new JPanel(new GridLayout(1,2));
    private final JPanel _currentAltitudePanel = new JPanel(new GridLayout(1,2));
    private final JPanel _currenZVelocityPanel = new JPanel(new GridLayout(1,2));
    private final JPanel _currenBatteryVoltagePanel = new JPanel(new GridLayout(1,2));

    public OtherSensorsStatusPanel(final IOtherSensorsStatusPanelController controller)
    {
        controller.setPanel(this);

        setPreferredSize(new Dimension(0, 100));

        init();
    }

    private void init()
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new LineBorder(Color.BLACK,3));

        _motorStatusLabel.setOpaque(true);
        _motorStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motorStatusLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _flightModeLabel.setOpaque(true);
        _flightModeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _flightModeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _batteryVoltageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _batteryVoltageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _altitudeHoldStateLabel.setOpaque(true);
        _altitudeHoldStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _altitudeHoldStateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _currentVehicleAltitudeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _currentVehicleAltitudeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _currentZVelocityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _currentZVelocityLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        final JPanel motorArmedPanel = new JPanel(new GridLayout(1,2));
        final JLabel motorLabel = new JLabel("Motor");
        motorLabel.setOpaque(true);
        motorLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        motorArmedPanel.add(motorLabel);
        motorArmedPanel.add(_motorStatusLabel);
        add(motorArmedPanel);

        final JPanel flightModePanel = new JPanel(new GridLayout(1,2));
        final JLabel flightModeLabel = new JLabel("Flight Mode");
        flightModeLabel.setOpaque(true);
        flightModeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        flightModePanel.add(flightModeLabel);
        flightModePanel.add(_flightModeLabel);
        add(flightModePanel);


        final JLabel altitudeHoldLabel = new JLabel("Altitude Hold");
        altitudeHoldLabel.setOpaque(true);
        altitudeHoldLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _altitudeHoldPanel.add(altitudeHoldLabel);
        _altitudeHoldPanel.add(_altitudeHoldStateLabel);

        final JLabel currentAltitudeLabel = new JLabel("Current Altitude");
        currentAltitudeLabel.setOpaque(true);
        currentAltitudeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _currentAltitudePanel.add(currentAltitudeLabel);
        _currentAltitudePanel.add(_currentVehicleAltitudeLabel);

        final JLabel currentZVelocityLabel = new JLabel("Z Velocity");
        currentZVelocityLabel.setOpaque(true);
        currentZVelocityLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _currenZVelocityPanel.add(currentZVelocityLabel);
        _currenZVelocityPanel.add(_currentZVelocityLabel);

        final JLabel batteryLabel = new JLabel("Battery");
        batteryLabel.setOpaque(true);
        batteryLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _currenBatteryVoltagePanel.add(batteryLabel);
        _currenBatteryVoltagePanel.add(_batteryVoltageLabel);


    }

    @Override
    public void setBaroEnabled(final boolean enabled)
    {
        if (enabled)
        {
            add(_currenZVelocityPanel);
            add(_currentAltitudePanel);
            add(_currenZVelocityPanel);
        }
    }

    @Override
    public void setBatteryMonitorEnabled(final boolean enabled)
    {
        if (enabled)
        {
            add(_currenBatteryVoltagePanel);
        }
    }

    @Override
    public void setMotorArmedState(final boolean armed)
    {
        if (armed)
        {
            _motorStatusLabel.setText("Armed");
            _motorStatusLabel.setBackground(Color.GREEN);
        }
        else
        {
            _motorStatusLabel.setText("Disarmed");
            _motorStatusLabel.setBackground(Color.GRAY);
        }
    }

    @Override
    public void setVehicleAltitude(final Float value)
    {
        _currentVehicleAltitudeLabel.setText(value.toString());
    }

    @Override
    public void setAltitudeHoldState(final boolean enabled)
    {
        if (enabled)
        {
            _altitudeHoldStateLabel.setText("Enabled");
            _altitudeHoldStateLabel.setBackground(Color.GREEN);
        }
        else
        {
            _altitudeHoldStateLabel.setText("Disabled");
            _altitudeHoldStateLabel.setBackground(Color.GRAY);
        }

    }

    @Override
    public void setFlightMode(final FlightMode flightMode)
    {
        _flightModeLabel.setText(flightMode.toString());
        if (flightMode.equals(FlightMode.Acrobatic))
        {
            _flightModeLabel.setBackground(Color.blue);
        }
        else
        {
            _flightModeLabel.setBackground(Color.GREEN);
        }
    }

    @Override
    public void setVoltage(final Float value)
    {
        _batteryVoltageLabel.setText(value.toString());
    }

    @Override
    public void setZVelocity(final Float value)
    {
        _currentZVelocityLabel.setText(value.toString());
    }

    @Override
    public void setDisconected()
    {
        remove(_currenZVelocityPanel);
        remove(_currentAltitudePanel);
        remove(_currenZVelocityPanel);
        remove(_currenBatteryVoltagePanel);
    }
}
