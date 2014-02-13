package AeroQuad.configurator.ui.mainpanel.monitoring.motormonitoring;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayController;
import AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel.IMotorDisplayPanel;

import java.io.IOException;
import java.net.URI;

public class MotorMonitoringPanelController implements IMotorMonitoringPanelController, IMotorDisplayController
{

    private final ISerialCommunicator _communicator;

    public MotorMonitoringPanelController(final IMessageDispatcher messageDispatcher,
                                          final ISerialCommunicator communicator)
    {
        _communicator = communicator;
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
        System.out.println("Motor = " + motor + " Value = " + value);
    }

    @Override
    public void sendUserMotorCommand()
    {
        System.out.println("Send command");
    }

    @Override
    public void stopAllMotor()
    {
        System.out.println("Stop command");
    }

    @Override
    public void overrideManualSendCommand(final boolean selected)
    {
        System.out.println("Override = " + selected);
    }


    @Override
    public void setPanel(final IMotorDisplayPanel panel)
    {
        //panel.setMaxMotorsValue(1200);
    }
}
