package AeroQuad.configurator.ui.mainpanel.setup.accelcalibration;

public interface IAccelCalibrationPanelController
{
    static final int NB_ACCEL_SAMPLE_TO_READ = 50;

    static final float ONE_G = 9.80665F;

    void setActivated(boolean activated);

    void setPanel(IAccelCalibrationPanel panel);

    void nextButtonPressed();

    void cancelButtonPressed();

    void calibrateLevel();
}
