package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class BatteryMonitorPidPanelController implements IBatteryMonitorPidPanelController
{
    private IBatteryMonitorPidPanel _panel;
    private boolean _haveBeenSincedOnce = false;

    public BatteryMonitorPidPanelController(final IMessageDispatcher messageDispatcher)
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
    public IRequest getRequest()
    {
        return null;
    }

    @Override
    public boolean haveBeenSincedOnce()
    {
        return false;
    }

    @Override
    public boolean isUserDataInSinced()
    {
        return false;
    }

    @Override
    public String getPidSetCommand()
    {
        return null;
    }

    @Override
    public void userDefaultButtonPressed()
    {

    }

    @Override
    public void setHaveNotBeenSincedOnce()
    {
        _haveBeenSincedOnce = false;
        _panel.setSinced(_haveBeenSincedOnce);
    }
}
