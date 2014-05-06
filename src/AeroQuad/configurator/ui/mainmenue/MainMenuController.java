package AeroQuad.configurator.ui.mainmenue;

import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.IMainPanelController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuController implements IMainMenuController
{
    private IMainMenuPanel _panel;
    private final IMainPanelController _mainPanelController;

    public MainMenuController(final IMessageDispatcher messageDispatcher,
                              final IMainPanelController mainPanelController)
    {
        _mainPanelController = mainPanelController;
        messageDispatcher.addListener(IMessageDispatcher.CONNECTION_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                final boolean isConnected = (Boolean)evt.getNewValue();
                _panel.setConnected(isConnected);
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

    @Override
    public void setPanel(final IMainMenuPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void processMonitoringButtonPressed()
    {
        _mainPanelController.showMonitoringPanel();
    }

    @Override
    public void processSetupButtonPressed()
    {
        _mainPanelController.showSetupPanel();
    }

    @Override
    public void processTuningButtonPressed()
    {
        _mainPanelController.showTuningPanel();
    }
}
