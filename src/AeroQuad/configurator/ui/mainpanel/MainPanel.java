package AeroQuad.configurator.ui.mainpanel;

import AeroQuad.configurator.ui.splashpanel.SplashPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements IMainPanel
{
    private final CardLayout _cardLayout = new CardLayout();
    private final IMainPanelController _controller;

    public MainPanel(final IMainPanelController controller)
    {
        _controller = controller;
        setLayout(_cardLayout);

        controller.setPanel(this);
        init();
    }

    private void init()
    {
        final JPanel slashPanel = new SplashPanel();
        add(slashPanel,SPLASH_SCREEN_KEY);
        add(_controller.getSetupPanel(),SETUP_SCREEN_KEY);
        add(_controller.getMonitoringPanel(),MONITORING_SCREEN_KEY);
        add(_controller.getTuningPanel(),TUNING_SCREEN_KEY);
    }

    @Override
    public void updateConnectionState(final boolean isConnected)
    {
        if (isConnected)
        {
            _cardLayout.show(this,MONITORING_SCREEN_KEY);
        }
        else
        {
            _cardLayout.show(this,SPLASH_SCREEN_KEY);
        }
    }

    @Override
    public void showMonitoringPanel()
    {
        _cardLayout.show(this,MONITORING_SCREEN_KEY);
    }

    @Override
    public void showSetupPanel()
    {
        _cardLayout.show(this,SETUP_SCREEN_KEY);
    }

    @Override
    public void showTuningPanel()
    {
        _cardLayout.show(this,TUNING_SCREEN_KEY);
    }
}
