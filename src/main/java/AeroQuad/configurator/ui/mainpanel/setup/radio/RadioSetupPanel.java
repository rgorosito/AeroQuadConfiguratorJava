package AeroQuad.configurator.ui.mainpanel.setup.radio;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioSetupPanel extends JPanel implements IRadioSetupPanel
{
    private final JToggleButton _channelDetectionButton = new JToggleButton(CHANNEL_DETECTION);
    private final JToggleButton _radioCalibrationButton = new JToggleButton(CHANNEL_CALIBRATION);

    private final ButtonGroup _buttonGroup = new ButtonGroup();
    private final IRadioSetupController _controller;

    private final CardLayout _cardLayout = new CardLayout();
    private final JPanel _cardLayoutPanel = new JPanel(_cardLayout);

    public RadioSetupPanel(final IRadioSetupController controller)
    {
        _controller = controller;
        _controller.setPanel(this);
        setLayout(new BorderLayout());

        initButtonPanel();

        initSubPanels();
    }

    private void initButtonPanel()
    {
        final JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        buttonPanel.add(_channelDetectionButton);
        buttonPanel.add(_radioCalibrationButton);
        add(buttonPanel, BorderLayout.WEST);
        _buttonGroup.add(_channelDetectionButton);
        _buttonGroup.add(_radioCalibrationButton);
        _channelDetectionButton.doClick();


        _channelDetectionButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.channelDetectionSelected();
            }
        });
        _radioCalibrationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.channelCalibrationSelected();
            }
        });
    }

    private void initSubPanels()
    {
        _cardLayoutPanel.add(_controller.getRadioChannelDetectionPanel(), CHANNEL_DETECTION);
        _cardLayoutPanel.add(_controller.getRadioCalibrationPanel(), CHANNEL_CALIBRATION);
        add(_cardLayoutPanel,BorderLayout.CENTER);
    }

    @Override
    public void loadCurrentPanel(final String panelId)
    {
        _cardLayout.show(_cardLayoutPanel, panelId);
    }
}
