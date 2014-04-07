package AeroQuad.configurator.communication;


import AeroQuad.configurator.communication.messaging.request.IRequest;

import java.util.List;

public interface ISerialCommunicator
{
    final static String CONNECTION_TIMEOUT_PROPERTY_KEY = "connection.timeout";

    List<String> getComPortAvailable();


    void connect(String commPort);

    void disconnect();

    void sendRequest(IRequest vehicleStatusRequest);

    void sendCommand(String command);

    boolean isConnected();

    void setUseWireless(boolean useWireless);

    boolean isConnecting();
}
