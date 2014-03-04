package AeroQuad.configurator.ui.mainpanel.receiverdisplay;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class ReceiverDisplayPanel extends JPanel implements IReceiverDisplayPanel
{
    private final StickPanel _throttleYawPanel = new StickPanel();
    private final StickPanel _rollPitchPanel = new StickPanel();

    final GridLayout _gridLayout = new GridLayout(2,1);
    final JPanel _middlePanel = new JPanel(_gridLayout);

    private final JLabel _modeLabel = new JLabel("Mode");
    private final JSlider _modeSlider = new JSlider();
    private final JLabel _aux1Label = new JLabel("AUX 1");
    private final JSlider _aux1Slider = new JSlider();
    private final JLabel _aux2Label = new JLabel("AUX 2");
    private final JSlider _aux2Slider = new JSlider();
    private final JLabel _aux3Label = new JLabel("AUX 3");
    private final JSlider _aux3Slider = new JSlider();


    public ReceiverDisplayPanel(final IReceiverDisplayPanelController controller)
    {
        controller.setPanel(this);

        init();
    }

    private void init()
    {
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(0,250));

        add(_throttleYawPanel, BorderLayout.WEST);
        add(_rollPitchPanel,BorderLayout.EAST);


        _modeSlider.setPaintLabels(true);
        _modeSlider.setMinimum(1000);
        _modeSlider.setMaximum(2000);
        _modeSlider.setOrientation(JSlider.HORIZONTAL);
        _modeSlider.setMajorTickSpacing(200);
        _modeSlider.setMinorTickSpacing(50);
        _modeSlider.setPaintTicks(true);
        _modeSlider.setPaintLabels(true);
        _modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        final JPanel modePanel = new JPanel(new GridLayout(2,1));
        modePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        modePanel.add(_modeLabel);
        modePanel.add(_modeSlider);
        _middlePanel.add(modePanel);

        _aux1Slider.setMinimum(1000);
        _aux1Slider.setMaximum(2000);
        _aux1Slider.setOrientation(JSlider.HORIZONTAL);
        _aux1Slider.setMajorTickSpacing(200);
        _aux1Slider.setMinorTickSpacing(50);
        _aux1Slider.setPaintTicks(true);
        _aux1Slider.setPaintLabels(true);

        _aux1Label.setHorizontalAlignment(SwingConstants.CENTER);
        final JPanel aux1Panel = new JPanel(new GridLayout(2,1));
        aux1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        aux1Panel.add(_aux1Label);
        aux1Panel.add(_aux1Slider);
        _middlePanel.add(aux1Panel);

        _aux2Slider.setMinimum(1000);
        _aux2Slider.setMaximum(2000);
        _aux2Label.setHorizontalAlignment(SwingConstants.CENTER);
        _aux2Slider.setOrientation(JSlider.HORIZONTAL);
        _aux2Slider.setMajorTickSpacing(200);
        _aux2Slider.setMinorTickSpacing(50);
        _aux2Slider.setPaintTicks(true);
        _aux2Slider.setPaintLabels(true);

        _aux3Slider.setMinimum(1000);
        _aux3Slider.setMaximum(2000);
        _aux3Label.setHorizontalAlignment(SwingConstants.CENTER);
        _aux3Slider.setOrientation(JSlider.HORIZONTAL);
        _aux3Slider.setMajorTickSpacing(200);
        _aux3Slider.setMinorTickSpacing(50);
        _aux3Slider.setPaintTicks(true);
        _aux3Slider.setPaintLabels(true);

        add(_middlePanel,BorderLayout.CENTER);

        setBorder(new LineBorder(Color.BLACK,3));
    }

    @Override
    public void setRollValue(final String value)
    {
        _rollPitchPanel.setX(Integer.parseInt(value));
    }

    @Override
    public void setPitchValue(final String value)
    {
        _rollPitchPanel.setY(Integer.parseInt(value));
    }

    @Override
    public void setYawValue(final String value)
    {
        _throttleYawPanel.setX(Integer.parseInt(value));
    }

    @Override
    public void setThrottleValue(final String value)
    {
        _throttleYawPanel.setY(Integer.parseInt(value));
    }

    @Override
    public void setModeValue(final String value)
    {
        _modeSlider.setValue(Integer.parseInt(value));
    }

    @Override
    public void setAux1Value(final String value)
    {
        _aux1Slider.setValue(Integer.parseInt(value));
    }

    @Override
    public void setAux2Value(final String value)
    {
        _aux2Slider.setValue(Integer.parseInt(value));
    }

    @Override
    public void setAux3Value(final String value)
    {
        _aux3Slider.setValue(Integer.parseInt(value));
    }

    @Override
    public void setNbChannel(final int nbChannel)
    {
        if (nbChannel >= 8)
        {
            _gridLayout.setRows(4);
            final JPanel aux2Panel = new JPanel(new GridLayout(2,1));
            aux2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
            aux2Panel.add(_aux2Label);
            aux2Panel.add(_aux2Slider);
            _middlePanel.add(aux2Panel);

            final JPanel aux3Panel = new JPanel(new GridLayout(2,1));
            aux3Panel.setBorder(BorderFactory.createLineBorder(Color.black));
            aux3Panel.add(_aux3Label);
            aux3Panel.add(_aux3Slider);
            _middlePanel.add(aux3Panel);

        }
    }
}
