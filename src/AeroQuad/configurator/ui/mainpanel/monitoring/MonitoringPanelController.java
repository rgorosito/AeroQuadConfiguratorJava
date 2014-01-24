package AeroQuad.configurator.ui.mainpanel.monitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.SensorsMonitoringController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.SensorsMonitoringPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusPanel;
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
    private final IMessageDispatcher _messageDispatcher;
    private final ISerialCommunicator _communicator;
    private IMonitoringPanel _panel;

    private JPanel _vehicleStatusPanel;
    private JPanel _motorCommandPanel;
    private JPanel _sensorsMonitoringPanel;


    public MonitoringPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _messageDispatcher = messageDispatcher;
        _communicator = communicator;

        buildChildPanels();

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

    private void buildChildPanels()
    {
        final ReceiverDisplayPanel receiverPanel = new ReceiverDisplayPanel(new ReceiverPanelController(_messageDispatcher));
        final VehicleStatusController vehicleStatusController = new VehicleStatusController(_messageDispatcher, _communicator);
        final MotorDisplayPanel motorCommandDisplayPanel = new MotorDisplayPanel(new MotorDisplayController(_messageDispatcher));
        final OtherSensorsStatusPanel otherSensorsStatusPanel = new OtherSensorsStatusPanel(new OtherSensorsStatusPanelController(_messageDispatcher));
        _vehicleStatusPanel = new VehicleStatusPanel(vehicleStatusController, receiverPanel, motorCommandDisplayPanel,otherSensorsStatusPanel);
        _motorCommandPanel = new MotorDisplayPanel(new MotorDisplayController(_messageDispatcher));
        _sensorsMonitoringPanel = new SensorsMonitoringPanel(new SensorsMonitoringController(_messageDispatcher, _communicator));
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
}
