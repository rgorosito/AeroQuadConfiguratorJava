package AeroQuad.configurator.ui.mainpanel.setup.radiocalibration;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioCalibrationPanel extends JPanel implements IRadioCalibrationPanel
{
    private final IRadioCalibrationPanelController _controller;
    private final JButton _startStopButton = new JButton("Start");

    public RadioCalibrationPanel(final IRadioCalibrationPanelController controller, final JPanel radioPanelDisplay)
    {
        _controller = controller;

        _controller.setPanel(this);

        init(radioPanelDisplay);
    }

    private void init(final JPanel radioPanelDisplay)
    {
        setLayout(new BorderLayout());

        final JLabel headerLabel = new JLabel("Transmitter calibration");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(new LineBorder(Color.black,1));
        add(headerLabel, BorderLayout.NORTH);

        add(radioPanelDisplay, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(new LineBorder(Color.black, 1));
        final JPanel infoPanel = new JPanel(new BorderLayout());
        final JLabel trimInfoLabel = new JLabel("<HTML><CENTER>First, set all trim at neutral position</CENTER><HTML>");
        trimInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(trimInfoLabel, BorderLayout.NORTH);
        final JLabel bottomInfoLabel = new JLabel("<HTML><CENTER>Then press start button<br>move all stick and switch to all min and max position<br>and press finish.</CENTER><HTML>");
        bottomInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.setPreferredSize(new Dimension(600, 125));
        infoPanel.add(bottomInfoLabel, BorderLayout.CENTER);
        buttonPanel.add(infoPanel, BorderLayout.WEST);
        final JPanel startStopButtonPanel = new JPanel(new GridLayout(1,2));
        startStopButtonPanel.add(new JLabel());
        startStopButtonPanel.add(_startStopButton);
        buttonPanel.add(startStopButtonPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        _startStopButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.buttonPressed();
            }
        });
    }

    @Override
    public void setButtonText(final String text)
    {
        _startStopButton.setText(text);
    }
}
