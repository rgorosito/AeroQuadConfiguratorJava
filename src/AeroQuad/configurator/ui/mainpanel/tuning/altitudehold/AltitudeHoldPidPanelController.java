package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.request.AltitudeHoldPidRequest;
import AeroQuad.configurator.messagedispatcher.AltitudeHoldPidData;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

public class AltitudeHoldPidPanelController implements IAltitudeHoldPidPanelController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IAltitudeHoldPidPanel _panel;

    private boolean _initialSyncked = false;
    private AltitudeHoldPidData _altitudeHoldPidData = new AltitudeHoldPidData();
    private AltitudeHoldPidData _userAltitudeHoldPidData = new AltitudeHoldPidData();


    public AltitudeHoldPidPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;
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
        _communicator.sendRequest(new AltitudeHoldPidRequest(_messageDispatcher));
    }

    @Override
    public void setPanel(final IAltitudeHoldPidPanel panel)
    {
        _panel = panel;
    }

    private boolean isDataSyncked()
    {
        if (!_altitudeHoldPidData.equals(_userAltitudeHoldPidData))
        {
            return false;
        }
        return true;
    }

}
