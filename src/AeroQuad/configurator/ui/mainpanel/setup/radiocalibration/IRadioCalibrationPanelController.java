package AeroQuad.configurator.ui.mainpanel.setup.radiocalibration;

public interface IRadioCalibrationPanelController
{
    void setActivated(boolean activated);

    void setPanel(IRadioCalibrationPanel panel);

    void buttonPressed();

    void cancel();
}
