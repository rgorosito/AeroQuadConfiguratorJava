package AeroQuad.configurator.ui.mainpanel.setup.eepromreset;

public interface IEepromResetController
{
    void setPanel(IEepromResetPanel panel);

    void setActivated(boolean activated);

    void resetEepromButtonPressed();
}
