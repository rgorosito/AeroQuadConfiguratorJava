package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.AttitudePidRequest;
import AeroQuad.configurator.communication.messaging.request.IRequest;
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

    private boolean _haveBeenSincedOnce = false;

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
                if (!_haveBeenSincedOnce)
                {
                    updatePanelFromPidData(_attitudePidData);
                    _userAttitudePidData = _attitudePidData;
                    _haveBeenSincedOnce = true;
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
    public IRequest getRequest()
    {
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
        return null;
    }

    @Override
    public void setPanel(final IAttitudePidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public boolean isUserDataInSinced()

    {
        if (!_attitudePidData.equals(_userAttitudePidData))
        {
            return false;
        }
        return true;
    }
}
