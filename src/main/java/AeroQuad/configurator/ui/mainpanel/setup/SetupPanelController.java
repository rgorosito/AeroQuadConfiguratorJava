package AeroQuad.configurator.ui.mainpanel.setup;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.AccelCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.AccelCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.accelcalibration.IAccelCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.eepromreset.EepromResetController;
import AeroQuad.configurator.ui.mainpanel.setup.eepromreset.EepromResetPanel;
import AeroQuad.configurator.ui.mainpanel.setup.eepromreset.IEepromResetController;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.EscCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.EscCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.esccalibration.IEscCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.IMagCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.MagCalibrationPanel;
import AeroQuad.configurator.ui.mainpanel.setup.magcalibration.MagCalibrationPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.IRadioSetupController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.RadioSetupController;
import AeroQuad.configurator.ui.mainpanel.setup.radio.RadioSetupPanel;
import AeroQuad.configurator.ui.mainpanel.setup.vehiclesetup.IVehicleSetupController;
import AeroQuad.configurator.ui.mainpanel.setup.vehiclesetup.VehicleSetupController;
import AeroQuad.configurator.ui.mainpanel.setup.vehiclesetup.VehicleSetupPanel;

import javax.swing.JPanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SetupPanelController implements ISetupPanelController
{
    private ISetupPanel _panel;


    private IEepromResetController _eepromResetController;
    private JPanel _eepromResetPanel;
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
        messageDispatcher.addListener(IMessageDispatcher.SET_MENUE_ENABLED_MESSAGE_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean enabled = (boolean)evt.getNewValue();
                _panel.setButtonsEnabled(enabled);
            }
        });
    }

    private void initControllers(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _eepromResetController = new EepromResetController(messageDispatcher, communicator);
        _eepromResetPanel = new EepromResetPanel(_eepromResetController);

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
    public void resetEepromButtonPressed()
    {
        _eepromResetController.setActivated(true);
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(false);
        _panel.showPanel(ISetupPanel.EEPROM_RESET);
    }


    @Override
    public void vehicleConfigButtonPressed()
    {
        _eepromResetController.setActivated(false);
        _accelCalibrationController.setActivated(true);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(false);
        _panel.showPanel(ISetupPanel.VEHICLE_CONFIG);
    }

    @Override
    public void accelCalibrationButtonPressed()
    {
        _eepromResetController.setActivated(false);
        _accelCalibrationController.setActivated(true);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(false);
        _panel.showPanel(ISetupPanel.ACCEL_CALIBRATION);
    }

    @Override
    public void radioCalibrationButtonPressed()
    {
        _eepromResetController.setActivated(false);
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(true);
        _panel.showPanel(ISetupPanel.RADIO_CALIBRATION);
    }

    @Override
    public void escCalibrationButtonPressed()
    {
        _eepromResetController.setActivated(false);
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(true);
        _magCalibrationController.setActivated(false);
        _radioSetupController.setActivated(false);
        _panel.showPanel(ISetupPanel.ESC_CALIBRATION);
    }

    @Override
    public void magCalibrationButtonPressed()
    {
        _eepromResetController.setActivated(false);
        _accelCalibrationController.setActivated(false);
        _escCalibrationController.setActivated(false);
        _magCalibrationController.setActivated(true);
        _radioSetupController.setActivated(false);
        _panel.showPanel(ISetupPanel.MAG_CALIBRATION);
    }

    @Override
    public JPanel getEepromResetPanel()
    {
        return _eepromResetPanel;
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
            _panel.performVehicleSetupSelection();
        }
    }
}
