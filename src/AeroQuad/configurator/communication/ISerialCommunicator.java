package AeroQuad.configurator.communication;


import AeroQuad.configurator.communication.messaging.request.IRequest;

import java.util.List;

public interface ISerialCommunicator
{
    final static String CONNECTION_TIMEOUT_PROPERTY_KEY = "connection.timeout";

    final int BOAD_RATE_4800 = 4800;
    final int BOAD_RATE_9400 = 9400;
    final int BOAD_RATE_14000 = 14000;
    final int BOAD_RATE_19200 = 19200;
    final int BOAD_RATE_28800 = 28800;
    final int BOAD_RATE_38400 = 38400;
    final int BOAD_RATE_57600 = 57600;
    final int BOAD_RATE_115200 = 115200;
    final int DEFAULT_BOAD_RATE = BOAD_RATE_115200;

    List<String> getComPortAvailable();

    void connect(int baudRate, String defaultPort);

    void connect(String commPort);

    void disconnect(final boolean b);

    void sendRequest(IRequest vehicleStatusRequest);

    void sendCommand(String command);

    boolean isConnected();
}
