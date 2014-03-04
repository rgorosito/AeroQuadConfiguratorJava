package AeroQuad.configurator.ui.mainpanel.tuning;

import javax.swing.JPanel;

public interface ITuningPanelController
{
    void setPanel(ITuningPanel panel);
    void setUserLevel(UserLevel beginner);
    void setActivated(boolean activated);

    JPanel getAccroPanel();
    JPanel getAttitudePanel();
    JPanel getYawPanel();
    JPanel getAltitudePanel();
    JPanel getBatteryMonitorPanel();
    JPanel getGpsPanel();

    void resetEeprom();
}
