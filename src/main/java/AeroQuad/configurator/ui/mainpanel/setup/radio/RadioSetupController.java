package AeroQuad.configurator.ui.mainpanel.setup.radio;

import javax.swing.JPanel;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.IReceiverDisplayPanelController;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.ReceiverDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.ReceiverPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiocalibration.IRadioCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiocalibration.RadioCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiocalibration.RadioCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection.IRadioChannelDetectionController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection.RadioChannelDetectionController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection.RadioChannelDetectionPanel;


public class RadioSetupController implements IRadioSetupController
{
    private IRadioChannelDetectionController _radioChannelDetectionController;
    private JPanel _radioChannelDetectionPanel;

    private IRadioCalibrationPanelController _radioCalibrationController;
    private JPanel _radioCalibrationPanel;
    private IRadioSetupPanel _panel;


    public RadioSetupController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {

        initControllers(messageDispatcher, communicator);
    }

    private void initControllers(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _radioChannelDetectionController = new RadioChannelDetectionController(messageDispatcher, communicator);
        _radioChannelDetectionPanel = new RadioChannelDetectionPanel(_radioChannelDetectionController);

        final IReceiverDisplayPanelController receiverDisplayPanelController = new ReceiverPanelController(messageDispatcher);
        final JPanel receiverPanel = new ReceiverDisplayPanel(receiverDisplayPanelController);
        _radioCalibrationController = new RadioCalibrationPanelController(messageDispatcher,communicator, receiverDisplayPanelController);
        _radioCalibrationPanel = new RadioCalibrationPanel(_radioCalibrationController, receiverPanel);

    }

    @Override
    public void setActivated(final boolean activated)
    {
        if (activated)
        {
            channelDetectionSelected();
        }
        else
        {
            _radioChannelDetectionController.setActivated(activated);
            _radioCalibrationController.setActivated(activated);
        }
    }

    @Override
    public void channelDetectionSelected()
    {
        _radioChannelDetectionController.setActivated(true);
        _radioCalibrationController.setActivated(false);
        _panel.loadCurrentPanel(IRadioSetupPanel.CHANNEL_DETECTION);
    }

    @Override
    public void channelCalibrationSelected()
    {
        _radioChannelDetectionController.setActivated(false);
        _radioCalibrationController.setActivated(true);
        _panel.loadCurrentPanel(IRadioSetupPanel.CHANNEL_CALIBRATION);
    }

    @Override
    public void setPanel(final IRadioSetupPanel panel)
    {
        _panel = panel;
    }

    @Override
    public JPanel getRadioChannelDetectionPanel()
    {
        return _radioChannelDetectionPanel;
    }

    @Override
    public JPanel getRadioCalibrationPanel()
    {
        return _radioCalibrationPanel;
    }
}
