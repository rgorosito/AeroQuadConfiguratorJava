package AeroQuad.configurator;


import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.SerialCommunicator;
import AeroQuad.configurator.communication.communicationstatistics.CommunicationStatisticsProcessor;
import AeroQuad.configurator.communication.communicationstatistics.ICommunicationStatisticsProcessor;
import AeroQuad.configurator.communication.connectionthread.ConnectionThreadMonitor;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagedispatcher.MessageDispatcher;
import AeroQuad.configurator.ui.AQConfiguratorMainFrame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Starter
{
    public Starter()
    {
        init();
    }

    private void init()
    {
        initProperties();

        final IMessageDispatcher messageDispatcher = new MessageDispatcher();

        final ISerialCommunicator communicator = new SerialCommunicator(messageDispatcher);
        final ICommunicationStatisticsProcessor statisticProcessor = new CommunicationStatisticsProcessor(communicator, messageDispatcher);
        final AQConfiguratorMainFrame mainFrame = new AQConfiguratorMainFrame(communicator,messageDispatcher);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new ConnectionThreadMonitor(communicator, statisticProcessor);
    }

    private void initProperties()
    {
        try
        {
            final Properties prop = new Properties();
            final InputStream in = new FileInputStream("configurator.properties");
            prop.load(in);
            System.getProperties().putAll(prop);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
