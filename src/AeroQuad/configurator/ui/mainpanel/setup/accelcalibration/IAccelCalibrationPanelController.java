package AeroQuad.configurator.ui.mainpanel.setup.accelcalibration;

public interface IAccelCalibrationPanelController
{
    static final int NB_ACCEL_SAMPLE_TO_READ = 25;

    void setActivated(boolean activated);

    void setPanel(IAccelCalibrationPanel panel);

    void nextButtonPressed();

    void cancelButtonPressed();
}
