package AeroQuad.configurator.ui.vehicleinfosfeedback;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;

public class VehicleInfoFeedbackPanel extends JPanel implements IVehicleInfoFeedbackPanel
{
    private final JLabel _boardType = new JLabel("CONNECTING");
    private final JLabel _gyroLabel = new JLabel("GYRO");
    private final JLabel _accelLabel = new JLabel("ACCEL");
    private final JLabel _baroLabel = new JLabel("BARO");
    private final JLabel _magLabel = new JLabel("MAG");
    private final JLabel _nbChannelLabel = new JLabel("Nb Channels");
    private final JLabel _flightConfig = new JLabel("");


    public VehicleInfoFeedbackPanel(final IVehicleInfoFeedbackController vehicleInfoFeedbackController)
    {
        vehicleInfoFeedbackController.setPanel(this);

//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new GridLayout(1,7));

        _boardType.setBorder(BorderFactory.createLineBorder(Color.black));
        _boardType.setHorizontalAlignment(SwingConstants.CENTER);
        add(_boardType);

        _gyroLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _gyroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _gyroLabel.setOpaque(true);
        _gyroLabel.setBackground(Color.red);
        add(_gyroLabel);

        _accelLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _accelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _accelLabel.setOpaque(true);
        _accelLabel.setBackground(Color.red);
        add(_accelLabel);

        _baroLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _baroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _baroLabel.setOpaque(true);
        _baroLabel.setBackground(Color.red);
        add(_baroLabel);

        _magLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _magLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _magLabel.setOpaque(true);
        _magLabel.setBackground(Color.red);
        add(_magLabel);

        _nbChannelLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _nbChannelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(_nbChannelLabel);

        _flightConfig.setBorder(BorderFactory.createLineBorder(Color.black));
        _flightConfig.setHorizontalAlignment(SwingConstants.CENTER);
        add(_flightConfig);

        setBaroVisible(false);
        setMagVisible(false);
    }

    @Override
    public void setBoardType(final String boardType)
    {
        _boardType.setText(boardType);
    }

    @Override
    public void setAccelDetected(final boolean detected)
    {
        _accelLabel.setBackground(detected ? Color.green : Color.red);
    }

    @Override
    public void setGyroscopeDetected(final boolean detected)
    {
        _gyroLabel.setBackground(detected ? Color.green : Color.red);
    }

    @Override
    public void setBarometerDetected(final boolean detected)
    {
        _baroLabel.setBackground(detected ? Color.green : Color.red);
    }

    @Override
    public void setMagDetected(final boolean detected)
    {
        _magLabel.setBackground(detected ? Color.green : Color.red);
    }

    @Override
    public void setNbChannel(final int nbChannel)
    {
        _nbChannelLabel.setText("Nb Channel: " + Integer.toString(nbChannel));
    }

    @Override
    public void setFlightConfig(final String flightConfig)
    {
        _flightConfig.setText(flightConfig);
    }

    @Override
    public void setBaroVisible(final boolean visible)
    {
        _baroLabel.setVisible(visible);
    }

    @Override
    public void setMagVisible(final boolean visible)
    {
        _magLabel.setVisible(visible);
    }
}
