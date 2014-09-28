package AeroQuad.configurator.ui.mainpanel.receiverdisplay;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ReceiverDisplayPanel extends JPanel implements IReceiverDisplayPanel
{
    private final StickPanel _throttleYawPanel = new StickPanel();
    private final StickPanel _rollPitchPanel = new StickPanel();

    final JPanel _middlePanel = new JPanel();

    private final JLabel _modeLabel = new JLabel("Mode");
    private final JSlider _modeSlider = new JSlider();
    private final JLabel _aux1Label = new JLabel("AUX 1");
    private final JSlider _aux1Slider = new JSlider();
    private final JLabel _aux2Label = new JLabel("AUX 2");
    private final JSlider _aux2Slider = new JSlider();
    private final JLabel _aux3Label = new JLabel("AUX 3");
    private final JSlider _aux3Slider = new JSlider();

    final JPanel _modePanel = new JPanel();
    final JPanel _aux1Panel = new JPanel();
    final JPanel _aux2Panel = new JPanel();
    final JPanel _aux3Panel = new JPanel();

    final JLabel _northSpacerLabel = new JLabel();
    final JLabel _southSpacerLabel = new JLabel();


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

        _middlePanel.setLayout(new BoxLayout(_middlePanel, BoxLayout.Y_AXIS));
        _modeSlider.setMinimum(1000);
        _modeSlider.setMaximum(2000);
        _modeSlider.setOrientation(JSlider.HORIZONTAL);
        _modeSlider.setMajorTickSpacing(200);
        _modeSlider.setMinorTickSpacing(50);
        //_modeSlider.setPaintTicks(true);
        _modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _modePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        _modePanel.setLayout(new BoxLayout(_modePanel, BoxLayout.PAGE_AXIS));
        _modePanel.add(_modeLabel);
        _modePanel.add(_modeSlider);
        _middlePanel.add(_modePanel);

        _aux1Slider.setMinimum(1000);
        _aux1Slider.setMaximum(2000);
        _aux1Slider.setOrientation(JSlider.HORIZONTAL);
        _aux1Slider.setMajorTickSpacing(200);
        _aux1Slider.setMinorTickSpacing(50);
        //_aux1Slider.setPaintTicks(true);
        _aux1Label.setHorizontalAlignment(SwingConstants.CENTER);
        _aux1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        _aux1Panel.setLayout(new BoxLayout(_aux1Panel, BoxLayout.PAGE_AXIS));
        _aux1Panel.add(_aux1Label);
        _aux1Panel.add(_aux1Slider);
        _middlePanel.add(_aux1Panel);

        _aux2Slider.setMinimum(1000);
        _aux2Slider.setMaximum(2000);
        _aux2Label.setHorizontalAlignment(SwingConstants.CENTER);
        _aux2Slider.setOrientation(JSlider.HORIZONTAL);
        _aux2Slider.setMajorTickSpacing(200);
        _aux2Slider.setMinorTickSpacing(50);
        //_aux2Slider.setPaintTicks(true);
        _aux2Panel.setLayout(new BoxLayout(_aux2Panel, BoxLayout.PAGE_AXIS));
        _aux2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        _aux2Panel.add(_aux2Label);
        _aux2Panel.add(_aux2Slider);
        _middlePanel.add(_aux2Panel);

        _aux3Slider.setMinimum(1000);
        _aux3Slider.setMaximum(2000);
        _aux3Label.setHorizontalAlignment(SwingConstants.CENTER);
        _aux3Slider.setOrientation(JSlider.HORIZONTAL);
        _aux3Slider.setMajorTickSpacing(200);
        _aux3Slider.setMinorTickSpacing(50);
        //_aux3Slider.setPaintTicks(true);
        _aux3Panel.setLayout(new BoxLayout(_aux3Panel, BoxLayout.PAGE_AXIS));
        _aux3Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        _aux3Panel.add(_aux3Label);
        _aux3Panel.add(_aux3Slider);
        _middlePanel.add(_aux3Panel);

        add(_northSpacerLabel, BorderLayout.NORTH);
        add(_middlePanel,BorderLayout.CENTER);
        add(_southSpacerLabel, BorderLayout.SOUTH);

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
    public void setDisconnected()
    {
        _aux1Panel.setVisible(false);
        _aux2Panel.setVisible(false);
        _aux3Panel.setVisible(false);
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
    public void setBounds(final int x, final int y, final int width, final int height)
    {
        final int componentWidth = width/3;
        if (height > componentWidth)
        {
            int offset = (height - componentWidth) / 2;
            _northSpacerLabel.setPreferredSize(new Dimension(0,offset));
            _southSpacerLabel.setPreferredSize(new Dimension(0,offset));
        }
        else
        {
            _northSpacerLabel.setPreferredSize(new Dimension(0,0));
            _southSpacerLabel.setPreferredSize(new Dimension(0,0));
        }

        _throttleYawPanel.setParentSize(width,height);
        _rollPitchPanel.setParentSize(width,height);
        super.setBounds(x, y, width, height);
    }
}
