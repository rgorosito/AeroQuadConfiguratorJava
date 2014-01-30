package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.AccroPidRequest;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccroPidPanelController implements IAccroPidPanelController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IAccroPidPanel _panel;

    private boolean _initialSyncked = false;

    private PIDData _rollPid = new PIDData();
    private PIDData _pitchPid = new PIDData();
    private String _stickScalling = "";

    private PIDData _userRollPid = new PIDData();
    private PIDData _userPitchPid = new PIDData();
    private String _userStickScalling = "";

    public AccroPidPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.ACCRO_ROLL_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _rollPid = (PIDData)evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setRollPid(_rollPid);
                    _userRollPid = _rollPid;
                }
            }
        });

        _messageDispatcher.addListener(IMessageDispatcher.ACCRO_PITCH_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _pitchPid = (PIDData)evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setPitchPid(_pitchPid);
                    _userPitchPid = _pitchPid;
                }
            }
        });

        _messageDispatcher.addListener(IMessageDispatcher.STICK_SCALING_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _stickScalling = (String)evt.getNewValue();
                if (!_initialSyncked)
                {
                    _panel.setStickScaling(_stickScalling);
                    _userStickScalling = _stickScalling;
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
        _communicator.sendRequest(new AccroPidRequest(_messageDispatcher));
    }

    @Override
    public void setPanel(final IAccroPidPanel panel)
    {
        _panel = panel;
    }

    private boolean isDataSyncked()
    {
        if (!_rollPid.equals(_userRollPid))
        {
            return false;
        }
        if (!_pitchPid.equals(_userPitchPid))
        {
            return false;
        }
        if (!_stickScalling.equals(_userStickScalling))
        {
            return false;
        }
        return true;
    }
}
