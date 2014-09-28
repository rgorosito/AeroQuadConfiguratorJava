package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.gpsstatepanel;


import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

@SuppressWarnings("serial")
public class GpsStatusPanel extends JPanel implements IGpsStatusPanel
{
    private final IGpsStatusPanelController _controller;
    private JLabel _stateLabel = new JLabel("");
    private JLabel _nbSatsLabel = new JLabel("");
    private JLabel _speedLabel = new JLabel("");
    private JLabel _altitudeLabel = new JLabel("");
    private JLabel _headingLabel = new JLabel("");
    private JLabel _latitudeLabel = new JLabel("");
    private JLabel _longitudeLabel = new JLabel("");
    private JLabel _distanceToWaypointLabel = new JLabel("");
    private JLabel _angleToWaypointLabel = new JLabel("");



    public GpsStatusPanel(final IGpsStatusPanelController controller)
    {
        _controller = controller;
        _controller.setPanel(this);

        init();
    }

    private void init()
    {
        _stateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _stateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _nbSatsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _nbSatsLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _speedLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _altitudeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _altitudeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _headingLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _latitudeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _latitudeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _longitudeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _longitudeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _distanceToWaypointLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _distanceToWaypointLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _angleToWaypointLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _angleToWaypointLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        setBorder(new LineBorder(Color.black, 3));
        setPreferredSize(new Dimension(250, 0));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        final JPanel statePanel = new JPanel(new GridLayout(1, 2));
        final JLabel stateLabel = new JLabel(" Gps State");
        stateLabel.setOpaque(true);
        stateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        statePanel.add(stateLabel);
        statePanel.add(_stateLabel);
        _stateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statePanel);

        final JPanel gpsSatsCountPanel = new JPanel(new GridLayout(1, 2));
        final JLabel gpsSatsLabel = new JLabel(" Nb Sats");
        gpsSatsLabel.setOpaque(true);
        gpsSatsLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        gpsSatsCountPanel.add(gpsSatsLabel);
        gpsSatsCountPanel.add(_nbSatsLabel);
        _nbSatsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gpsSatsCountPanel);

        final JPanel gpsSpeedPanel = new JPanel(new GridLayout(1, 2));
        final JLabel gpsSpeedLabel = new JLabel(" Speed");
        gpsSpeedLabel.setOpaque(true);
        gpsSpeedLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        gpsSpeedPanel.add(gpsSpeedLabel);
        gpsSpeedPanel.add(_speedLabel);
        _speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gpsSpeedPanel);

        final JPanel gpsAltitudePanel = new JPanel(new GridLayout(1, 2));
        final JLabel gpsAltitudeLabel = new JLabel(" Altitude");
        gpsAltitudeLabel.setOpaque(true);
        gpsAltitudeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        gpsAltitudePanel.add(gpsAltitudeLabel);
        gpsAltitudePanel.add(_altitudeLabel);
        _altitudeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gpsAltitudePanel);

        final JPanel gpsHeadingPanel = new JPanel(new GridLayout(1, 2));
        final JLabel gpsHeadingLabel = new JLabel(" Heading");
        gpsHeadingLabel.setOpaque(true);
        gpsHeadingLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        gpsHeadingPanel.add(gpsHeadingLabel);
        gpsHeadingPanel.add(_headingLabel);
        _headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gpsHeadingPanel);

        final JPanel gpsLatitudePanel = new JPanel(new GridLayout(1, 2));
        final JLabel gpsLatitudeLabel = new JLabel(" Latitude");
        gpsLatitudeLabel.setOpaque(true);
        gpsLatitudeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        gpsLatitudePanel.add(gpsLatitudeLabel);
        gpsLatitudePanel.add(_latitudeLabel);
        _latitudeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gpsLatitudePanel);

        final JPanel gpsLongitudePanel = new JPanel(new GridLayout(1, 2));
        final JLabel gpsLongitudeLabel = new JLabel(" Latitude");
        gpsLongitudeLabel.setOpaque(true);
        gpsLongitudeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        gpsLongitudePanel.add(gpsLongitudeLabel);
        gpsLongitudePanel.add(_longitudeLabel);
        _longitudeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gpsLongitudePanel);

        final JPanel distanceToWaypointPanel = new JPanel(new GridLayout(1, 2));
        final JLabel distanceToWaypointLabel = new JLabel(" Dst To WP");
        distanceToWaypointLabel.setOpaque(true);
        distanceToWaypointLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        distanceToWaypointPanel.add(distanceToWaypointLabel);
        distanceToWaypointPanel.add(_distanceToWaypointLabel);
        _distanceToWaypointLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(distanceToWaypointPanel);

        final JPanel angleToWaypointPanel = new JPanel(new GridLayout(1, 2));
        final JLabel angleToWaypointLabel = new JLabel(" Angle to WP");
        angleToWaypointLabel.setOpaque(true);
        angleToWaypointLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        angleToWaypointPanel.add(angleToWaypointLabel);
        angleToWaypointPanel.add(_angleToWaypointLabel);
        _angleToWaypointLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(angleToWaypointPanel);
    }

    @Override
    public void setGpsState(final String gpsState)
    {
        _stateLabel.setText(gpsState);
    }

    @Override
    public void setNbSats(final String nbSats)
    {
        _nbSatsLabel.setText(nbSats);
    }

    @Override
    public void setSpeed(final String speed)
    {
        _speedLabel.setText(speed);
    }

    @Override
    public void setAltitude(final String altitude)
    {
        _altitudeLabel.setText(altitude);
    }

    @Override
    public void setHeading(final String heading)
    {
        _headingLabel.setText(heading);
    }

    @Override
    public void setLatitude(final String latitude)
    {
        _latitudeLabel.setText(latitude);
    }

    @Override
    public void setLongitude(final String longitude)
    {
        _longitudeLabel.setText(longitude);
    }

    @Override
    public void setDistanceToWayPoint(final String distanceToWayPoint)
    {
        _distanceToWaypointLabel.setText(distanceToWayPoint);
    }

    @Override
    public void setAngleToWaypoint(final String angleToWayPoint)
    {
        _angleToWaypointLabel.setText(angleToWayPoint);
    }
}
