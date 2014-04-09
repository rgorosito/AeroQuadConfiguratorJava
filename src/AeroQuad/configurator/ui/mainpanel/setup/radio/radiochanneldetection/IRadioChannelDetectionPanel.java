package AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection;

public interface IRadioChannelDetectionPanel
{
    final static String UNDETECTED = "Undetected";
    final static String DETECTING = "Detecting";
    final static String DETECTED= "Detected";

    void setAux1Visible(boolean visible);
    void setAux2Visible(boolean visible);
    void setAux3Visible(boolean visible);

    void setCancelButtonEnabled(boolean enabled);

    void setStartButtonEnabled(boolean enabled);

    void updateUserFeedback(ReceiverDetectionState currentDetectingChannel, int cpt);

    void setCurrentChannelDetected(ReceiverDetectionState currentDetectingChannel);
}
