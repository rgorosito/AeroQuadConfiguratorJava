package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.AttitudePidRequest;
import AeroQuad.configurator.messagedispatcher.AttitudePidData;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
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

    private AttitudePidData _attitudePidData = new AttitudePidData();
    private AttitudePidData _userAttitudePidData = new AttitudePidData();

    public AttitudePidPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.ATTITUDE_PID_DATA_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _attitudePidData = (AttitudePidData) evt.getNewValue();
                if (!_initialSyncked)
                {
                    updatePanelFromPidData(_attitudePidData);
                    _userAttitudePidData = _attitudePidData;
                    _initialSyncked = true;
                    _panel.setSynced(true);
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
        if (!_attitudePidData.equals(_userAttitudePidData))
        {
            return false;
        }
        return true;
    }
}
