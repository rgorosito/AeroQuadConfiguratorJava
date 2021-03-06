package AeroQuad.configurator.ui.mainpanel.setup.accelcalibration;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AccelCalibrationPanel extends JPanel implements IAccelCalibrationPanel
{

    private final JLabel _imageLabel = new JLabel();
    private final JLabel _textLabel = new JLabel(FLAT_UP_TEXT);
    private final IAccelCalibrationPanelController _controller;
    private JButton _nextButton;
    private JButton _cancelbutton;
    private JProgressBar _progressBar;

    public AccelCalibrationPanel(final IAccelCalibrationPanelController controller)
    {
        _controller = controller;

        _controller.setPanel(this);

        init();

        _nextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.nextButtonPressed();
            }
        });

        _cancelbutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.cancelButtonPressed();
            }
        });
    }

    private void init()
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,3));

        final JPanel calibrationPanel = new JPanel(new BorderLayout());
        _imageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(FLAT_UP_IMAGE_NAME)));
        _imageLabel.setBorder(new LineBorder(Color.black, 1));
        _imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        calibrationPanel.add(_imageLabel, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new LineBorder(Color.black,1));
        bottomPanel.add(_textLabel, BorderLayout.WEST);
        _textLabel.setPreferredSize(new Dimension(600,125));
        _textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        final JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        _nextButton = new JButton("Start");
        _nextButton.setPreferredSize(new Dimension(0, 125));
        buttonPanel.add(_nextButton);
        _cancelbutton = new JButton("Cancel");
        buttonPanel.add(_cancelbutton);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        final JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.setBorder(new LineBorder(Color.black,1));
        progressPanel.add(new JLabel(PROGRESS_STRING), BorderLayout.WEST);
        _progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
        _progressBar.setMinimum(0);
        _progressBar.setMaximum(100);
        progressPanel.add(_progressBar, BorderLayout.CENTER);
        bottomPanel.add(progressPanel, BorderLayout.NORTH);

        calibrationPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(calibrationPanel, BorderLayout.CENTER);

        final JPanel levelCalibrationPanel = new JPanel(new BorderLayout());
        levelCalibrationPanel.setBorder(new LineBorder(Color.black,1));
        final JLabel levelInfoLabel = new JLabel("<HTML>To calibrate vehicle level, press here after the accel calibration procedure</HTML>");
        levelInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelInfoLabel.setPreferredSize(new Dimension(600,125));
        levelCalibrationPanel.add(levelInfoLabel, BorderLayout.WEST);
        final JPanel calibrateLevelButtonPanel = new JPanel(new GridLayout(1,2));
        calibrateLevelButtonPanel.add(new JLabel());
        final JButton calibrateLevelButton = new JButton("<HTML>Calibrate level</HTML>");
        calibrateLevelButtonPanel.add(calibrateLevelButton);

        levelCalibrationPanel.add(calibrateLevelButtonPanel, BorderLayout.CENTER);
        calibrateLevelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.calibrateLevel();
            }
        });

        add(levelCalibrationPanel, BorderLayout.SOUTH);

        _cancelbutton.setEnabled(false);
    }

    @Override
    public void setCancelButtonEnabled(final boolean enabled)
    {
        _cancelbutton.setEnabled(enabled);
    }

    @Override
    public void setNextButtonEnabeld(final boolean enabled)
    {
        _nextButton.setEnabled(enabled);
    }

    @Override
    public void setProgress(final int progress)
    {
        _progressBar.setValue(progress);
    }

    @Override
    public void setCurrentCalibrationStep(final CalibrationStep currentCalibrationStep)
    {
        switch(currentCalibrationStep)
        {
            case FLAT_UP:
                _imageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(FLAT_UP_IMAGE_NAME)));
                _nextButton.setText("Start");
                _textLabel.setText(FLAT_UP_TEXT);
                return;
            case FLAT_DOWN:
                _imageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(FLAT_DOWN_IMAGE_NAME)));
                _nextButton.setText("Next");
                _textLabel.setText(FLAT_DOWN_TEXT);
                return;
            case LEFT_SIDE_DOWN:
                _imageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(LEFT_SIDE_DOWN_IMAGE_NAME)));
                _nextButton.setText("Next");
                _textLabel.setText(LEFT_SIDE_DOWN_TEXT);
                return;
            case RIGHT_SIDE_DOWN:
                _imageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(RIGHT_SIDE_IMAGE_NAME)));
                _nextButton.setText("Next");
                _textLabel.setText(RIGHT_SIDE_DOWN_TEXT);
                return;
            case NOSE_UP:
                _imageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(NOSE_UP_IMAGE_NAME)));
                _nextButton.setText("Next");
                _textLabel.setText(NOSE_UP_TEXT);
                return;
            case NOSE_DOWN:
                _imageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(NOSE_DOWN_IMAGE_NAME)));
                _nextButton.setText("Next");
                _textLabel.setText(NOSE_DOWN_TEXT);
                return;
            case FINISHED:
                _imageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(FLAT_UP_IMAGE_NAME)));
                _nextButton.setText("Finish");
                _textLabel.setText(FINISH_TEXT);
        }
    }
}
