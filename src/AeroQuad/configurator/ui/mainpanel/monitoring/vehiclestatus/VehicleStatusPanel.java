package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus;

import AeroQuad.configurator.messagesdispatcher.VehicleAttitude;
import AeroQuad.configurator.ui.artificialhorizon.ArtificialHorizonPanel;

import javax.swing.*;
import java.awt.*;

public class VehicleStatusPanel extends JPanel implements IVehicleStatusPanel
{
    private final IVehicleStatusController _controller;

    final ArtificialHorizonPanel _artificialHorizonPanel = new ArtificialHorizonPanel();
    private final JPanel _receiverPanel;
    private final JPanel _motorCommandPanel;
    private final JPanel _gpsStatusPanel;
    private final JPanel _otherSensorsStatusPanel;

    private final JPanel _bottomPanel = new JPanel();

    public VehicleStatusPanel(
            final IVehicleStatusController controller,
            final JPanel receiverPanel,
            final JPanel motorCommandPanel,
            final JPanel otherSensorsStatusPanel,
            final JPanel gpsStatusPanel)
    {
        _controller = controller;
        _receiverPanel = receiverPanel;
        _motorCommandPanel = motorCommandPanel;
        _gpsStatusPanel = gpsStatusPanel;
        _otherSensorsStatusPanel = otherSensorsStatusPanel;
        _controller.setPanel(this);

        init();
    }

    private void init()
    {

        setLayout(new GridLayout(2, 1));
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(_artificialHorizonPanel, BorderLayout.WEST);
        topPanel.add(_motorCommandPanel, BorderLayout.CENTER);
        add(topPanel);


        _bottomPanel.setLayout(new BoxLayout(_bottomPanel, BoxLayout.X_AXIS));
        _bottomPanel.add(_otherSensorsStatusPanel);
        _bottomPanel.add(_receiverPanel);
        add(_bottomPanel);
    }

    @Override
    public void setVehicleAttitude(final VehicleAttitude vehicleAttitude)
    {
        _artificialHorizonPanel.setVehicleAttitude(vehicleAttitude);
    }

    @Override
    public void setHaveGps(final boolean haveGps)
    {
        _bottomPanel.remove(_otherSensorsStatusPanel);
        _bottomPanel.remove(_receiverPanel);
        _bottomPanel.remove(_gpsStatusPanel);

        _bottomPanel.add(_otherSensorsStatusPanel);
        if (haveGps)
        {
            _bottomPanel.add(_gpsStatusPanel);
        }
        _bottomPanel.add(_receiverPanel);
    }
}
