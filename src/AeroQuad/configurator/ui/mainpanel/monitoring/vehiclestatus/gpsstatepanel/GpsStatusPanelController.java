package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.gpsstatepanel;

import AeroQuad.configurator.messagesdispatcher.GpsDatas;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GpsStatusPanelController implements IGpsStatusPanelController
{
    private IGpsStatusPanel _panel;

    public GpsStatusPanelController(final IMessageDispatcher messageDispatcher)
    {
        messageDispatcher.addListener(IMessageDispatcher.GPS_INFOS_UPDATED, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                final GpsDatas gpsDatas = (GpsDatas)evt.getNewValue();
                updatePanelFromGpsDatas(gpsDatas);
            }
        });
    }

    @Override
    public void setPanel(final IGpsStatusPanel panel)
    {
        _panel = panel;
    }

    private void updatePanelFromGpsDatas(final GpsDatas gpsDatas)
    {
        final String gpsState = GpsState.fromInt(Integer.parseInt(gpsDatas.getGpsState()));
        _panel.setGpsState(gpsState);
        if (gpsState.equals("Detecting"))
        {
            setOthersFieldToZeros();
            return;
        }

        final String nbSats = gpsDatas.getNbSats();
        _panel.setNbSats(nbSats);

        final String speed = gpsDatas.getSpeed();
        _panel.setSpeed(speed);

        final String altitude = gpsDatas.getAltitude();
        _panel.setAltitude(altitude);

        final String heading = gpsDatas.getHeading();
        _panel.setHeading(heading);

        final String latitude = gpsDatas.getLatitude();
        _panel.setLatitude(latitude);

        final String longitude = gpsDatas.getLongitude();
        _panel.setLongitude(longitude);
    }

    private void setOthersFieldToZeros()
    {
        _panel.setNbSats("0");
        _panel.setSpeed("0");
        _panel.setAltitude("0");
        _panel.setHeading("0");
        _panel.setLatitude("0");
        _panel.setLongitude("0");
    }
}
