package AeroQuad.configurator.ui.mainpanel.tuning.yaw;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.YawPidRequest;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class YawPidPanelController implements IYawPidPanelController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IYawPidPanel _panel;

    private boolean _initialSyncked = false;

    private PIDData _yawPid = new PIDData();
    private PIDData _headingHoldPid = new PIDData();
    private PIDData _userYawPid = new PIDData();
    private PIDData _userHeadingHoldPid = new PIDData();


    public YawPidPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.YAW_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _yawPid = (PIDData)evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setYawPid(_yawPid);
                    _userYawPid = _yawPid;
                }
            }
        });

        _messageDispatcher.addListener(IMessageDispatcher.HEADING_HOLD_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _headingHoldPid = (PIDData)evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setHeadingHoldPid(_headingHoldPid);
                    _userHeadingHoldPid = _headingHoldPid;
                    _initialSyncked = true;
                    _panel.setSinced(true);
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
        _communicator.sendRequest(new YawPidRequest(_messageDispatcher));
    }

    @Override
    public void setPanel(final IYawPidPanel panel)
    {
        _panel = panel;
    }

    private boolean isDataSyncked()
    {
        if (!_yawPid.equals(_userYawPid))
        {
            return false;
        }
        if (!_headingHoldPid.equals(_userHeadingHoldPid))
        {
            return false;
        }
        return false;
    }
}
