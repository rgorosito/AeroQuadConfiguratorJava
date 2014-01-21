package AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus;

import AeroQuad.configurator.messageDispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.IConfiguratorController;

public interface IVehicleStatusController extends IConfiguratorController
{
    IMessageDispatcher getMessageDispatcher();

    void setPanel(IVehicleStatusPanel panel);
}
