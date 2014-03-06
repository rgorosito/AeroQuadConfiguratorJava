package AeroQuad.configurator.ui.mainpanel.setup.magcalibration;

public interface IMagCalibrationPanelController
{
    void setActivated(boolean activated);

    void setPanel(IMagCalibrationPanel panel);

    void buttonPressed();

    void cancelCalibration();
}
