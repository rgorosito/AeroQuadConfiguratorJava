package AeroQuad.configurator.ui.mainpanel.setup.radio.radiochanneldetection;

import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioChannelDetectionPanel extends JPanel implements IRadioChannelDetectionPanel
{
    private final JProgressBar _throttleProgressBar = new JProgressBar(0,5);
    private final JLabel _throttleDetectionStateLabel = new JLabel(UNDETECTED);
    private final JProgressBar _rollProgressBar = new JProgressBar(0,5);
    private final JLabel _rollDetectionStateLabel = new JLabel(UNDETECTED);
    private final JProgressBar _pitchProgressBar = new JProgressBar(0,5);
    private final JLabel _pitchDetectionStateLabel = new JLabel(UNDETECTED);
    private final JProgressBar _yawProgressBar = new JProgressBar(0,5);
    private final JLabel _yawDetectionStateLabel = new JLabel(UNDETECTED);
    private final JProgressBar _modeProgressBar = new JProgressBar(0,5);
    private final JLabel _modeDetectionStateLabel = new JLabel(UNDETECTED);

    final JPanel _aux1Panel = new JPanel(new GridLayout(1,3));
    private final JProgressBar _aux1ProgressBar = new JProgressBar(0,5);
    private final JLabel _aux1DetectionStateLabel = new JLabel(UNDETECTED);

    final JPanel _aux2Panel = new JPanel(new GridLayout(1,3));
    private final JProgressBar _aux2ProgressBar = new JProgressBar(0,5);
    private final JLabel _aux2DetectionStateLabel = new JLabel(UNDETECTED);

    final JPanel _aux3Panel = new JPanel(new GridLayout(1,3));
    private final JProgressBar _aux3ProgressBar = new JProgressBar(0,5);
    private final JLabel _aux3DetectionStateLabel = new JLabel(UNDETECTED);

    private final JLabel _indicationTextLabel = new JLabel();

    private final JButton _startButton = new JButton("Start");
    private final JButton _cancelButton = new JButton("Cancel");

    private final IRadioChannelDetectionController _controller;
    private String _userMessage = "";
    private boolean _visible = true;

    public RadioChannelDetectionPanel(final IRadioChannelDetectionController controller)
    {
        _controller = controller;
        _controller.setPanel(this);

        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createLineBorder(Color.black));

        final JPanel channelDisplayPanelConntainer = new JPanel();
        channelDisplayPanelConntainer.setLayout(new BoxLayout(channelDisplayPanelConntainer, BoxLayout.Y_AXIS));


        final JPanel rollPanel = new JPanel(new GridLayout(1,3));
        rollPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel rollLabel = new JLabel("Roll");
        rollLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rollLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        rollLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        rollPanel.add(rollLabel);
        _rollProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        rollPanel.add(_rollProgressBar);
        rollPanel.add(_rollDetectionStateLabel);
        _rollDetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(rollPanel);

        final JPanel pitchPanel = new JPanel(new GridLayout(1,3));
        pitchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel pitchLabel = new JLabel("Pitch");
        pitchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pitchLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        pitchLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        pitchPanel.add(pitchLabel);
        _pitchProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        pitchPanel.add(_pitchProgressBar);
        pitchPanel.add(_pitchDetectionStateLabel);
        _pitchDetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(pitchPanel);

        final JPanel yawPanel = new JPanel(new GridLayout(1,3));
        yawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel yawLabel = new JLabel("Yaw");
        yawLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yawLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        yawLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        yawPanel.add(yawLabel);
        _yawProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        yawPanel.add(_yawProgressBar);
        yawPanel.add(_yawDetectionStateLabel);
        _yawDetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(yawPanel);

        final JPanel throttlePanel = new JPanel(new GridLayout(1,3));
        throttlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel throttleLabel = new JLabel("Throttle");
        throttleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        throttleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        throttleLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        throttlePanel.add(throttleLabel);
        _throttleProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        throttlePanel.add(_throttleProgressBar);
        throttlePanel.add(_throttleDetectionStateLabel);
        _throttleDetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(throttlePanel);

        final JPanel modePanel = new JPanel(new GridLayout(1,3));
        modePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel modeLabel = new JLabel("Mode");
        modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        modeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        modeLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        modePanel.add(modeLabel);
        _modeProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        modePanel.add(_modeProgressBar);
        modePanel.add(_modeDetectionStateLabel);
        _modeDetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(modePanel);

        _aux1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel aux1Label = new JLabel("Aux1");
        aux1Label.setHorizontalAlignment(SwingConstants.CENTER);
        aux1Label.setBorder(BorderFactory.createLineBorder(Color.black));
        aux1Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _aux1Panel.add(aux1Label);
        _aux1ProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        _aux1Panel.add(_aux1ProgressBar);
        _aux1Panel.add(_aux1DetectionStateLabel);
        _aux1DetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(_aux1Panel);
        _aux1Panel.setVisible(false);

        _aux2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel aux2Label = new JLabel("Aux2");
        aux2Label.setHorizontalAlignment(SwingConstants.CENTER);
        aux2Label.setBorder(BorderFactory.createLineBorder(Color.black));
        aux2Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _aux2Panel.add(aux2Label);
        _aux2ProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        _aux2Panel.add(_aux2ProgressBar);
        _aux2Panel.add(_aux2DetectionStateLabel);
        _aux2DetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(_aux2Panel);
        _aux2Panel.setVisible(false);

        _aux3Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel aux3Label = new JLabel("Aux3");
        aux3Label.setHorizontalAlignment(SwingConstants.CENTER);
        aux3Label.setBorder(BorderFactory.createLineBorder(Color.black));
        aux3Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _aux3Panel.add(aux3Label);
        _aux3ProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        _aux3Panel.add(_aux3ProgressBar);
        _aux3Panel.add(_aux3DetectionStateLabel);
        _aux3DetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(_aux3Panel);
        _aux3Panel.setVisible(false);
        add(channelDisplayPanelConntainer, BorderLayout.CENTER);

        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setPreferredSize(new Dimension(300,0));

        _indicationTextLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _indicationTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(_indicationTextLabel, BorderLayout.CENTER);
        final JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.setPreferredSize(new Dimension(0, 125));
        buttonPanel.add(_startButton);
        buttonPanel.add(_cancelButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.EAST);

        _startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                if (_startButton.getText().equals(FINISH))
                {
                    _controller.completeDetection();
                }
                else
                {
                    _controller.startDetection();
                }
            }
        });

        _cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.cancelDetection();
            }
        });

        _cancelButton.setEnabled(false);
    }


    @Override
    public void setAux1Visible(final boolean visible)
    {
        _aux1Panel.setVisible(visible);
    }

    @Override
    public void setAux2Visible(final boolean visible)
    {
        _aux2Panel.setVisible(visible);
    }

    @Override
    public void setAux3Visible(final boolean visible)
    {
        _aux3Panel.setVisible(visible);
    }

    @Override
    public void setCancelButtonEnabled(final boolean enabled)
    {
        _cancelButton.setEnabled(enabled);
    }

    @Override
    public void setStartButtonEnabled(final boolean enabled)
    {
        _startButton.setEnabled(enabled);
    }

    @Override
    public void setCurrentChannelDetected(final ReceiverChannel currentDetectingChannel)
    {
        _userMessage = "Move your " + currentDetectingChannel.toString() + " stick";
        _indicationTextLabel.setText(_userMessage);
    }



    @Override
    public void updateUserFeedback(final ReceiverChannel currentDetectingChannel, final int cpt)
    {
        switch (currentDetectingChannel)
        {
            case THROTTLE:
                updateUserFeedbackWidget(_throttleProgressBar, _throttleDetectionStateLabel, cpt);
                break;
            case ROLL:
                updateUserFeedbackWidget(_rollProgressBar, _rollDetectionStateLabel, cpt);
                break;
            case PITCH:
                updateUserFeedbackWidget(_pitchProgressBar, _pitchDetectionStateLabel, cpt);
                break;
            case YAW:
                updateUserFeedbackWidget(_yawProgressBar, _yawDetectionStateLabel, cpt);
                break;
            case MODE:
                updateUserFeedbackWidget(_modeProgressBar, _modeDetectionStateLabel, cpt);
                break;
            case AUX1:
                updateUserFeedbackWidget(_aux1ProgressBar, _aux1DetectionStateLabel, cpt);
                break;
            case AUX2:
                updateUserFeedbackWidget(_aux2ProgressBar, _aux2DetectionStateLabel, cpt);
                break;
            case AUX3:
                updateUserFeedbackWidget(_aux3ProgressBar, _aux3DetectionStateLabel, cpt);
                break;
        }
    }

    private void updateUserFeedbackWidget(final JProgressBar progressBar, final JLabel label, final int cpt)
    {
        progressBar.setValue(cpt);
        String message = DETECTING;
        for (int i = 0; i < cpt; i++)
        {
            message += ".";
        }
        label.setText(message);
        _visible = !_visible;
        _indicationTextLabel.setText(_visible ? _userMessage : "");
    }

    @Override
    public void setChannelDetected(final ReceiverChannel currentDetectingChannel)
    {
        switch (currentDetectingChannel)
        {
            case THROTTLE:
                setSpecificChannelDetected(_throttleProgressBar, _throttleDetectionStateLabel);
                break;
            case ROLL:
                setSpecificChannelDetected(_rollProgressBar, _rollDetectionStateLabel);
                break;
            case PITCH:
                setSpecificChannelDetected(_pitchProgressBar, _pitchDetectionStateLabel);
                break;
            case YAW:
                setSpecificChannelDetected(_yawProgressBar, _yawDetectionStateLabel);
                break;
            case MODE:
                setSpecificChannelDetected(_modeProgressBar, _modeDetectionStateLabel);
                break;
            case AUX1:
                setSpecificChannelDetected(_aux1ProgressBar, _aux1DetectionStateLabel);
                break;
            case AUX2:
                setSpecificChannelDetected(_aux2ProgressBar, _aux2DetectionStateLabel);
                break;
            case AUX3:
                setSpecificChannelDetected(_aux3ProgressBar, _aux3DetectionStateLabel);
                break;
        }
    }

    @Override
    public void setFinishing()
    {
        _startButton.setEnabled(true);
        _startButton.setText(FINISH);
    }

    @Override
    public void resetWidgetInitialState()
    {
        _throttleProgressBar.setValue(0);
        _rollProgressBar.setValue(0);
        _pitchProgressBar.setValue(0);
        _yawProgressBar.setValue(0);
        _modeProgressBar.setValue(0);
        _aux1ProgressBar.setValue(0);
        _aux2ProgressBar.setValue(0);
        _aux3ProgressBar.setValue(0);

        _startButton.setText("Start");

        _throttleDetectionStateLabel.setText(UNDETECTED);
        _rollDetectionStateLabel.setText(UNDETECTED);
        _pitchDetectionStateLabel.setText(UNDETECTED);
        _yawDetectionStateLabel.setText(UNDETECTED);
        _modeDetectionStateLabel.setText(UNDETECTED);
        _aux1DetectionStateLabel.setText(UNDETECTED);
        _aux2DetectionStateLabel.setText(UNDETECTED);
        _aux3DetectionStateLabel.setText(UNDETECTED);
    }

    private void setSpecificChannelDetected(final JProgressBar progressBar, final JLabel label)
    {
        progressBar.setValue(5);
        label.setText(DETECTED);
    }
}
