package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel;

import AeroQuad.configurator.messagesdispatcher.AltitudeControlState;
import AeroQuad.configurator.messagesdispatcher.FlightMode;

public interface IOtherSensorsStatusPanel
{
    void setBaroEnabled(boolean enabled);

    void setBatteryMonitorEnabled(boolean enabled);

    void setMotorArmedState(boolean armed);

    void setVehicleAltitude(Float value);

    void setAltitudeControlState(AltitudeControlState enabled);

    void setFlightMode(FlightMode flightMode);

    void setVoltage(Float value);

    void setZVelocity(Float value);

    void setDisconected();
}
