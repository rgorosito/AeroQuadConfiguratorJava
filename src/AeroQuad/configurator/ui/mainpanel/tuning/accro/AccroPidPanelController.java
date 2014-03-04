package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.AccroPidRequest;
import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.messagesdispatcher.AccroPidData;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccroPidPanelController implements IAccroPidPanelController
{
    private final IMessageDispatcher _messageDispatcher;
    private IAccroPidPanel _panel;

    private boolean _haveBeenSincedOnce = false;

    private AccroPidData _pidData = new AccroPidData();
    private AccroPidData _userPidData = new AccroPidData();
    private UserLevel _userLevel = UserLevel.Beginner;

    public AccroPidPanelController(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.ACCRO_PID_DATA_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _pidData = (AccroPidData)evt.getNewValue();
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

    private void updatePanelFromPidData(final AccroPidData pidData)
    {
        _panel.setRollPid(pidData.getRollPid());
        _panel.setPitchPid(pidData.getPitchPid());
        _panel.setStickScaling(pidData.getStickScaling());
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
        return new AccroPidRequest(_messageDispatcher);
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
        buffer.append(IMessageDefinition.ACCRO_PID_SET_COMMAND);
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
        buffer.append(_userPidData.getStickScaling());
        return buffer.toString();
    }

    @Override
    public void userDefaultButtonPressed()
    {
        final String rollP = System.getProperty(DEFAULT_PID_P);
        final String rollI = System.getProperty(DEFAULT_PID_I);
        final String rollD = System.getProperty(DEFAULT_PID_D);
        final PIDData rollPid = new PIDData(rollP,rollI,rollD);

        final String stickScaling = System.getProperty(DEFAULT_PID_STICK_SCALING);
        _userPidData = new AccroPidData(rollPid, rollPid.getCopy(), stickScaling);
        updatePanelFromPidData(_userPidData);
    }

    @Override
    public void setHaveNotBeenSincedOnce()
    {
        _haveBeenSincedOnce = false;
        _panel.setSinced(_haveBeenSincedOnce);
    }

    @Override
    public void setPanel(final IAccroPidPanel panel)
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
            _panel.setPitchPid(pid);
        }
    }

    @Override
    public void userPitchPidChanged(final PIDData pid)
    {
        _userPidData.setPitchPid(pid);
    }

    @Override
    public void userStickScalingChanged(final String text)
    {
        _userPidData.setStickScaling(text);
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
