package AeroQuad.configurator.ui.mainpanel.tuning.accro;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.request.AccroPidRequest;
import AeroQuad.configurator.messagedispatcher.AccroPidData;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.PIDData;
import AeroQuad.configurator.ui.mainpanel.tuning.UserLevel;

import javax.swing.SwingUtilities;
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
                    _userPidData = _pidData.getCopy();
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
        if (!_initialSyncked)
        {
            return false;
        }
        final boolean sinced = isDataSyncked();
        if (!sinced)
        {
            sendUserPidDataToBoard();
        }
        return sinced;
    }

    private void sendUserPidDataToBoard()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                final StringBuffer buffer = new StringBuffer();
                buffer.append(IMessageDefinition.ACCRO_PID_SET_COMMAND);
                buffer.append(_userPidData.getRollPid().getP() + ";");
                buffer.append(_userPidData.getRollPid().getI() + ";");
                buffer.append(_userPidData.getRollPid().getD() + ";");
                buffer.append(_userPidData.getPitchPid().getP() + ";");
                buffer.append(_userPidData.getPitchPid().getI() + ";");
                buffer.append(_userPidData.getPitchPid().getD() + ";");
                buffer.append(_userPidData.getStickScaling());
                _communicator.sendCommand(buffer.toString());
            }
        });
    }

    @Override
    public void processSyncing()
    {
        System.out.println("Send request");
        _communicator.sendRequest(new AccroPidRequest(_messageDispatcher));
    }

    @Override
    public void setPanel(final IAccroPidPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void userRollPidChanged(final PIDData pid)
    {
        _userPidData.setRollPid(pid);
    }

    @Override
    public void userPitchPidChanged(final PIDData pid)
    {
        _userPidData.setPitchPid(pid);
    }

    @Override
    public void userStickScalingChanged(final String text)
    {
        _userPidData.setStickScaling(text);
    }

    private boolean isDataSyncked()
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
