package AeroQuad.configurator.ui.mainpanel.setup;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.AccelCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.AccelCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.IAccelCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.EscCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.EscCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.IEscCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.IMagCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.MagCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.MagCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.radiocalibration.IRadioCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.radiocalibration.RadioCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.radiocalibration.RadioCalibrationPanelController;

import javax.swing.JPanel;

public class SetupPanelController implements ISetupPanelController
{
    private ISetupPanel _panel;

    private IAccelCalibrationPanelController _accelCalibrationController;
    private JPanel _accelCalibrationPanel;
    private IEscCalibrationPanelController _escCalibrationController;
    private JPanel _escCalibrationPanel;
    private IMagCalibrationPanelController _magCalibrationController;
    private JPanel _magCalibrationPanel;
    private IRadioCalibrationPanelController _radioCalibrationController;
    private JPanel _radioCalibrationPanel;

    public SetupPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        initControllers(messageDispatcher, communicator);
    }

    private void initControllers(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _accelCalibrationController = new AccelCalibrationPanelController(messageDispatcher,communicator);
        _accelCalibrationPanel = new AccelCalibrationPanel(_accelCalibrationController);

        _escCalibrationController = new EscCalibrationPanelController(messageDispatcher,communicator);
        _escCalibrationPanel = new EscCalibrationPanel(_escCalibrationController);

        _magCalibrationController = new MagCalibrationPanelController(messageDispatcher,communicator);
        _magCalibrationPanel = new MagCalibrationPanel(_magCalibrationController);

        _radioCalibrationController = new RadioCalibrationPanelController(messageDispatcher,communicator);
        _radioCalibrationPanel = new RadioCalibrationPanel(_radioCalibrationController);
    }

    @Override
    public void setPanel(final ISetupPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void accelCalibrationButtonPressed()
    {
        _accelCalibrationController.setActivated(true);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioCalibrationController.setActivated(false);
        _panel.showPanel(ISetupPanel.ACCEL_CALIBRATION);
    }

    @Override
    public void radioCalibrationButtonPressed()
    {
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioCalibrationController.setActivated(true);
        _panel.showPanel(ISetupPanel.RADIO_CALIBRATION);
    }

    @Override
    public void escCalibrationButtonPressed()
    {
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(true);
        _magCalibrationController.setActivated(false);
        _radioCalibrationController.setActivated(false);
        _panel.showPanel(ISetupPanel.ESC_CALIBRATION);
    }

    @Override
    public void magCalibrationButtonPressed()
    {
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(true);
        _radioCalibrationController.setActivated(false);
        _panel.showPanel(ISetupPanel.MAG_CALIBRATION);
    }

    @Override
    public JPanel getAccelCalibrationPanel()
    {
        return _accelCalibrationPanel;
    }

    @Override
    public JPanel getRadioCalibrationPanel()
    {
        return _radioCalibrationPanel;
    }

    @Override
    public JPanel getEscCalibrationPanel()
    {
        return _escCalibrationPanel;
    }

    @Override
    public JPanel getMagCalibrationPanel()
    {
        return _magCalibrationPanel;
    }

    @Override
    public void setActivated(final boolean activated)
    {
        if (activated)
        {
            accelCalibrationButtonPressed();
        }
    }
}
