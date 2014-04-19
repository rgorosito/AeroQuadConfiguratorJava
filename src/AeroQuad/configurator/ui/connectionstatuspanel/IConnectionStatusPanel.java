package AeroQuad.configurator.ui.connectionstatuspanel;

public interface IConnectionStatusPanel
{
    final String CONNECTED = "CONNECTED";
    final String DISCONNECTED = "DISCONNECTED";

    void setConnected(boolean connected);

    void setUsage(double usage);

    void setCommPortOpen(String comPort);

    void setBaudRate(String boadRate);
}
