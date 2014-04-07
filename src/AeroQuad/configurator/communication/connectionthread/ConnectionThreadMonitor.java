package AeroQuad.configurator.communication.connectionthread;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.communication.communicationstatistics.ICommunicationStatisticsProcessor;

import java.util.List;

public class ConnectionThreadMonitor implements IConnectionThreadMonitor
{
    private final int NB_EMPTY_HIT_THRESHOLD = 4;
    private final int NB_CONNECTING_COUNT_TO_FAILED = 8;

    private final ICommunicationStatisticsProcessor _statisticProcessor;
    private int _nothingReceivedHit;
    private int _tryingToConnectNbHit;

    public ConnectionThreadMonitor(final ISerialCommunicator communicator, ICommunicationStatisticsProcessor statisticProcessor)
    {
        _statisticProcessor = statisticProcessor;
        final Thread connectionThread = new Thread(new CommunicationThread(communicator));
        connectionThread.setName("WatchDog");
        connectionThread.start();
    }

    private void monitorConnection(final ISerialCommunicator communicator)
    {
        if (communicator.isConnecting())
        {
            System.out.println("Connecting");
            _tryingToConnectNbHit++;
            if (_tryingToConnectNbHit > NB_CONNECTING_COUNT_TO_FAILED)
            {
                System.out.println("Cant connect, close and retry");
                _tryingToConnectNbHit = 0;
                communicator.disconnect();
            }
        }
        else if (!communicator.isConnected())
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
            _tryingToConnectNbHit = 0;
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
                monitorConnection(_communicator);

                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    System.err.println("Communication thread error = " + e);
                }
            }
        }


    }


}
