package AeroQuad.configurator.ui.mainpanel.monitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayController;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.MotorDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring.IMotorMonitoringPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring.MotorMonitoringPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring.MotorMonitoringPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.ISensorsMonitoringController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.SensorsMonitoringController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.SensorsMonitoringPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.IVehicleStatusController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.gpsstatepanel.GpsStatusPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.gpsstatepanel.GpsStatusPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.motordisplaypanel.MotorDisplayPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel.OtherSensorsStatusPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel.OtherSensorsStatusPanelController;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.ReceiverDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.receiverdisplay.ReceiverPanelController;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MonitoringPanelController implements IMonitoringPanelController
{
    private IMonitoringPanel _panel;

    private final ISensorsMonitoringController _sensorsMonitoringPanelController;
    private final IMotorMonitoringPanelController _motorsMonitoringPanelController;
    private final IVehicleStatusController _vehicleStatusController;
    private JPanel _vehicleStatusPanel;
    private JPanel _motorCommandPanel;
    private JPanel _sensorsMonitoringPanel;


    public MonitoringPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        final JPanel receiverPanel = new ReceiverDisplayPanel(new ReceiverPanelController(messageDispatcher));
        _vehicleStatusController = new VehicleStatusController(messageDispatcher, communicator);
        final JPanel motorCommandDisplayPanel = new MotorDisplayPanel(new MotorDisplayPanelController(messageDispatcher, communicator));
        final JPanel otherSensorsStatusPanel = new OtherSensorsStatusPanel(new OtherSensorsStatusPanelController(messageDispatcher));
        final GpsStatusPanelController gpsStatePanelController = new GpsStatusPanelController(messageDispatcher);
        final JPanel gpsStatePanel = new GpsStatusPanel(gpsStatePanelController);
        _vehicleStatusPanel = new VehicleStatusPanel(_vehicleStatusController, receiverPanel, motorCommandDisplayPanel,otherSensorsStatusPanel,gpsStatePanel);


        _motorsMonitoringPanelController = new MotorMonitoringPanelController(messageDispatcher, communicator);
        final MotorDisplayPanel motorDisplayPanel = new MotorDisplayPanel((IMotorDisplayController)_motorsMonitoringPanelController);
        _motorCommandPanel = new MotorMonitoringPanel(_motorsMonitoringPanelController, motorDisplayPanel);
        _sensorsMonitoringPanelController = new SensorsMonitoringController(messageDispatcher, communicator);
        _sensorsMonitoringPanel = new SensorsMonitoringPanel(_sensorsMonitoringPanelController);


        messageDispatcher.addListener(IMessageDispatcher.CONNECTION_STATE_CHANGE, new PropertyChangeListener()
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
        if (activated)
        {
            vehicleMonitoringButtonPressed();
        }
    }
}
