package AeroQuad.configurator.ui;

import AeroQuad.configurator.communication.ISerialCommunicator;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;
import AeroQuad.configurator.ui.connectionpanel.ConnectionStatusPanel;
import AeroQuad.configurator.ui.connectionpanel.ConnectionPanelController;
import AeroQuad.configurator.ui.mainmenue.MainMenuController;
import AeroQuad.configurator.ui.mainmenue.MainMenuPanel;
import AeroQuad.configurator.ui.mainpanel.MainPanel;
import AeroQuad.configurator.ui.mainpanel.MainPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.MonitoringPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.MonitoringPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.SensorsMonitoringController;
import AeroQuad.configurator.ui.mainpanel.monitoring.sensorsmonitoring.SensorsMonitoringPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.VehicleStatusPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.motordisplay.MotorDisplayController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.motordisplay.MotorDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel.OtherSensorsStatusPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.otherssensorsstatuspanel.OtherSensorsStatusPanelController;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.receiverdisplay.ReceiverDisplayPanel;
import AeroQuad.configurator.ui.mainpanel.monitoring.vehiclestatus.receiverdisplay.ReceiverPanelController;
import AeroQuad.configurator.ui.mainpanel.setup.SetupPanel;
import AeroQuad.configurator.ui.mainpanel.setup.SetupPanelController;
import AeroQuad.configurator.ui.mainpanel.tuning.TuningPanel;
import AeroQuad.configurator.ui.mainpanel.tuning.TuningPanelController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;
import java.util.LinkedList;

public class AQConfiguratorMainFrame extends JFrame
{
    public AQConfiguratorMainFrame(final ISerialCommunicator communicator,
                                   final IMessageDispatcher messageDispatcher)
    {
        super("AeroQuad Configurator v4.0");
        initLookAndFeelUi();
        initUi(communicator, messageDispatcher);
    }


    private void initLookAndFeelUi()
    {
        // Panel
        UIManager.put("Panel.background", Color.lightGray);

        // Label
        UIManager.put("Label.foreground", Color.white);
//        UIManager.put("Label.font", UiUtils.MEDIUM_FONT);

        // Text Field
//        UIManager.put("TextField.font", UiUtils.SMALL_FONT);
        UIManager.put("TextField.border", new LineBorder(new ColorUIResource(50, 50, 50), 1));

        // Text Area
//        UIManager.put("TextArea.font", UiUtils.MEDIUM_FONT);
//        UIManager.put("TextArea.background", UiUtils.OFF_WHITE_COLOR);
        UIManager.put("TextArea.margin", new InsetsUIResource(8, 10, 10, 0));

        // Buttons
        final LinkedList buttonGradient = new LinkedList();
        buttonGradient.add(0.3);
        buttonGradient.add(0.0);
        buttonGradient.add(new ColorUIResource(190, 190, 190));
        buttonGradient.add(new ColorUIResource(220, 220, 220));
        buttonGradient.add(new ColorUIResource(190, 190, 190));

//        UIManager.put("Button.font", UiUtils.SMALL_FONT);
        UIManager.put("Button.gradient", buttonGradient);
        UIManager.put("Button.select", Color.green);
        UIManager.put("Button.focus", new ColorUIResource(220, 220, 220));
        UIManager.put("Button.border", new LineBorder(Color.black, 1));


//        UIManager.put("ToggleButton.font", UiUtils.SMALL_FONT);
        UIManager.put("ToggleButton.gradient", buttonGradient);
        UIManager.put("ToggleButton.select", Color.darkGray);
        UIManager.put("ToggleButton.focus", Color.DARK_GRAY);
        UIManager.put("ToggleButton.border", new LineBorder(Color.black, 1));

        // Separator
        UIManager.put("Separator.background", Color.black);
        UIManager.put("Separator.foreground", Color.darkGray);

        // ComboBox
//        UIManager.put("ComboBox.font", UiUtils.NORMAL_FONT);


        // ScollPane
        UIManager.put("ScrollPane.border", new LineBorder(Color.black, 0));

        // TableHeader
//        UIManager.put("TableHeader.font", UiUtils.SMALL_FONT);
        UIManager.put("TableHeader.background", Color.white);
        UIManager.put("TableHeader.foreground", Color.black);
        UIManager.put("TableHeader.cellBorder", new LineBorder(Color.darkGray, 1));

        // Table
        UIManager.put("Table.background", Color.black);
        UIManager.put("Table.foreground", Color.white);
//        UIManager.put("Table.font", UiUtils.SMALL_FONT);
        UIManager.put("Table.gridColor", Color.darkGray);
    }

    private void initUi(final ISerialCommunicator communicator, final IMessageDispatcher messageDispatcher)
    {
        final JPanel mainContainer = new JPanel(new BorderLayout());

        final MainPanelController mainPanelController = new MainPanelController(communicator);
        {
            final ReceiverDisplayPanel receiverPanel = new ReceiverDisplayPanel(new ReceiverPanelController(messageDispatcher));
            final VehicleStatusController vehicleStatusController = new VehicleStatusController(messageDispatcher, communicator);
            final MotorDisplayPanel motorCommandDisplayPanel = new MotorDisplayPanel(new MotorDisplayController(messageDispatcher));
            final OtherSensorsStatusPanel otherSensorsStatusPanel = new OtherSensorsStatusPanel(new OtherSensorsStatusPanelController(messageDispatcher));
            final VehicleStatusPanel vehicleStatusPanel = new VehicleStatusPanel(vehicleStatusController, receiverPanel, motorCommandDisplayPanel,otherSensorsStatusPanel);
            final MotorDisplayPanel motorCommandPanel = new MotorDisplayPanel(new MotorDisplayController(messageDispatcher));
            final SensorsMonitoringPanel sensorsMonitoringPanel = new SensorsMonitoringPanel(new SensorsMonitoringController(messageDispatcher,communicator));


            final MonitoringPanel monitoringPanel = new MonitoringPanel(
                    new MonitoringPanelController(communicator),
                    vehicleStatusPanel,
                    sensorsMonitoringPanel,
                    motorCommandPanel);

            final SetupPanel setupPanel = new SetupPanel(new SetupPanelController(communicator));

            final TuningPanel tuningPanel = new TuningPanel(new TuningPanelController(communicator));

            final MainPanel mainPanel = new MainPanel(mainPanelController, monitoringPanel, setupPanel, tuningPanel);
            mainContainer.add(mainPanel, BorderLayout.CENTER);
        }

        {
            final MainMenuPanel mainMenue = new MainMenuPanel(new MainMenuController(communicator, mainPanelController));
            mainContainer.add(mainMenue, BorderLayout.WEST);
        }

        {
            final ConnectionStatusPanel connectionPanel = new ConnectionStatusPanel(new ConnectionPanelController(communicator,messageDispatcher));
            mainContainer.add(connectionPanel, BorderLayout.SOUTH);
        }

        getContentPane().add(mainContainer);
        pack();
        setVisible(true);
    }
}
