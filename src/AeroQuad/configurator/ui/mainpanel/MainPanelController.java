package AeroQuad.configurator.ui.mainpanel;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.IMonitoringPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.MonitoringPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.MonitoringPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.ISetupPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.SetupPanel;
import AeroQuad.configurator.ui.mainpanel.setup.SetupPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.ITuningPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.TuningPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.TuningPanelController;

import javax.swing.JPanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainPanelController implements IMainPanelController
{
    private final JPanel _monitoringPanel;
    private final JPanel _setupPanel;
    private final JPanel _tuningPanel;

    private final ITuningPanelController _tuningController;
    private final ISetupPanelController _setupPanelController;
    private final IMonitoringPanelController _monitoringPanelController;

    private IMainPanel _panel;

    public MainPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _monitoringPanelController = new MonitoringPanelController(messageDispatcher, communicator);
        _monitoringPanel = new MonitoringPanel(_monitoringPanelController);
        _setupPanelController = new SetupPanelController(messageDispatcher, communicator);
        _setupPanel = new SetupPanel(_setupPanelController);
        _tuningController = new TuningPanelController(messageDispatcher, communicator);
        _tuningPanel = new TuningPanel(_tuningController);

        communicator.addListener(ISerialCommunicator.CONNECTION_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean isConnected = (Boolean)evt.getNewValue();
                updateConnectionState(isConnected);
            }
        });
    }

        private void updateConnectionState(final boolean isConnected)
    {
       _panel.updateConnectionState(isConnected);
    }

    @Override
    public void setPanel(final IMainPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void showMonitoringPanel()
    {
        _setupPanelController.setActivated(false);
        _tuningController.setActivated(false);
        _monitoringPanelController.setActivated(true);
        _panel.showMonitoringPanel();
    }

    @Override
    public void showSetupPanel()
    {
        _setupPanelController.setActivated(true);
        _tuningController.setActivated(false);
        _monitoringPanelController.setActivated(false);
        _panel.showSetupPanel();
    }

    @Override
    public void showTuningPanel()
    {
        _setupPanelController.setActivated(false);
        _tuningController.setActivated(true);
        _monitoringPanelController.setActivated(false);
        _panel.showTuningPanel();
    }

    @Override
    public JPanel getSetupPanel()
    {
        return _setupPanel;
    }

    @Override
    public JPanel getMonitoringPanel()
    {
        return _monitoringPanel;
    }

    @Override
    public JPanel getTuningPanel()
    {
        return _tuningPanel;
    }
}
