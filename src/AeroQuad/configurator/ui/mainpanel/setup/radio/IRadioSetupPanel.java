package AeroQuad.configurator.ui.mainpanel.setup.radio;

public interface IRadioSetupPanel
{
    static final String CHANNEL_DETECTION = "<html><center>1.<br>CHANNEL<br>DETECTION</center></html>";
    static final String CHANNEL_CALIBRATION = "<html><center>2.<br>CHANNEL<br>CALIBATION</center></html>";

    void loadCurrentPanel(String panelId);
}
