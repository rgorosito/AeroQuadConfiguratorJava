package AeroQuad.configurator.ui.mainpanel;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.MonitoringPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.MonitoringPanelController;
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
    private final ITuningPanelController _tuningController = new TuningPanelController();

    private JPanel _monitoringPanel;
    private JPanel _setupPanel;
    private JPanel _tuningPanel;

    private final IMessageDispatcher _messageDispatcher;
    private final ISerialCommunicator _communicator;
    private IMainPanel _panel;


    public MainPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _messageDispatcher = messageDispatcher;
        _communicator = communicator;

        buildChildPanelsAndControllers();

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

    private void buildChildPanelsAndControllers()
    {
        _monitoringPanel = new MonitoringPanel(new MonitoringPanelController(_messageDispatcher, _communicator));
        _setupPanel = new SetupPanel(new SetupPanelController(_communicator));
        _tuningPanel = new TuningPanel(_tuningController);
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
        _tuningController.setActivated(false);
        _panel.showMonitoringPanel();
    }

    @Override
    public void showSetupPanel()
    {
        _tuningController.setActivated(false);
        _panel.showSetupPanel();
    }

    @Override
    public void showTuningPanel()
    {
        _tuningController.setActivated(true);
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
