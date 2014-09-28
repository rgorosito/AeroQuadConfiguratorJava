package AeroQuad.configurator.ui.mainpanel.setup;

import javax.swing.JPanel;

public interface ISetupPanelController
{

    void setPanel(ISetupPanel panel);

    void accelCalibrationButtonPressed();

    void radioCalibrationButtonPressed();

    void escCalibrationButtonPressed();

    void magCalibrationButtonPressed();

    JPanel getAccelCalibrationPanel();

    JPanel getRadioCalibrationPanel();

    JPanel getEscCalibrationPanel();

    JPanel getMagCalibrationPanel();

    void setActivated(boolean activated);

    void vehicleConfigButtonPressed();

    JPanel getVehicleSetupPanel();

    void resetEepromButtonPressed();

    JPanel getEepromResetPanel();
}
