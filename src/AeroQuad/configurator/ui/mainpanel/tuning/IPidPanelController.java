package AeroQuad.configurator.ui.mainpanel.tuning;

public interface IPidPanelController
{
    void setUserLevel(UserLevel userLevel);

    boolean isSyncked();

    void processSyncing();
}
