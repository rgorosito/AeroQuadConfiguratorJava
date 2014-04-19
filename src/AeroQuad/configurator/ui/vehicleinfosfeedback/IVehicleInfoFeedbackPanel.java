package AeroQuad.configurator.ui.vehicleinfosfeedback;

public interface IVehicleInfoFeedbackPanel
{
    void setBoardType(String boardType);

    void setAccelDetected(boolean detected);

    void setGyroscopeDetected(boolean detected);

    void setBarometerDetected(boolean detected);

    void setMagDetected(boolean detected);

    void setNbChannel(int nbChannel);

    void setFlightConfig(String flightConfig);

    void setBaroVisible(boolean visible);

    void setMagVisible(boolean visible);
}
