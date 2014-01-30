package AeroQuad.configurator.ui.mainpanel.monitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.ISensorsMonitoringController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.SensorsMonitoringController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.SensorsMonitoringPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.IVehicleStatusController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.motordisplay.IMotorDisplayController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.motordisplay.MotorDisplayController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.motordisplay.MotorDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel.OtherSensorsStatusPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel.OtherSensorsStatusPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.receiverdisplay.ReceiverDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.receiverdisplay.ReceiverPanelController;

import javax.swing.JPanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MonitoringPanelController implements IMonitoringPanelController
{
    private IMonitoringPanel _panel;

    private final ISensorsMonitoringController _sensorsMonitoringPanelController;
    private final IMotorDisplayController _motorsMonitoringPanelController;
    private final IVehicleStatusController _vehicleStatusController;
    private JPanel _vehicleStatusPanel;
    private JPanel _motorCommandPanel;
    private JPanel _sensorsMonitoringPanel;


    public MonitoringPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        final ReceiverDisplayPanel receiverPanel = new ReceiverDisplayPanel(new ReceiverPanelController(messageDispatcher));
        _vehicleStatusController = new VehicleStatusController(messageDispatcher, communicator);
        final MotorDisplayPanel motorCommandDisplayPanel = new MotorDisplayPanel(new MotorDisplayController(messageDispatcher, communicator));
        final OtherSensorsStatusPanel otherSensorsStatusPanel = new OtherSensorsStatusPanel(new OtherSensorsStatusPanelController(messageDispatcher));
        _vehicleStatusPanel = new VehicleStatusPanel(_vehicleStatusController, receiverPanel, motorCommandDisplayPanel,otherSensorsStatusPanel);
        _motorsMonitoringPanelController = new MotorDisplayController(messageDispatcher, communicator);
        _motorCommandPanel = new MotorDisplayPanel(_motorsMonitoringPanelController);
        _sensorsMonitoringPanelController = new SensorsMonitoringController(messageDispatcher, communicator);
        _sensorsMonitoringPanel = new SensorsMonitoringPanel(_sensorsMonitoringPanelController);


        communicator.addListener(ISerialCommunicator.CONNECTION_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean connected = (Boolean)evt.getNewValue();
                _panel.setConnectedState(connected);
            }
        });
    }

    @Override
    public void setPanel(final IMonitoringPanel panel)
    {
        _panel = panel;
    }

    @Override
    public JPanel getVehicleStatusPanel()
    {
        return _vehicleStatusPanel;
    }

    @Override
    public JPanel getSensorsMonitoringPanel()
    {
        return _sensorsMonitoringPanel;
    }

    @Override
    public JPanel getMotorCommandPanel()
    {
        return _motorCommandPanel;
    }

    @Override
    public void sensorsMonitoringButtonPressed()
    {
        _motorsMonitoringPanelController.setActivated(false);
        _sensorsMonitoringPanelController.setActivated(true);
        _vehicleStatusController.setActivated(false);
        _panel.showPanel(IMonitoringPanel.SENSORS);
    }

    @Override
    public void motorsMonitoringButtonPressed()
    {
        _motorsMonitoringPanelController.setActivated(true);
        _sensorsMonitoringPanelController.setActivated(false);
        _vehicleStatusController.setActivated(false);
        _panel.showPanel(IMonitoringPanel.MOTORS);
    }

    @Override
    public void vehicleMonitoringButtonPressed()
    {
        _motorsMonitoringPanelController.setActivated(false);
        _sensorsMonitoringPanelController.setActivated(false);
        _vehicleStatusController.setActivated(true);
        _panel.showPanel(IMonitoringPanel.VEHICLE);
    }

    @Override
    public void setActivated(final boolean activated)
    {
        vehicleMonitoringButtonPressed();
    }
}
