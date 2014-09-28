package AeroQuad.configurator.ui.mainpanel.setup.eepromreset;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import javax.swing.SwingUtilities;

public class EepromResetController implements IEepromResetController
{
    private final ISerialCommunicator _communicator;
    private final IMessageDispatcher _messageDispatcher;
    private IEepromResetPanel _panel;

    public EepromResetController(final IMessageDispatcher messageDispatcher, final ISerialCommunicator communicator)
    {
        _communicator = communicator;
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public void setPanel(final IEepromResetPanel panel)
    {
        _panel = panel;
    }

    @Override
    public void setActivated(final boolean activated)
    {

    }

    @Override
    public void resetEepromButtonPressed()
    {
        _communicator.sendCommand("I");
        _messageDispatcher.dispatchMessage(IMessageDispatcher.SET_MENUE_ENABLED_MESSAGE_KEY, false);
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
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        _messageDispatcher.dispatchMessage(IMessageDispatcher.SET_MENUE_ENABLED_MESSAGE_KEY, true);
                    }
                });
            }

        });
        disabledResetEepromThread.start();  // cause it can take some time for the board and we get disconected if we ask 2 time in a row!
    }
}
