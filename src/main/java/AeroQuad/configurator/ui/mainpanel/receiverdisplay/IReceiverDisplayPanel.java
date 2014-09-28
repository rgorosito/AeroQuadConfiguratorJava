package AeroQuad.configurator.ui.mainpanel.receiverdisplay;

public interface IReceiverDisplayPanel
{
    void setRollValue(String value);

    void setPitchValue(String value);

    void setYawValue(String value);

    void setThrottleValue(String value);

    void setModeValue(String value);

    void setAux1Value(String value);

    void setAux2Value(String value);

    void setAux3Value(String value);

    void setDisconnected();

    void setAux1Visible(boolean visible);
    void setAux2Visible(boolean visible);
    void setAux3Visible(boolean visible);
}
