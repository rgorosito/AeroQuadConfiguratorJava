package AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor;

import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.BatteryConfigRequest;
import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.messagesdispatcher.BatteryMonitorConfigData;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BatteryMonitorPidPanelController implements IBatteryMonitorPidPanelController
{
    private final IMessageDispatcher _messageDispatcher;
    private IBatteryMonitorPidPanel _panel;
    private boolean _haveBeenSincedOnce = false;

    private BatteryMonitorConfigData _pidData = new BatteryMonitorConfigData();
    private BatteryMonitorConfigData _userPidData = new BatteryMonitorConfigData();

    public BatteryMonitorPidPanelController(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.BATTERY_MONITOR_CONFIG_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _pidData = (BatteryMonitorConfigData)evt.getNewValue();
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

    private void updatePanelFromPidData(final BatteryMonitorConfigData pidData)
    {
        _panel.setAlarmVoltage(pidData.getAlarmVoltage());
        _panel.setThrottleTarget(pidData.getThrottleTarget());
        _panel.setGoingDownTime(pidData.getGoingDownTime());
    }

    @Override
    public void setPanel(final IBatteryMonitorPidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void userAlarmVoltageChanged(final String value)
    {
        _userPidData.setAlarmVoltage(value);
    }

    @Override
    public void userThrottleTargetChanged(final String value)
    {
        _userPidData.setThrottleTarget(value);
    }

    @Override
    public void userGoingDownTimeChanged(final String value)
    {
        _userPidData.setGoingDownTime(value);
    }


    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        // do nothing
    }

    @Override
    public IRequest getRequest()
    {
        return new BatteryConfigRequest(_messageDispatcher);
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
        buffer.append(IMessageDefinition.BATTERY_CONFIG_SET_COMMAND).append(" ");
        buffer.append(_userPidData.getAlarmVoltage() + ";");
        buffer.append(_userPidData.getThrottleTarget() + ";");
        buffer.append(_userPidData.getGoingDownTime() + ";");
        return buffer.toString();
    }

    @Override
    public void userDefaultButtonPressed()
    {
        final String defaultAlarmVoltage = System.getProperty(DEFAULT_ALARM_VOLTAGE_PROPERTY_KEY);
        final String defaultThrottleTarget = System.getProperty(DEFAULT_THROTTLE_TARGET_PROPERTY_KEY);
        final String defaultGoinDownTime = System.getProperty(DEFAULT_GOIN_DOWN_TIME_PROPERTY_KEY);
        _userPidData = new BatteryMonitorConfigData(defaultAlarmVoltage,defaultThrottleTarget,defaultGoinDownTime);
        updatePanelFromPidData(_userPidData);
    }

    @Override
    public void setHaveNotBeenSincedOnce()
    {
        _haveBeenSincedOnce = false;
        _panel.setSinced(_haveBeenSincedOnce);
        _pidData = new BatteryMonitorConfigData();
        updatePanelFromPidData(_pidData);
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
