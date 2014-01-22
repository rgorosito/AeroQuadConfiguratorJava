package AeroQuad.configurator.ui.connectionpanel;

public interface IConnectionStatusPanel
{
    final String CONNECTED = "CONNECTED";
    final String DISCONNECTED = "DISCONNECTED";

    void setConnected(boolean connected);

    void setUsage(double usage);
}
