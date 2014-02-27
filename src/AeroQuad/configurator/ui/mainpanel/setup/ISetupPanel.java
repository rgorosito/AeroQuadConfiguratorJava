package AeroQuad.configurator.ui.mainpanel.setup;

public interface ISetupPanel
{
    static final String ACCEL_CALIBRATION = "<html><center>ACCEL<br>CALIBATION</center></html>";
    static final String MAG_CALIBRATION = "<html><center>MAG<br>CALIBATION</center></html>";
    static final String RADIO_CALIBRATION = "<html><center>TRANSMITTER<br>CALIBATION</center></html>";
    static final String ESC_CALIBRATION = "<html><center>ESC<br>CALIBATION</center></html>";

    void showPanel(String panelId);

    void setMagBalenEnable(boolean enable);
}
