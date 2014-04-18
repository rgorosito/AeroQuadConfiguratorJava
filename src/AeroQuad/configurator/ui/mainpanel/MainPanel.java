package AeroQuad.configurator.ui.mainpanel;

import AeroQuad.configurator.ui.splashpanel.SplashPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements IMainPanel
{
    private final CardLayout _cardLayout = new CardLayout();
    final JPanel _cardPanel = new JPanel(_cardLayout);

    private final IMainPanelController _controller;



    public MainPanel(final IMainPanelController controller)
    {
        _controller = controller;
        setLayout(new BorderLayout());

        controller.setPanel(this);
        init();
    }

    private void init()
    {
        add(_controller.getVehicleInfosFeedback(),BorderLayout.NORTH);
        add(_cardPanel, BorderLayout.CENTER);
        final JPanel slashPanel = new SplashPanel();
        _cardPanel.add(slashPanel,SPLASH_SCREEN_KEY);
        _cardPanel.add(_controller.getSetupPanel(),SETUP_SCREEN_KEY);
        _cardPanel.add(_controller.getMonitoringPanel(),MONITORING_SCREEN_KEY);
        _cardPanel.add(_controller.getTuningPanel(),TUNING_SCREEN_KEY);
    }

    @Override
    public void updateConnectionState(final boolean isConnected)
    {
        if (isConnected)
        {
            _cardLayout.show(_cardPanel,MONITORING_SCREEN_KEY);
        }
        else
        {
            _cardLayout.show(_cardPanel,SPLASH_SCREEN_KEY);
        }
    }

    @Override
    public void showMonitoringPanel()
    {
        _cardLayout.show(_cardPanel,MONITORING_SCREEN_KEY);
    }

    @Override
    public void showSetupPanel()
    {
        _cardLayout.show(_cardPanel,SETUP_SCREEN_KEY);
    }

    @Override
    public void showTuningPanel()
    {
        _cardLayout.show(_cardPanel,TUNING_SCREEN_KEY);
    }
}
