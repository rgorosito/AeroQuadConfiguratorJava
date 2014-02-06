package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.communication.messaging.request.YawPidRequest;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;
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
    private UserLevel _userLevel = UserLevel.Beginner;


    public YawPidPanelController(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.YAW_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _yawPid = (YawPidData)evt.getNewValue();
                updatePanelFromPidData(_yawPid);
                if (!_haveBeenSincedOnce)
                {
                    _userYawPid = _yawPid.getCopy();
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
        _userLevel = userLevel;
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
        final StringBuffer buffer = new StringBuffer();
        buffer.append(IMessageDefinition.YAW_PID_SET_COMMAND);
        buffer.append(_userYawPid.getYawPid().getP() + ";");
        buffer.append(_userYawPid.getYawPid().getI() + ";");
        buffer.append(_userYawPid.getYawPid().getD() + ";");
        buffer.append(_userYawPid.getHeadingHoldPid().getP() + ";");
        buffer.append(_userYawPid.getHeadingHoldPid().getI() + ";");
        buffer.append(_userYawPid.getHeadingHoldPid().getD() + ";");
        buffer.append("1");
        return buffer.toString();
    }

    @Override
    public void userDefaultButtonPressed()
    {
        final String yawP = System.getProperty(DEFAULT_YAW_P);
        final String yawI = System.getProperty(DEFAULT_YAW_I);
        final String yawD = System.getProperty(DEFAULT_YAW_D);
        final PIDData yawPid = new PIDData(yawP,yawI,yawD);

        final String headingHoldP = System.getProperty(DEFAULT_HEADING_HOLD_P);
        final String headingHoldI = System.getProperty(DEFAULT_HEADING_HOLD_I);
        final String headingHoldD = System.getProperty(DEFAULT_HEADING_HOLD_D);
        final PIDData headingHoldPid = new PIDData(headingHoldP,headingHoldI,headingHoldD);

        _userYawPid = new YawPidData(yawPid, headingHoldPid);
        updatePanelFromPidData(_userYawPid);
    }

    @Override
    public void setHaveNotBeenSincedOnce()
    {
        _haveBeenSincedOnce = false;
        _panel.setSinced(_haveBeenSincedOnce);
    }

    @Override
    public void setPanel(final IYawPidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void userYawPidChanged(final PIDData pid)
    {
        _userYawPid.setYawPid(pid);
    }

    @Override
    public void headingHoldPidChanged(final PIDData pid)
    {
        _userYawPid.setHeadingHoldPid(pid);
    }

    @Override
    public boolean isUserDataInSinced()
    {
        boolean ret = true;
        if (!_yawPid.equals(_userYawPid))
        {
            ret = false;
        }
        _panel.setSinced(ret);
        return ret;
    }
}
