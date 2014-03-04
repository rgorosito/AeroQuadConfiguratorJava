package AeroQuad.configurator.ui.mainpanel.setup.magcalibration;

public interface IMagCalibrationPanel
{
    void setXMinValue(String value);

    void setXMaxValue(String value);

    void setXSliderValue(int value);

    void setYMinValue(String value);

    void setYMaxValue(String value);

    void setYSliderValue(int value);

    void setZMinValue(String value);

    void setZMaxValue(String value);

    void setZSliderValue(int value);

    void setButtonText(String text);
}
