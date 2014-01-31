package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.AccroPidRequest;
import AeroQuad.configurator.messagedispatcher.AccroPidData;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccroPidPanelController implements IAccroPidPanelController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IAccroPidPanel _panel;

    private boolean _initialSyncked = false;

    private AccroPidData _pidData = new AccroPidData();
    private AccroPidData _userPidData = new AccroPidData();

    public AccroPidPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.ACCRO_PID_DATA_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _pidData = (AccroPidData)evt.getNewValue();
                if (!_initialSyncked)
                {
                    updatePanelFromPidData(_pidData);
                    _userPidData = _pidData;
                    _initialSyncked = true;
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
        if (!_pidData.equals(_userPidData))
        {
            return false;
        }
        return true;
    }
}
