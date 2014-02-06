package AeroQuad.configurator.ui.mainpanel.tuning.altitudehold;

import AeroQuad.configurator.communication.messaging.request.AltitudeHoldPidRequest;
import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.messagedispatcher.AltitudeHoldPidData;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AltitudeHoldPidPanelController implements IAltitudeHoldPidPanelController
{
    private final IMessageDispatcher _messageDispatcher;
    private IAltitudeHoldPidPanel _panel;

    private boolean _haveBeenSincedOnce = false;
    private AltitudeHoldPidData _altitudeHoldPidData = new AltitudeHoldPidData();
    private AltitudeHoldPidData _userAltitudeHoldPidData = new AltitudeHoldPidData();


    public AltitudeHoldPidPanelController(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.ALTITUDE_HOLD_PID_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _altitudeHoldPidData = (AltitudeHoldPidData)evt.getNewValue();
                if (!_haveBeenSincedOnce)
                {
                    updatePanelFromPidData(_altitudeHoldPidData);
                    _userAltitudeHoldPidData = _altitudeHoldPidData;
                    _haveBeenSincedOnce = true;
                    _panel.setSinced(true);
                }
            }
        });

    }

    private void updatePanelFromPidData(final AltitudeHoldPidData altitudeHoldPidData)
    {
        _panel.setAltitudePid(altitudeHoldPidData.getAltitudePid());
        _panel.setThrottleBump(altitudeHoldPidData.getAltitudeBump());
        _panel.setThrottlePanic(altitudeHoldPidData.getThrottlePanic());
        _panel.setMinThrottleAdjust(altitudeHoldPidData.getMinThrottleAdjust());
        _panel.setMaxThrottleAdjust(altitudeHoldPidData.getMaxThrottleAdjust());
        _panel.setSmoothFactor(altitudeHoldPidData.getSmoothFactor());
    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        _panel.setUserLevel(userLevel);
    }

    @Override
    public IRequest getRequest()
    {
        return new AltitudeHoldPidRequest(_messageDispatcher);
    }

    @Override
    public boolean haveBeenSincedOnce()
    {
        return _haveBeenSincedOnce;
    }

    @Override
    public String getPidSetCommand()
    {
        return "";
    }

    @Override
    public void userDefaultButtonPressed()
    {

    }

    @Override
    public void setPanel(final IAltitudeHoldPidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public boolean isUserDataInSinced()
    {
        if (!_altitudeHoldPidData.equals(_userAltitudeHoldPidData))
        {
            return false;
        }
        return true;
    }
}
