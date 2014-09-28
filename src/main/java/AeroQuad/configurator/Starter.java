package AeroQuad.configurator;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.SerialCommunicator;
import AeroQuad.configurator.communication.communicationstatistics.CommunicationStatisticsProcessor;
import AeroQuad.configurator.communication.communicationstatistics.ICommunicationStatisticsProcessor;
import AeroQuad.configurator.communication.connectionthread.ConnectionThreadMonitor;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import AeroQuad.configurator.messagesdispatcher.MessageDispatcher;
import AeroQuad.configurator.ui.AQConfiguratorMainFrame;

public class Starter
{
	
	private static final String AQ_PROPERTIES = "/configurations/configurator.properties";

    public Starter()
    {
        init();
    }

    private void init()
    {
        initLog4j();

        //LOGGER.debug("INIT APPLICATION");
        initProperties();

        final IMessageDispatcher messageDispatcher = new MessageDispatcher();

        final ISerialCommunicator communicator = new SerialCommunicator(messageDispatcher);
        final ICommunicationStatisticsProcessor statisticProcessor = new CommunicationStatisticsProcessor(communicator, messageDispatcher);
        final AQConfiguratorMainFrame mainFrame = new AQConfiguratorMainFrame(communicator,messageDispatcher);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //LOGGER.debug("START CONNECTION MONITORING");
        new ConnectionThreadMonitor(communicator, statisticProcessor);
    }

    private void initLog4j()
    {
        //org.apache.log4j.LogManager.resetConfiguration();
        //DOMConfigurator.configure(DEFAULT_LOGGING_PROPERTIES);
    }

    private void initProperties()
    {
        try
        {
            final Properties prop = new Properties();

            final InputStream in = this.getClass().getResourceAsStream(AQ_PROPERTIES);
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
