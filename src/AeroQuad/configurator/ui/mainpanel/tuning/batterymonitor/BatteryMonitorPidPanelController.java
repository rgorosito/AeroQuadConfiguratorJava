package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class BatteryMonitorPidPanelController implements IBatteryMonitorPidPanelController
{
    private IBatteryMonitorPidPanel _panel;

    public BatteryMonitorPidPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {

    }


    @Override
    public void setPanel(final IBatteryMonitorPidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        // do nothing
    }

    @Override
    public boolean isSyncked()
    {
        return true;
    }

    @Override
    public void processSyncing()
    {

    }
}
