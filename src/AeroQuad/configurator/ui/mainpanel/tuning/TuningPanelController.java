package AeroQuad.configurator.ui.mainpanel.tuning;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.tuning.accro.AccroPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.accro.AccroPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.accro.IAccroPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.altitudehold.AltitudeHoldPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.altitudehold.AltitudeHoldPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.altitudehold.IAltitudeHoldPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.attitude.AttitudePidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.attitude.AttitudePidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.attitude.IAttitudePidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor.BatteryMonitorPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor.BatteryMonitorPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.batterymonitor.IBatteryMonitorPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.gps.GpsPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.gps.GpsPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.gps.IGpsPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.yaw.IYawPidPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.yaw.YawPidPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.yaw.YawPidPanelController;

import javax.swing.JPanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TuningPanelController implements ITuningPanelController
{
    private final ISerialCommunicator _communicator;
    private ITuningPanel _panel;

    private JPanel _accroPanel;
    private JPanel _attitudePanel;
    private JPanel _yawPanel;
    private JPanel _altitudePanel;
    private JPanel _batteryMonitorPanel;
    private JPanel _gpsPanel;

    private IAccroPidPanelController _accroPanelController;
    private IAttitudePidPanelController _attitudePanelController;
    private IYawPidPanelController _yawPidPanelController;
    private IAltitudeHoldPidPanelController _altitudeHoldPidPanelController;
    private IBatteryMonitorPidPanelController _batteryMonitorPidPanelController;
    private IGpsPidPanelController _gpsPidPanelController;

    private Timer _syncTimer = null;
    private boolean _sendCommandNeeded = false;

    private final List<IPidPanelController> _pidPanelControllerList = new ArrayList<>(1);
    private boolean _activated = false;

    public TuningPanelController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;

        buildChildscontrollersAndPanels(messageDispatcher, communicator);

        setUserLevel(UserLevel.Beginner);

        messageDispatcher.addListener(IMessageDispatcher.BAROMETER_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean isAltitudeHoldEnabled = (boolean)evt.getNewValue();
                _panel.setAltitudeHoldVisible(isAltitudeHoldEnabled);
                if (isAltitudeHoldEnabled)
                {
                    _pidPanelControllerList.add(_altitudeHoldPidPanelController);
                }
                else
                {
                    _pidPanelControllerList.remove(_altitudeHoldPidPanelController);
                }
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.BATTERY_MONITOR_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean isBatteryMonitorEnabled = (boolean)evt.getNewValue();
                _panel.setBatteryMonitorVisible(isBatteryMonitorEnabled);
                if (isBatteryMonitorEnabled)
                {
                    _pidPanelControllerList.add(_batteryMonitorPidPanelController);
                }
                else
                {
                    _pidPanelControllerList.remove(_batteryMonitorPidPanelController);
                }
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.GPS_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean isGpsEnabled = (boolean)evt.getNewValue();
                _panel.setGpsPanelVisible(isGpsEnabled);
                if (isGpsEnabled)
                {
                    _pidPanelControllerList.add(_gpsPidPanelController);
                }
                else
                {
                    _pidPanelControllerList.remove(_gpsPidPanelController);
                }
            }
        });

        messageDispatcher.addListener(IMessageDispatcher.CONNECTION_STATE_CHANGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final boolean isConnected = (boolean) evt.getNewValue();
                if (!isConnected)
                {
                    resetPanelsControllersInitState();
                }
            }
        });
    }

    private void resetPanelsControllersInitState()
    {
        _accroPanelController.setHaveNotBeenSincedOnce();
        _attitudePanelController.setHaveNotBeenSincedOnce();
        _yawPidPanelController.setHaveNotBeenSincedOnce();
        _altitudeHoldPidPanelController.setHaveNotBeenSincedOnce();
        _batteryMonitorPidPanelController.setHaveNotBeenSincedOnce();
        _gpsPidPanelController.setHaveNotBeenSincedOnce();
    }

    private void buildChildscontrollersAndPanels(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _accroPanelController = new AccroPidPanelController(messageDispatcher);
        _pidPanelControllerList.add(_accroPanelController);
        _accroPanel = new AccroPidPanel(_accroPanelController);
        _attitudePanelController = new AttitudePidPanelController(messageDispatcher);
        _pidPanelControllerList.add(_attitudePanelController);
        _attitudePanel = new AttitudePidPanel(_attitudePanelController);
        _yawPidPanelController = new YawPidPanelController(messageDispatcher);
        _pidPanelControllerList.add(_yawPidPanelController);
        _yawPanel = new YawPidPanel(_yawPidPanelController);
        _altitudeHoldPidPanelController = new AltitudeHoldPidPanelController(messageDispatcher);
        _altitudePanel = new AltitudeHoldPidPanel(_altitudeHoldPidPanelController);
        _batteryMonitorPidPanelController = new BatteryMonitorPidPanelController(messageDispatcher);
        _batteryMonitorPanel = new BatteryMonitorPidPanel(_batteryMonitorPidPanelController);
        _gpsPidPanelController = new GpsPidPanelController(messageDispatcher);
        _gpsPanel = new GpsPidPanel(_gpsPidPanelController);
    }

    @Override
    public void setPanel(final ITuningPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void setUserLevel(final UserLevel userLevel)
    {
        _accroPanelController.setUserLevel(userLevel);
        _attitudePanelController.setUserLevel(userLevel);
        _yawPidPanelController.setUserLevel(userLevel);
        _altitudeHoldPidPanelController.setUserLevel(userLevel);
        _gpsPidPanelController.setUserLevel(userLevel);
    }

    @Override
    public void setActivated(final boolean activated)
    {
        _activated = activated;
        if (activated)
        {
            _communicator.sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
            _syncTimer = new Timer(true);
            _syncTimer.schedule(new SyncTask(),0,100);
        }
        else
        {
            if (_syncTimer != null)
            {
                _syncTimer.cancel();
                _syncTimer = null;
            }
            resetPanelsControllersInitState();
        }
    }


    @Override
    public JPanel getAccroPanel()
    {
        return _accroPanel;
    }

    @Override
    public JPanel getAttitudePanel()
    {
        return _attitudePanel;
    }

    @Override
    public JPanel getYawPanel()
    {
        return _yawPanel;
    }

    @Override
    public JPanel getAltitudePanel()
    {
        return _altitudePanel;
    }

    @Override
    public JPanel getBatteryMonitorPanel()
    {
        return _batteryMonitorPanel;
    }

    @Override
    public JPanel getGpsPanel()
    {
        return _gpsPanel;
    }

    @Override
    public void resetEeprom()
    {
        _communicator.sendCommand("I");
        _panel.setResetEepromEnabled(false);
        final Thread disabledResetEepromThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    // do nothing
                }
                _panel.setResetEepromEnabled(true);
            }
        });
        disabledResetEepromThread.start();  // cause it can take some time for the board and we get disconected if we ask 2 time in a row!
        resetPanelsControllersInitState();
    }

    class SyncTask extends TimerTask
    {
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            synchronized (_pidPanelControllerList)
            {
                if (_activated)
                {
                    sincedAll();
                }
            }
        }
    }


    private void sincedAll()
    {
        for (final IPidPanelController controller: _pidPanelControllerList)
        {
            if (!controller.haveBeenSincedOnce())
            {
                _communicator.sendRequest(controller.getRequest());
                return;
            }
            else if (!controller.isUserDataInSinced())
            {
                if (_sendCommandNeeded)
                {
                    final String pidSetCommand = controller.getSetPidCommand();
                    _communicator.sendCommand(pidSetCommand);
                    _communicator.sendCommand("W"); // Write Eeprom
                }
                else
                {
                    _communicator.sendRequest(controller.getRequest());
                }
                _sendCommandNeeded = !_sendCommandNeeded;
                return;
            }
        }
    }
}
