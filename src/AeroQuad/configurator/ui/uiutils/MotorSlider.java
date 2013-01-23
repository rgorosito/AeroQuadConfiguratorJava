package AeroQuad.configurator.ui.uiutils;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import java.awt.Color;

public class MotorSlider extends JSlider
{
    public MotorSlider()
    {
        super(JSlider.VERTICAL,1000,2000,1000);

        setMajorTickSpacing(200);
        setMinorTickSpacing(50);
        setPaintTicks(true);
        setPaintLabels(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public void setValue(final int n)
    {
        super.setValue(n);    //To change body of overridden methods use File | Settings | File Templates.
        udateColorFeedback();
    }

    public void udateColorFeedback()
    {
        final int value = getValue();
        Color color = Color.LIGHT_GRAY;
        if (value >= 1050 && value <= 1600)
        {
            color = Color.green;
        }
        else if (value > 1600 && value <= 1800)
        {
            color = Color.yellow;
        }
        else if (value > 1800)
        {
            color = Color.red;
        }
        setBackground(color);
    }
}
