package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.communication.messaging.request.YawPidRequest;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.YawPidData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class YawPidPanelController implements IYawPidPanelController
{
    private final IMessageDispatcher _messageDispatcher;
    private IYawPidPanel _panel;

    private boolean _haveBeenSincedOnce = false;

    private YawPidData _yawPid = new YawPidData();
    private YawPidData _userYawPid = new YawPidData();


    public YawPidPanelController(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.YAW_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _yawPid = (YawPidData)evt.getNewValue();
                if (!_haveBeenSincedOnce)
                {
                    updatePanelFromPidData(_yawPid);
                    _userYawPid = _yawPid;
                    _haveBeenSincedOnce = true;
                    _panel.setSinced(true);
                }
            }
        });
    }

    private void updatePanelFromPidData(final YawPidData yawPid)
    {
        _panel.setYawPid(yawPid.getYawPid());
        _panel.setHeadingHoldPid(yawPid.getHeadingHoldPid());
    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        _panel.setUserLevel(userLevel);
    }

    @Override
    public IRequest getRequest()
    {
        return new YawPidRequest(_messageDispatcher);
    }

    @Override
    public boolean haveBeenSincedOnce()
    {
        return _haveBeenSincedOnce;
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
    public void setPanel(final IYawPidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public boolean isUserDataInSinced()
    {
        if (!_yawPid.equals(_userYawPid))
        {
            return false;
        }
        return true;
    }
}
