package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.AttitudePidRequest;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AttitudePidPanelController implements IAttitudePidPanelController
{
    private final IMessageDispatcher _messageDispatcher;
    private final ISerialCommunicator _communicator
            ;
    private IAttitudePidPanel _panel;

    private boolean _initialSyncked = false;

    private PIDData _gyroRollPid = new PIDData();
    private PIDData _accelRollPid = new PIDData();
    private PIDData _gyroPitchPid = new PIDData();
    private PIDData _accelPitchPid = new PIDData();

    private PIDData _userGyroRollPid = new PIDData();
    private PIDData _userAccelRollPid = new PIDData();
    private PIDData _userGyroPitchPid = new PIDData();
    private PIDData _userAccelPitchPid = new PIDData();


    public AttitudePidPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.ATTITUDE_GYRO_ROLL_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _gyroRollPid = (PIDData) evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setGyroRollPid(_gyroRollPid);
                    _userGyroRollPid = _gyroRollPid;
                }
            }
        });

        _messageDispatcher.addListener(IMessageDispatcher.ATTITUDE_ACCEL_ROLL_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _accelRollPid = (PIDData) evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setAccelRollPid(_accelRollPid);
                    _userAccelRollPid = _accelRollPid;
                }
            }
        });

        _messageDispatcher.addListener(IMessageDispatcher.ATTITUDE_GYRO_PITCH_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _gyroPitchPid = (PIDData) evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setGyroPitchPid(_gyroPitchPid);
                    _userGyroPitchPid = _gyroPitchPid;
                    _initialSyncked = true;
                    _panel.setSynced(true);
                }
            }
        });

        _messageDispatcher.addListener(IMessageDispatcher.ATTITUDE_ACCEL_PITCH_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _accelPitchPid = (PIDData) evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setAccelPitchPid(_accelPitchPid);
                    _userAccelPitchPid = _accelPitchPid;
                }
            }
        });
    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        _panel.setUserLevel(userLevel);
    }

    @Override
    public boolean isSyncked()
    {
        return _initialSyncked && isDataSyncked();
    }

    @Override
    public void processSyncing()
    {
        _communicator.sendRequest(new AttitudePidRequest(_messageDispatcher));
    }

    @Override
    public void setPanel(final IAttitudePidPanel panel)
    {
        _panel = panel;
    }

    private boolean isDataSyncked()
    {
        if (!_gyroRollPid.equals(_userGyroRollPid))
        {
            return false;
        }
        if (!_accelRollPid.equals(_userAccelRollPid))
        {
            return false;
        }
        if (!_gyroPitchPid.equals(_userGyroPitchPid))
        {
            return false;
        }
        if (!_accelPitchPid.equals(_userAccelPitchPid))
        {
            return false;
        }
        return true;
    }
}
