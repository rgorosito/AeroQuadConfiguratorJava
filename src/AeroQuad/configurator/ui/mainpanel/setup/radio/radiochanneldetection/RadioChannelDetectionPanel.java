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
    private final JProgressBar _throttleProgressBar = new JProgressBar(0,3);
    private final JLabel _throttleDetectionStateLabel = new JLabel(UNDETECTED);
    private final JProgressBar _rollProgressBar = new JProgressBar(0,3);
    private final JLabel _rollDetectionStateLabel = new JLabel(UNDETECTED);
    private final JProgressBar _pitchProgressBar = new JProgressBar(0,3);
    private final JLabel _pitchDetectionStateLabel = new JLabel(UNDETECTED);
    private final JProgressBar _yawProgressBar = new JProgressBar(0,3);
    private final JLabel _yawDetectionStateLabel = new JLabel(UNDETECTED);
    private final JProgressBar _modeProgressBar = new JProgressBar(0,3);
    private final JLabel _modeDetectionStateLabel = new JLabel(UNDETECTED);


    final JPanel _aux1Panel = new JPanel(new GridLayout(1,3));
    private final JProgressBar _aux1ProgressBar = new JProgressBar(0,3);
    private final JLabel _aux1DetectionStateLabel = new JLabel(UNDETECTED);

    final JPanel _aux2Panel = new JPanel(new GridLayout(1,3));
    private final JProgressBar _aux2ProgressBar = new JProgressBar(0,3);
    private final JLabel _aux2DetectionStateLabel = new JLabel(UNDETECTED);

    final JPanel _aux3Panel = new JPanel(new GridLayout(1,3));
    private final JProgressBar _aux3ProgressBar = new JProgressBar(0,3);
    private final JLabel _aux3DetectionStateLabel = new JLabel(UNDETECTED);

    private final JLabel _indicationTextLabel = new JLabel();

    private final JButton _startButton = new JButton("Start");
    private final JButton _cancelButton = new JButton("Cancel");

    private final IRadioChannelDetectionController _controller;

    public RadioChannelDetectionPanel(final IRadioChannelDetectionController controller)
    {
        _controller = controller;

        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createLineBorder(Color.black));

        final JPanel channelDisplayPanelConntainer = new JPanel();
        channelDisplayPanelConntainer.setLayout(new BoxLayout(channelDisplayPanelConntainer, BoxLayout.Y_AXIS));


        final JPanel throttlePanel = new JPanel(new GridLayout(1,3));
        throttlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel throttleLabel = new JLabel("Throttle");
        throttleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        throttleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        throttleLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        throttlePanel.add(throttleLabel);
        throttlePanel.add(_throttleProgressBar);
        throttlePanel.add(_throttleDetectionStateLabel);
        _throttleDetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(throttlePanel);

        final JPanel rollPanel = new JPanel(new GridLayout(1,3));
        rollPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel rollLabel = new JLabel("Roll");
        rollLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rollLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        rollLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        rollPanel.add(rollLabel);
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
        yawPanel.add(_yawProgressBar);
        yawPanel.add(_yawDetectionStateLabel);
        _yawDetectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        channelDisplayPanelConntainer.add(yawPanel);

        final JPanel modePanel = new JPanel(new GridLayout(1,3));
        modePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel modeLabel = new JLabel("Mode");
        modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        modeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        modeLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        modePanel.add(modeLabel);
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
                _controller.startDetection();
            }
        });

        _startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.cancelDetection();
            }
        });
    }
}
