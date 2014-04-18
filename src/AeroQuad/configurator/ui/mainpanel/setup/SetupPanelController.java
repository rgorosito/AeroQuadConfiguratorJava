package AeroQuad.configurator.ui.mainpanel.setup;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusPanel;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.IReceiverDisplayPanelController;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.ReceiverDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.ReceiverPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.AccelCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.AccelCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.IAccelCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.EscCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.EscCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.IEscCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.IMagCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.MagCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.MagCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.IRadioSetupController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.RadioSetupController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.RadioSetupPanel;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiocalibration.IRadioCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiocalibration.RadioCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.radio.radiocalibration.RadioCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.vehiclesetup.IVehicleSetupController;
import AeroQuad.configurator.ui.mainpanel.setup.vehiclesetup.VehicleSetupController;
import AeroQuad.configurator.ui.mainpanel.setup.vehiclesetup.VehicleSetupPanel;

import javax.swing.JPanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SetupPanelController implements ISetupPanelController
{
    private ISetupPanel _panel;

    private IVehicleSetupController _vehicleSetupController;
    private JPanel _vehicleSetupPanel;
    private IAccelCalibrationPanelController _accelCalibrationController;
    private JPanel _accelCalibrationPanel;
    private IEscCalibrationPanelController _escCalibrationController;
    private JPanel _escCalibrationPanel;
    private IMagCalibrationPanelController _magCalibrationController;
    private JPanel _magCalibrationPanel;

    private IRadioSetupController _radioSetupController;
    private JPanel _radioSetupPanel;

    public SetupPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        initControllers(messageDispatcher, communicator);

        messageDispatcher.addListener(IMessageDispatcher.MAGNETOMETER_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                boolean magEnabled = (boolean)evt.getNewValue();
                _panel.setMagBalenEnable(magEnabled);
            }
        });
    }

    private void initControllers(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _vehicleSetupController = new VehicleSetupController(messageDispatcher, communicator);
        _vehicleSetupPanel = new VehicleSetupPanel(_vehicleSetupController);

        _accelCalibrationController = new AccelCalibrationPanelController(messageDispatcher,communicator);
        _accelCalibrationPanel = new AccelCalibrationPanel(_accelCalibrationController);

        _escCalibrationController = new EscCalibrationPanelController(communicator);
        _escCalibrationPanel = new EscCalibrationPanel(_escCalibrationController);

        _magCalibrationController = new MagCalibrationPanelController(messageDispatcher,communicator);
        _magCalibrationPanel = new MagCalibrationPanel(_magCalibrationController);

        _radioSetupController = new RadioSetupController(messageDispatcher,communicator);
        _radioSetupPanel = new RadioSetupPanel(_radioSetupController);
    }

    @Override
    public void setPanel(final ISetupPanel panel)
    {
        _panel = panel;
    }


    @Override
    public void vehicleConfigButtonPressed()
    {
        _vehicleSetupController.setActivated(true);
        _accelCalibrationController.setActivated(true);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(false);
        _panel.showPanel(ISetupPanel.VEHICLE_CONFIG);

    }


    @Override
    public void accelCalibrationButtonPressed()
    {
        _vehicleSetupController.setActivated(false);
        _accelCalibrationController.setActivated(true);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(false);
        _panel.showPanel(ISetupPanel.ACCEL_CALIBRATION);
    }

    @Override
    public void radioCalibrationButtonPressed()
    {
        _vehicleSetupController.setActivated(false);
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(true);
        _panel.showPanel(ISetupPanel.RADIO_CALIBRATION);
    }

    @Override
    public void escCalibrationButtonPressed()
    {
        _vehicleSetupController.setActivated(false);
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(true);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(false);
        _panel.showPanel(ISetupPanel.ESC_CALIBRATION);
    }

    @Override
    public void magCalibrationButtonPressed()
    {
        _vehicleSetupController.setActivated(false);
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(true);
        _radioSetupController.setActivated(false);
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
        return _radioSetupPanel;
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
    public JPanel getVehicleSetupPanel()
    {
        return _vehicleSetupPanel;
    }


    @Override
    public void setActivated(final boolean activated)
    {
        if (activated)
        {
            _panel.performEscCalibrationSelection();
        }
    }

}
