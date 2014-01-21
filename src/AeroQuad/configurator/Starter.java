package AeroQuad.configurator;


import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.SerialCommunicator;
import AeroQuad.configurator.communication.communicationstatistics.CommunicationStatisticsProcessor;
import AeroQuad.configurator.communication.communicationstatistics.ICommunicationStatisticsProcessor;
import AeroQuad.configurator.communication.connectionthread.ConnectionThreadMonitor;
import AeroQuad.configurator.messageDispatcher.IMessageDispatcher;
import AeroQuad.configurator.messageDispatcher.MessageDispatcher;
import AeroQuad.configurator.ui.AQConfiguratorMainFrame;

import javax.swing.*;

public class Starter
{
    public Starter()
    {
        init();
    }

    private void init()
    {
        final IMessageDispatcher messageDispatcher = new MessageDispatcher();

        final ISerialCommunicator communicator = new SerialCommunicator(messageDispatcher);
        final ICommunicationStatisticsProcessor statisticProcessor = new CommunicationStatisticsProcessor(communicator);
        new ConnectionThreadMonitor(communicator, statisticProcessor);
        final AQConfiguratorMainFrame mainFrame = new AQConfiguratorMainFrame(communicator,messageDispatcher);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Starter();
            }
        });
    }
}
