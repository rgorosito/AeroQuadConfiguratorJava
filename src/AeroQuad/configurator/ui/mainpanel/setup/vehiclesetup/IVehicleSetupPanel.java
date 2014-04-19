package AeroQuad.configurator.ui.mainpanel.setup.vehiclesetup;

import AeroQuad.configurator.messagesdispatcher.FlightConfigType;
import AeroQuad.configurator.messagesdispatcher.ReceiverType;

public interface IVehicleSetupPanel
{
    void selectFlightConfigType(FlightConfigType flightConfigType);

    void setReceiverType(ReceiverType receiverType);

    void setYawIsReversed(boolean reversed);
}
