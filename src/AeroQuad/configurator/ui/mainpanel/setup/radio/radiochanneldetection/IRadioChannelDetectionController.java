package AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection;

public interface IRadioChannelDetectionController
{
    void setActivated(boolean activated);

    void startDetection();

    void cancelDetection();

    void setPanel(IRadioChannelDetectionPanel panel);
}
