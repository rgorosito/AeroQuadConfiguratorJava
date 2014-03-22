package AeroQuad.configurator.communication;

import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.communication.messaging.request.VehicleInfoRequest;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.SwingUtilities;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class SerialCommunicator implements ISerialCommunicator
{
    @SuppressWarnings("unchecked")

    //private static final Logger LOGGER = LogManager.getLogger(SerialCommunicator.class);

    private CommPortIdentifier _portId = null;
    private String _connectedPortName;
    private SerialPort _connectedPort = null;

    private InputStream _imputStreamReader = null;
    private OutputStream _outputStream = null;
    private boolean _isConnected = false;
    private BufferedReader _bufferedReader = null;

    private final IMessageDispatcher _messageDispatcher;
    private IMessageAnalyser _messageAnalyser;

    private boolean _isConnecting = false;

    public SerialCommunicator(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.GPS_PROPERTY_KEY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _isConnecting = false;
                _isConnected = true;
                //LOGGER.debug("CONNECTED TO AEROQUAD");
                _messageDispatcher.dispatchMessage(IMessageDispatcher.CONNECTION_STATE_CHANGE, _isConnected);
            }
        });
    }

    @Override
    public List<String> getComPortAvailable()
    {
        final ArrayList<String> portListName = new ArrayList<String>(1);
        final Enumeration portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements())
        {
            _portId = (CommPortIdentifier) portList.nextElement();
            portListName.add(_portId.getName().toString());
        }

        return portListName;      // Return the ports found on the system
    }

    @Override
    public void connect(final String commPort)
    {
        connect(ISerialCommunicator.DEFAULT_BOAD_RATE, commPort);
    }

    @Override
    public void connect(final int baudRate, final String defaultPort)
    {
        if (_isConnecting)
        {
            return;
        }
        _isConnecting = true;
        _connectedPortName = defaultPort;

        boolean portFound = false;
        if (_portId != null)
        {
            if (_portId.getName().equals(_connectedPortName))
            {
                portFound = true;
                _messageDispatcher.dispatchMessage(IMessageDispatcher.BAUD_RATE, baudRate);
                _messageDispatcher.dispatchMessage(IMessageDispatcher.COMM_PORT_OPENED, _connectedPortName);
            }
        }

        if (portFound)
        {
            try
            {
                //LOGGER.debug("Trying to connect to " + defaultPort + "@" + baudRate);
                _connectedPort = (SerialPort) _portId.open("Aeroquad Serial Communicator", 2000);
                _imputStreamReader = _connectedPort.getInputStream();
                _outputStream = _connectedPort.getOutputStream();
                _bufferedReader = new BufferedReader(new InputStreamReader(_imputStreamReader), 5);
                _connectedPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                _connectedPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                _connectedPort.addEventListener(new SerialPortEventListener()
                {

                    @Override
                    public void serialEvent(SerialPortEvent serialPortEvent)
                    {
                        processSerialEvent(serialPortEvent);
                    }
                });
            }
            catch (final Exception e)
            {
                System.err.println("Can't connect to comm port because of " + e);
                _imputStreamReader = null;
            }
            // Advise if data available to be read on the port
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            _connectedPort.notifyOnDataAvailable(true);
            //LOGGER.debug("Port " + defaultPort + "@" + baudRate + " open");
            sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
            final VehicleInfoRequest request = new VehicleInfoRequest(_messageDispatcher);
            _messageAnalyser = request.getMessageAnalyser();
            //LOGGER.debug("Request vehicle information");
            sendRequest(request);
        }
    }

   @Override
    public void disconnect(final boolean forceDisconnect)
    {
        if (_isConnected || forceDisconnect)
        {
            try
            {
                if (_imputStreamReader != null)
                {
                    _imputStreamReader.close();
                    _imputStreamReader = null;
                }
                if (_bufferedReader != null)
                {
                    _bufferedReader.close();
                    _bufferedReader = null;
                }
            }
            catch (Exception e)
            {
                System.err.println("Close connection problem");
            }

            _connectedPort.close();

            System.out.println("Serial Port closed!!");
            _isConnected = false;
            _isConnecting = false;
            _messageDispatcher.dispatchMessage(IMessageDispatcher.CONNECTION_STATE_CHANGE, _isConnected);
        }
        //LOGGER.debug("***DISCONECTED***");
    }


    @Override
    public void sendRequest(final IRequest request)
    {
        _messageAnalyser = request.getMessageAnalyser();
        sendCommand(request.getStringMessage());
    }

    @Override
    public void sendCommand(final String command)
    {
        try
        {
            //System.out.println(command);
            //LOGGER.debug("Send = " + command);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RAW_DATA_MESSAGE_SENT, command);
            _outputStream.write(command.getBytes());
            _outputStream.close();
        }
        catch (IOException e)
        {
            System.err.println("Send command error");
            disconnect(false);
        }
    }

    @Override
    public boolean isConnected()
    {
        return _isConnected;
    }

    private void processSerialEvent(final SerialPortEvent event)
    {

        switch (event.getEventType())
        {
            case SerialPortEvent.BI:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.FE:
            case SerialPortEvent.OE:
            case SerialPortEvent.PE:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;

            case SerialPortEvent.DATA_AVAILABLE:
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        analyzeIncomingData();
                    }
                });
                break;

            default:
                break;
        }
    }


    private void analyzeIncomingData()
    {
        String rawData = null;
        try
        {
            while ((rawData = _bufferedReader.readLine()) != null)
            {
                handleReceivedString(rawData);
            }
        }
        catch (IOException e)
        {
            // do nothing
        }
        catch (Exception e)
        {
            if (rawData != null)
            {
                System.err.println("Decoding message error = " + rawData);
            }
        }
    }

    private void handleReceivedString(final String rawData)
    {
        //LOGGER.debug("Received = " + rawData);
        //System.out.println(rawData);
        _messageDispatcher.dispatchMessage(IMessageDispatcher.RAW_DATA_MESSAGE_RECEIVED,  rawData);
        _messageAnalyser.analyzeRawData(rawData);
    }
}
