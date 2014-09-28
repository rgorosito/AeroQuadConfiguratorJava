package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.GpsPidRequest;
import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.messagesdispatcher.GpsPidData;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GpsPidPanelController implements IGpsPidPanelController
{
    private final IMessageDispatcher _messageDispatcher;
    private IGpsPidPanel _panel;
    private boolean _haveBeenSincedOnce = false;

    private GpsPidData _pidData = new GpsPidData();
    private GpsPidData _userPidData = new GpsPidData();
    private UserLevel _userLevel = UserLevel.Beginner;

    public GpsPidPanelController(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.GPS_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _pidData = (GpsPidData)evt.getNewValue();
                if (!_haveBeenSincedOnce)
                {
                    updatePanelFromPidData(_pidData);
                    _userPidData = _pidData.getCopy();
                    _haveBeenSincedOnce = true;
                    _panel.setSinced(true);
                }
            }
        });
    }

    private void updatePanelFromPidData(final GpsPidData pidData)
    {
        _panel.setRollPid(pidData.getRollPid());
        _panel.setPitchPid(pidData.getPitchPid());
        _panel.setYawPid(pidData.getYawPid());
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
        return new GpsPidRequest(_messageDispatcher);
    }

    @Override
    public boolean haveBeenSincedOnce()
    {
        return _haveBeenSincedOnce;
    }

    @Override
    public String getSetPidCommand()
    {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(IMessageDefinition.GPS_PID_SET_COMMAND).append(" ");
        buffer.append(_userPidData.getRollPid().getP() + ";");
        buffer.append(_userPidData.getRollPid().getI() + ";");
        buffer.append(_userPidData.getRollPid().getD() + ";");
        if (_userLevel == UserLevel.Beginner)
        {
            buffer.append(_userPidData.getRollPid().getP() + ";");
            buffer.append(_userPidData.getRollPid().getI() + ";");
            buffer.append(_userPidData.getRollPid().getD() + ";");
        }
        else
        {
            buffer.append(_userPidData.getPitchPid().getP() + ";");
            buffer.append(_userPidData.getPitchPid().getI() + ";");
            buffer.append(_userPidData.getPitchPid().getD() + ";");
        }
        buffer.append(_userPidData.getYawPid().getP() + ";");
        buffer.append(_userPidData.getYawPid().getI() + ";");
        buffer.append(_userPidData.getYawPid().getD());

        return buffer.toString();
    }

    @Override
    public void userDefaultButtonPressed()
    {
        final String rollP = System.getProperty(DEFAULT_ROLL_PID_P);
        final String rollI = System.getProperty(DEFAULT_ROLL_PID_I);
        final String rollD = System.getProperty(DEFAULT_ROLL_PID_D);
        final PIDData rollPid = new PIDData(rollP, rollI, rollD);

        final String pitchP = System.getProperty(DEFAULT_PITCH_PID_P);
        final String pitchI = System.getProperty(DEFAULT_PITCH_PID_I);
        final String pitchD = System.getProperty(DEFAULT_PITCH_PID_D);
        final PIDData pitchPid = new PIDData(pitchP, pitchI, pitchD);

        final String yawP = System.getProperty(DEFAULT_YAW_PID_P);
        final String yawI = System.getProperty(DEFAULT_YAW_PID_I);
        final String yawD = System.getProperty(DEFAULT_YAW_PID_D);
        final PIDData yawPid = new PIDData(yawP, yawI, yawD);

        _userPidData = new GpsPidData(rollPid, pitchPid, yawPid);
        updatePanelFromPidData(_userPidData);
    }

    @Override
    public void setHaveNotBeenSincedOnce()
    {
        _haveBeenSincedOnce = false;
        _panel.setSinced(_haveBeenSincedOnce);
        _pidData = new GpsPidData();
        updatePanelFromPidData(_pidData);
    }

    @Override
    public void setPanel(final IGpsPidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void userRollPidChanged(final PIDData pid)
    {
        _userPidData.setRollPid(pid);
        if (_userLevel == UserLevel.Beginner)
        {
            _userPidData.setPitchPid(pid);
        }
    }

    @Override
    public void userPitchPidChanged(final PIDData pid)
    {
        _userPidData.setPitchPid(pid);
    }

    @Override
    public void userYawPidChanged(final PIDData pid)
    {
        _userPidData.setYawPid(pid);
    }

    @Override
    public boolean isUserDataInSinced()
    {
        boolean ret = true;
        if (!_pidData.equals(_userPidData))
        {
            ret = false;
        }
        _panel.setSinced(ret);
        return ret;
    }
}
