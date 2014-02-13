package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayController;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayPanel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MotorMonitoringPanelController implements IMotorMonitoringPanelController, IMotorDisplayController
{

    private final ISerialCommunicator _communicator;
    private int _nbMotors = 4;

    final Map<Integer,Integer> _motorsCommandValues = new HashMap<Integer,Integer>();
    private IMotorMonitoringPanel _panel;
    private boolean _overrideSendButton = false;

    public MotorMonitoringPanelController(final IMessageDispatcher messageDispatcher,
                                          final ISerialCommunicator communicator)
    {
        _communicator = communicator;

        setMotorsCommandToStopped();

        messageDispatcher.addListener(IMessageDispatcher.NB_MOTORS_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _nbMotors = (int)evt.getNewValue();
            }
        });
    }

    private void setMotorsCommandToStopped()
    {
        for (int i = 1;i < 8; i++)
        {
            _motorsCommandValues.put(i, 1000);
        }
    }

    @Override
    public void setActivated(final boolean activated)
    {
        if (activated)
        {
            _communicator.sendCommand(ISerialCommunicator.REQUEST_STOP_SENDING);
        }
    }

    @Override
    public void showCraftConfugurationPage()
    {
        try
        {
            java.awt.Desktop.getDesktop().browse(URI.create("http://aeroquad.com/showwiki.php?title=Flight+Configurations"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void userMotorValueChanged(final int motor, final int value)
    {
        _motorsCommandValues.put(motor, value);
    }

    @Override
    public void sendUserMotorCommand()
    {
        sendMotorCommand();
    }


    @Override
    public void stopAllMotor()
    {
        setMotorsCommandToStopped();
        sendMotorCommand();
        for (int i = 1;i <= _nbMotors; i++)
        {
            _panel.setMotorValue(i, _motorsCommandValues.get(i));
        } 
    }

    @Override
    public void overrideManualSendCommand(final boolean selected)
    {
        _overrideSendButton = selected;
        _panel.setSendEnabled(!selected);
        if (_overrideSendButton)
        {
            final Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (_overrideSendButton)
                    {
                        try
                        {
                            Thread.sleep(250);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        sendMotorCommand();
                    }
                }
            });
            thread.start();
        }
        else
        {
            stopAllMotor();
        }
    }

    @Override
    public void setPanel(final IMotorMonitoringPanel panel)
    {
        _panel = panel;
    }


    @Override
    public void setPanel(final IMotorDisplayPanel panel)
    {
        // was a bad design choice to try reuse the MotorDisplayPanel here, so, do nothing here. @todo, fix this
    }

    private void sendMotorCommand()
    {
        final StringBuffer command = new StringBuffer();
        command.append(IMessageDefinition.OVERRIDE_MOTOR_COMMAND);
        command.append("123.45;");
        for (int i = 1; i <= _nbMotors; i++)
        {
            command.append(Integer.toString(_motorsCommandValues.get(i))).append(";");
        }
        _communicator.sendCommand(command.toString());
    }
}
