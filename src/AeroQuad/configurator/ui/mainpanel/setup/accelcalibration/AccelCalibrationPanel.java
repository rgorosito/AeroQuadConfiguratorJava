package AeroQuad.configurator.ui.mainpanel.setup.accelcalibration;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class AccelCalibrationPanel extends JPanel implements IAccelCalibrationPanel
{

    private final JLabel _imageLabel = new JLabel();
    private final JLabel _textLabel = new JLabel("Press start button");
    private JButton _startButton;
    private JButton _cancelbutton;

    public AccelCalibrationPanel(final IAccelCalibrationPanelController accelCalibrationController)
    {
        init();
    }

    private void init()
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,3));

        _imageLabel.setIcon(new ImageIcon(topUpImage));
        _imageLabel.setBorder(new LineBorder(Color.black, 1));
        _imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(_imageLabel, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(_textLabel, BorderLayout.WEST);
        _textLabel.setPreferredSize(new Dimension(600,125));
        _textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        final JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        _startButton = new JButton("Start");
        _startButton.setPreferredSize(new Dimension(0,125));
        buttonPanel.add(_startButton);
        _cancelbutton = new JButton("Cancel");
        buttonPanel.add(_cancelbutton);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
