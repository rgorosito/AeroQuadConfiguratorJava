package AeroQuad.configurator.ui.mainpanel.setup;

public interface ISetupPanel
{
    static final String VEHICLE_CONFIG= "<html><center>1.<br>VEHICLE<br>CONFIG</center></html>";
    static final String ESC_CALIBRATION = "<html><center>2.<br>ESC<br>CALIBATION</center></html>";
    static final String ACCEL_CALIBRATION = "<html><center>3.<br>ACCEL<br>CALIBATION</center></html>";
    static final String MAG_CALIBRATION = "<html><center>5.<br>MAG<br>CALIBATION</center></html>";
    static final String RADIO_CALIBRATION = "<html><center>4.<br>TRANSMITTER</center></html>";



    void showPanel(String panelId);

    void setMagBalenEnable(boolean enable);

    void performEscCalibrationSelection();
}
