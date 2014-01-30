package AeroQuad.configurator.ui.mainpanel.tuning.attitude;

import AeroQuad.configurator.ui.mainpanel.tuning.IPidPanelController;

public interface IAttitudePidPanelController extends IPidPanelController
{
    void setPanel(IAttitudePidPanel panel);
}
