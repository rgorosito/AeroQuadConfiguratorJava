package AeroQuad.configurator.communication;


import AeroQuad.configurator.communication.messaging.IMessageDefinition;
import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.request.IRequest;
import AeroQuad.configurator.communication.messaging.request.VehicleInfoRequest;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;
import jssc.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SerialCommunicator implements ISerialCommunicator
{
    private final String SERIAL_END_OF_LINE = "\r\n";

    private final IMessageDispatcher _messageDispatcher;

    private boolean _isConnected = false;
    private boolean _isConnecting = false;

    private int _baudRateSpeed = SerialPort.BAUDRATE_115200;
    private IMessageAnalyser _messageAnalyser;
    private SerialPort _serialPort;
    private String _rawLine = "";

    private int _connectionDelay = 2000;

    public SerialCommunicator(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;

        _messageDispatcher.addListener(IMessageDispatcher.YAW_DIRECTION_KET, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                _isConnecting = false;
                _isConnected = true;

                System.out.println("CONNECTED TO AEROQUAD");
                _messageDispatcher.dispatchMessage(IMessageDispatcher.CONNECTION_STATE_CHANGE, _isConnected);
            }
        });

    }

    @Override
    public List<String> getComPortAvailable()
    {
        final List<String> ret = new ArrayList<String>(1);
        final String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++)
        {
            ret.add(portNames[i]);
        }
        _messageDispatcher.dispatchMessage(IMessageDispatcher.COM_PORT_LIST_UPDATE_KEY, ret);
        return ret;
    }

    @Override
    public void connect(final String commPort)
    {
        if (_isConnecting)
        {
            return;
        }

        System.out.println("Trying to connect to " + commPort + "@" + _baudRateSpeed);

        _messageDispatcher.dispatchMessage(IMessageDispatcher.BAUD_RATE, _baudRateSpeed);
        _messageDispatcher.dispatchMessage(IMessageDispatcher.COMM_PORT_OPENED, commPort);
        _isConnecting = true;
        _serialPort = new SerialPort(commPort);
        try
        {
            _serialPort.openPort();
            _serialPort.setParams(_baudRateSpeed, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            _serialPort.addEventListener(new SerialPortEventListener()
            {
                @Override
                public void serialEvent(final SerialPortEvent event)
                {
                    analyseSerialPortEvent(event);
                }
            });

            final Thread initialRequestThread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(_connectionDelay);
                        sendCommand(IMessageDefinition.REQUEST_STOP_SENDING);
                        Thread.sleep(_connectionDelay);
                        sendRequest(new VehicleInfoRequest(_messageDispatcher));
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                }
            });
            initialRequestThread.start();

        }
        catch (SerialPortException ex)
        {
            System.out.println(ex);
            _isConnecting = false;
            _isConnected = false;
            _rawLine = "";
        }
    }

    private void analyseSerialPortEvent(final SerialPortEvent event)
    {
        if(event.isRXCHAR())
        {
            try
            {
                synchronized (_serialPort)
                {
                    final String rawData = _serialPort.readString();
                    analyseIncommingData(rawData);
                }
            }
            catch (SerialPortException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }


    private void analyseIncommingData(final String rawData)
    {
        if (rawData == null)
        {
            return;
        }
        _rawLine += rawData;
        String line = "";
        while (_rawLine.contains(SERIAL_END_OF_LINE))
        {
            line += _rawLine.charAt(0);
            _rawLine = _rawLine.substring(1);
            if (_rawLine.length() >= 2 && _rawLine.charAt(0) == '\r' && _rawLine.charAt(1) == '\n')
            {
                _rawLine = _rawLine.substring(2);
                _messageDispatcher.dispatchMessage(IMessageDispatcher.RAW_DATA_MESSAGE_RECEIVED,  line);
                if (_messageAnalyser != null)
                {
                    _messageAnalyser.analyzeRawData(line);
                }
                line = "";
            }
        }
    }

    @Override
    public void disconnect()
    {
        if (_serialPort != null)
        {
            try
            {
                _isConnected = false;
                _isConnecting = false;
                _rawLine = "";
                _serialPort.closePort();
            }
            catch (Exception e)
            {

            }
            _messageDispatcher.dispatchMessage(IMessageDispatcher.CONNECTION_STATE_CHANGE, _isConnected);
            _serialPort = null;
        }
        _serialPort = null;
        System.out.println("DISCONNECTED");
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
        final String msgToSent = command.replace(",",".");

//        System.out.println("WRITE " + msgToSent);

        _messageDispatcher.dispatchMessage(IMessageDispatcher.RAW_DATA_MESSAGE_SENT, msgToSent);

        try
        {
            _serialPort.writeString(msgToSent);
        }
        catch (Exception e)
        {
            // do nothing
        }
    }

    @Override
    public boolean isConnected()
    {
        return _isConnected;
    }

    @Override
    public void setUseWireless(final boolean useWireless)
    {
        _baudRateSpeed = useWireless ? SerialPort.BAUDRATE_57600 : SerialPort.BAUDRATE_115200;
        disconnect();
    }

    @Override
    public boolean isConnecting()
    {
        return _isConnecting;
    }
}
