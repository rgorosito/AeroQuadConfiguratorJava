package AeroQuad.configurator.ui.mainpanel;


import javax.swing.JPanel;

public interface IMainPanelController
{
    void setPanel(IMainPanel mainPanel);

    void showMonitoringPanel();

    void showSetupPanel();

    void showTuningPanel();

    JPanel getSetupPanel();

    JPanel getMonitoringPanel();

    JPanel getTuningPanel();
}
