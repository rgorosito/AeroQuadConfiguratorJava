package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.AttitudePidRequest;
import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.messagedispatcher.AttitudePidData;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AttitudePidPanelController implements IAttitudePidPanelController
{
    private final IMessageDispatcher _messageDispatcher;
    private IAttitudePidPanel _panel;

    private boolean _haveBeenSincedOnce = false;

    private AttitudePidData _attitudePidData = new AttitudePidData();
    private AttitudePidData _userAttitudePidData = new AttitudePidData();
    private UserLevel _userLevel = UserLevel.Beginner;

    public AttitudePidPanelController(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.ATTITUDE_PID_DATA_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                System.out.println("UPDATED");
                _attitudePidData = (AttitudePidData) evt.getNewValue();
                updatePanelFromPidData(_attitudePidData);
                if (!_haveBeenSincedOnce)
                {
                    _userAttitudePidData = _attitudePidData.getCopy();
                    _haveBeenSincedOnce = true;
                    _panel.setSinced(true);
                }
            }
        });
    }

    private void updatePanelFromPidData(final AttitudePidData attitudePidData)
    {
        _panel.setAccelPitchPid(attitudePidData.getAccelPitchPid());
        _panel.setAccelRollPid(attitudePidData.getAccelRollPid());
        _panel.setGyroRollPid(attitudePidData.getGyroRollPid());
        _panel.setGyroPitchPid(attitudePidData.getGyroPitchPid());
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
        System.out.println("Send request");
        return new AttitudePidRequest(_messageDispatcher);
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
        buffer.append(IMessageDefinition.ATTITUDE_PID_SET_COMMAND);
        buffer.append(_userAttitudePidData.getAccelRollPid().getP() + ";");
        buffer.append(_userAttitudePidData.getAccelRollPid().getI() + ";");
        buffer.append(_userAttitudePidData.getAccelRollPid().getD() + ";");
        if (_userLevel == UserLevel.Beginner)
        {
            buffer.append(_userAttitudePidData.getAccelRollPid().getP() + ";");
            buffer.append(_userAttitudePidData.getAccelRollPid().getI() + ";");
            buffer.append(_userAttitudePidData.getAccelRollPid().getD() + ";");
        }
        else
        {
            buffer.append(_userAttitudePidData.getAccelPitchPid().getP() + ";");
            buffer.append(_userAttitudePidData.getAccelPitchPid().getI() + ";");
            buffer.append(_userAttitudePidData.getAccelPitchPid().getD() + ";");
        }

        buffer.append(_userAttitudePidData.getGyroRollPid().getP() + ";");
        buffer.append(_userAttitudePidData.getGyroRollPid().getI() + ";");
        buffer.append(_userAttitudePidData.getGyroRollPid().getD() + ";");
        if (_userLevel == UserLevel.Beginner)
        {
            buffer.append(_userAttitudePidData.getGyroRollPid().getP() + ";");
            buffer.append(_userAttitudePidData.getGyroRollPid().getI() + ";");
            buffer.append(_userAttitudePidData.getGyroRollPid().getD());
        }
        else
        {
            buffer.append(_userAttitudePidData.getGyroPitchPid().getP() + ";");
            buffer.append(_userAttitudePidData.getGyroPitchPid().getI() + ";");
            buffer.append(_userAttitudePidData.getGyroPitchPid().getD());
        }

        System.out.println(buffer.toString());
        return buffer.toString();
    }

    @Override
    public void userDefaultButtonPressed()
    {
        final String accelP = System.getProperty(DEFAULT_PID_ACCEL_P);
        final String accelI = System.getProperty(DEFAULT_PID_ACCEL_I);
        final String accelD = System.getProperty(DEFAULT_PID_ACCEL_D);
        final PIDData accelPid = new PIDData(accelP,accelI,accelD);

        final String gyroP = System.getProperty(DEFAULT_PID_GYRO_P);
        final String gyroI = System.getProperty(DEFAULT_PID_GYRO_I);
        final String gyroD = System.getProperty(DEFAULT_PID_GYRO_D);
        final PIDData gyroPid = new PIDData(gyroP,gyroI,gyroD);

        _userAttitudePidData = new AttitudePidData(accelPid, accelPid.getCopy() ,gyroPid, gyroPid.getCopy());
        updatePanelFromPidData(_userAttitudePidData);
    }

    @Override
    public void setPanel(final IAttitudePidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void userGyroRollPidChanged(final PIDData pid)
    {
        _userAttitudePidData.setGyroRollPid(pid);
        if (_userLevel == UserLevel.Beginner)
        {
            _userAttitudePidData.setGyroPitchPid(pid);
        }
    }

    @Override
    public void userGyroPitchPidChanged(final PIDData pid)
    {
        _userAttitudePidData.setGyroPitchPid(pid);
    }

    @Override
    public void userAccelRollPidChanged(final PIDData pid)
    {
        System.out.println("User accel roll changed = " + pid.getP());
        _userAttitudePidData.setAccelRollPid(pid);
        if(_userLevel == UserLevel.Beginner)
        {
            _userAttitudePidData.setAccelPitchPid(pid);
        }
    }

    @Override
    public void userAccelPitchPidChanged(final PIDData pid)
    {
        _userAttitudePidData.setAccelPitchPid(pid);
    }

    @Override
    public boolean isUserDataInSinced()
    {
        boolean ret = true;
        if (!_attitudePidData.equals(_userAttitudePidData))
        {
            ret = false;
        }
        _panel.setSinced(ret);
        return ret;
    }
}
