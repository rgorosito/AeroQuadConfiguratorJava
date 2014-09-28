package AeroQuad.configurator.ui.mainpanel.setup.radio;

import javax.swing.JPanel;
import java.awt.Component;

public interface IRadioSetupController
{
    void setActivated(boolean activated);

    void channelDetectionSelected();

    void channelCalibrationSelected();

    void setPanel(IRadioSetupPanel panel);

    JPanel getRadioChannelDetectionPanel();

    JPanel getRadioCalibrationPanel();
}
