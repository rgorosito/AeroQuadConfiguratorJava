package AeroQuad.configurator.ui.connectionpanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class ConnectionStatusPanel extends JPanel implements IConnectionStatusPanel
{
    private final JLabel _connectionStateLabel = new JLabel(DISCONNECTED,SwingConstants.CENTER);
    private final JCheckBox _wirelessCheckBox = new JCheckBox("Wireless");

    private IConnectionStatusPanelController _controller;
    private boolean _isConnected = false;
    private double _usage;
    private String _baudRate = "";
    private String _comPort = "";
    private boolean _wasConnected = false;

    public ConnectionStatusPanel(final IConnectionStatusPanelController controller)
    {
        _controller = controller;
        _controller.setPanel(this);
        setLayout(new BorderLayout());

        _connectionStateLabel.setOpaque(true);
        add(_connectionStateLabel, BorderLayout.CENTER);
        add(_wirelessCheckBox, BorderLayout.EAST);

        _wirelessCheckBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _controller.useWireless(_wirelessCheckBox.isSelected());
            }
        });
    }

    @Override
    public void setConnected(final boolean isConnected)
    {
        _wasConnected = _isConnected;
        _isConnected = isConnected;
        updateStatusLabel();
    }



    @Override
    public void setUsage(final double usage)
    {
        _usage = usage;
        updateStatusLabel();
    }

    @Override
    public void setCommPortOpen(final String comPort)
    {
        _comPort = comPort;

        updateStatusLabel();
    }

    @Override
    public void setBaudRate(final String boadRate)
    {
        _baudRate = boadRate;
    }


    private void updateStatusLabel()
    {
        if (_wasConnected && !_isConnected)
        {
            _comPort = "";
            _baudRate = "";
        }
        if (!_isConnected && _comPort.length() > 0)
        {
            _connectionStateLabel.setForeground(Color.white);
            _connectionStateLabel.setText("OPEN " + _comPort + " @ " +_baudRate);
        }
        else
        {
            _connectionStateLabel.setBackground(Color.GREEN);
            final DecimalFormat decimalFormat = new DecimalFormat("0.00");
            final String usageString = decimalFormat.format(_usage);
            _connectionStateLabel.setForeground(Color.black);
            _connectionStateLabel.setText(_isConnected ? CONNECTED + " Usage = " + usageString + " %": DISCONNECTED);
        }
        _wasConnected = _isConnected;
    }
}
