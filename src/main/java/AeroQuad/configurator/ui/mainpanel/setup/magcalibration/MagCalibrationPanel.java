package AeroQuad.configurator.ui.mainpanel.setup.magcalibration;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MagCalibrationPanel extends JPanel implements IMagCalibrationPanel
{
    private final IMagCalibrationPanelController _controller;

    private final JLabel _xMinLabel = new JLabel("0");
    private final JSlider _xSlider = new JSlider(-1000,1000);
    private final JLabel _xMaxLabel = new JLabel("0");

    private final JLabel _yMinLabel = new JLabel("0");
    private final JSlider _ySlider = new JSlider(-1000,1000);
    private final JLabel _yMaxLabel = new JLabel("0");

    private final JLabel _zMinLabel = new JLabel("0");
    private final JSlider _zSlider = new JSlider(-1000,1000);
    private final JLabel _zMaxLabel = new JLabel("0");

    private final JButton _startFinishCalibrationButton = new JButton("Start");
    private final JButton _cancelButton = new JButton("Cancel");

    public MagCalibrationPanel(final IMagCalibrationPanelController controller)
    {
        _controller = controller;

        _controller.setPanel(this);

        init();
    }

    private void init()
    {
        setLayout(new BorderLayout());

        final JLabel headerLabel = new JLabel("Magnetometer calibration");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(new LineBorder(Color.black,1));
        add(headerLabel, BorderLayout.NORTH);

        initCenterPanel();

        initBottomPanel();
    }

    private void initCenterPanel()
    {
        final JPanel centerPanel = new JPanel(new GridLayout(1,3));
        centerPanel.setBorder(new LineBorder(Color.black,1));

        final JPanel xPanel = new JPanel(new BorderLayout());
        xPanel.setBorder(new LineBorder(Color.black,1));
        xPanel.add(_xMaxLabel, BorderLayout.NORTH);
        _xMaxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _xSlider.setForeground(Color.black);
        _xSlider.setOrientation(JSlider.VERTICAL);
        _xSlider.setMajorTickSpacing(200);
        _xSlider.setMinorTickSpacing(50);
        _xSlider.setPaintTicks(true);
        _xSlider.setPaintLabels(true);
        _xSlider.setEnabled(false);
        xPanel.add(_xSlider,BorderLayout.CENTER);
        _xMinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        xPanel.add(_xMinLabel,BorderLayout.SOUTH);
        centerPanel.add(xPanel);

        final JPanel yPanel = new JPanel(new BorderLayout());
        yPanel.setBorder(new LineBorder(Color.black,1));
        yPanel.add(_yMaxLabel,BorderLayout.NORTH);
        _yMaxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _ySlider.setForeground(Color.black);
        _ySlider.setOrientation(JSlider.VERTICAL);
        _ySlider.setMajorTickSpacing(200);
        _ySlider.setMinorTickSpacing(50);
        _ySlider.setPaintTicks(true);
        _ySlider.setPaintLabels(true);
        _ySlider.setEnabled(false);
        yPanel.add(_ySlider,BorderLayout.CENTER);
        _yMinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yPanel.add(_yMinLabel,BorderLayout.SOUTH);
        centerPanel.add(yPanel);

        final JPanel zPanel = new JPanel(new BorderLayout());
        zPanel.setBorder(new LineBorder(Color.black,1));
        zPanel.add(_zMaxLabel,BorderLayout.NORTH);
        _zMaxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _zSlider.setForeground(Color.black);
        _zSlider.setOrientation(JSlider.VERTICAL);
        _zSlider.setMajorTickSpacing(200);
        _zSlider.setMinorTickSpacing(50);
        _zSlider.setPaintTicks(true);
        _zSlider.setPaintLabels(true);
        _zSlider.setEnabled(false);
        zPanel.add(_zSlider,BorderLayout.CENTER);
        _zMinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zPanel.add(_zMinLabel,BorderLayout.SOUTH);
        centerPanel.add(zPanel);

        add(centerPanel,BorderLayout.CENTER);
    }

    private void initBottomPanel()
    {
        final JPanel levelCalibrationPanel = new JPanel(new BorderLayout());
        levelCalibrationPanel.setBorder(new LineBorder(Color.black,1));
        final JLabel levelInfoLabel = new JLabel("<HTML><CENTER>Press start button, rotate your Aeroquad in all direction<br>of the 3 normal axis and then press finish when done.<br>All axis value should min and maxed out</CENTER><HTML>");
        levelInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelInfoLabel.setPreferredSize(new Dimension(600,125));
        levelCalibrationPanel.add(levelInfoLabel, BorderLayout.WEST);
        final JPanel calibrateLevelButtonPanel = new JPanel(new GridLayout(1,2));

        calibrateLevelButtonPanel.add(_startFinishCalibrationButton);
        calibrateLevelButtonPanel.add(_cancelButton);
        levelCalibrationPanel.add(calibrateLevelButtonPanel, BorderLayout.CENTER);
        add(levelCalibrationPanel, BorderLayout.SOUTH);

        _startFinishCalibrationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.buttonPressed();
            }
        });
        _cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.cancelCalibration();
            }
        });
        _cancelButton.setEnabled(false);
    }

    @Override
    public void setXMinValue(final String value)
    {
        _xMinLabel.setText(value);
    }

    @Override
    public void setXMaxValue(final String value)
    {
        _xMaxLabel.setText(value);
    }

    @Override
    public void setXSliderValue(final int value)
    {
        _xSlider.setValue(value);
    }

    @Override
    public void setYMinValue(final String value)
    {
        _yMinLabel.setText(value);
    }

    @Override
    public void setYMaxValue(final String value)
    {
        _yMaxLabel.setText(value);
    }

    @Override
    public void setYSliderValue(final int value)
    {
        _ySlider.setValue(value);
    }

    @Override
    public void setZMinValue(final String value)
    {
        _zMinLabel.setText(value);
    }

    @Override
    public void setZMaxValue(final String value)
    {
        _zMaxLabel.setText(value);
    }

    @Override
    public void setZSliderValue(final int value)
    {
        _zSlider.setValue(value);
    }

    @Override
    public void setButtonText(final String text)
    {
        _startFinishCalibrationButton.setText(text);
    }

    @Override
    public void setCancelEnable(final boolean enable)
    {
        _cancelButton.setEnabled(enable);
    }
}
