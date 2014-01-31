package AeroQuad.configurator.ui.connectionpanel;


import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;


public class ConnectionStatusPanel extends JPanel implements IConnectionStatusPanel
{
    private final JLabel _connectionStateLabel = new JLabel(DISCONNECTED,SwingConstants.CENTER);

    private IConnectionStatusPanelController _controller;
    private boolean _isConnected = false;
    private Color _defaultBackgroundColor;
    private double _usage;

    public ConnectionStatusPanel(final IConnectionStatusPanelController controller)
    {
        _controller = controller;
        _controller.setPanel(this);
        setLayout(new BorderLayout());


        _connectionStateLabel.setOpaque(true);
        add(_connectionStateLabel, BorderLayout.CENTER);
    }


    @Override
    public void setConnected(final boolean isConnected)
    {
        _isConnected = isConnected;
        updateStatusLabel();
    }

    private void updateStatusLabel()
    {
        _connectionStateLabel.setBackground(_isConnected ? Color.GREEN : _defaultBackgroundColor);
        final DecimalFormat decimalFormat = new DecimalFormat("0.00");
        final String usageString = decimalFormat.format(_usage);
        _connectionStateLabel.setForeground(Color.black);
        _connectionStateLabel.setText(_isConnected ? CONNECTED + " Usage = " + usageString + " %": DISCONNECTED);
    }

    @Override
    public void setUsage(final double usage)
    {
        _usage = usage;
        updateStatusLabel();
    }
}
