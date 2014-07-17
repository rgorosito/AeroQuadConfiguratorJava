package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.gpsstatepanel;

public interface IGpsStatusPanel
{
    void setGpsState(String gpsState);

    void setNbSats(String nbSats);

    void setSpeed(String speed);

    void setAltitude(String altitude);

    void setHeading(String heading);

    void setLatitude(String latitude);

    void setLongitude(String longitude);

    void setDistanceToWayPoint(String distanceToWayPoint);

    void setAngleToWaypoint(String angleToWayPoint);
}
