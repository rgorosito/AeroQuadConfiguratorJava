package AeroQuad.configurator.communication.connectionthread;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.communicationstatistics.ICommunicationStatisticsProcessor;

import javax.swing.*;
import java.util.List;

public class ConnectionThreadMonitor
{
    private final int NB_EMPTY_HIT_THRESHOLD = 2; // @todo: use some config in property file

    private final ICommunicationStatisticsProcessor _statisticProcessor;
    private int _nothingReceivedHit;

    public ConnectionThreadMonitor(final ISerialCommunicator communicator, ICommunicationStatisticsProcessor statisticProcessor)
    {
        _statisticProcessor = statisticProcessor;
        final Thread thread = new Thread(new CommunicationThread(communicator));
        thread.start();
    }

    private void monitorConnection(final ISerialCommunicator communicator)
    {
        if (!communicator.isConnected())
        {
            tryToConnect(communicator);
        }
        else
        {
            monitorConnectedState(communicator);
        }
        _statisticProcessor.processCommunicationStatistics();
    }

    private void tryToConnect(final ISerialCommunicator communicator)
    {
        final List<String> commPortList = communicator.getComPortAvailable();
        if (commPortList.size() != 0)
        {
            for (final String commPort : commPortList)
            {
                communicator.connect(commPort);
                if (communicator.isConnected())
                {
                    break;
                }
            }
        }
    }

    private void monitorConnectedState(final ISerialCommunicator communicator)
    {
        if (_statisticProcessor.getNbCharReceived() == 0)
        {
            communicator.sendCommand("!");
            _nothingReceivedHit++;
            if (_nothingReceivedHit > NB_EMPTY_HIT_THRESHOLD)
            {
                communicator.disconnect();
            }
        }
        else
        {
            _nothingReceivedHit = 0;
        }
    }

    private class CommunicationThread implements Runnable
    {
        private final ISerialCommunicator _communicator;

        public CommunicationThread(ISerialCommunicator communicator)
        {
            _communicator = communicator;
        }

        @Override
        public void run()
        {
            while (true)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        monitorConnection(_communicator);
                    }
                });

                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    System.err.println("Communication thread error = " + e);
                }
            }
        }


    }


}
