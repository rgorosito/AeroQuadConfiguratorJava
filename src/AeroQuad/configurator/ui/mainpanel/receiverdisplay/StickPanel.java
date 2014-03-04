package AeroQuad.configurator.ui.mainpanel.receiverdisplay;

import AeroQuad.configurator.MathUtil;

import javax.swing.*;
import java.awt.*;

public class StickPanel extends JLabel
{
    private int _x;
    private int _y;
    private int _width;
    private int _height;

    public StickPanel()
    {
        setBorder(BorderFactory.createLineBorder(Color.black));

        setPreferredSize(new Dimension(225,225));
        setMinimumSize(new Dimension(225, 225));
    }

    void setX(final int x)
    {
        _x = x;
        repaint();
    }

    void setY(final int y)
    {
        _y = y;
        repaint();
    }

    @Override
    public void paint(final Graphics g)
    {
        super.paint(g);

        drawCircles(g);

        drawStickFeedback(g);
    }

    private void drawStickFeedback(final Graphics g)
    {
        final int minSize = Math.min(_width, _height);

        final int x = (int) MathUtil.map(_x, 1000, 2000, 0, minSize);
        final int y = (int) MathUtil.map(_y, 2000, 1000, 0, minSize);

        final int offsetX = _width - minSize;
        final int offsetY = _height - minSize;

        g.setColor(Color.blue);
        final int dotSize = (int) (minSize*0.1);
        g.fillOval(x-(dotSize/2)+offsetX,y-(dotSize/2)+(offsetY/2),dotSize,dotSize);
    }


    private void drawCircles(final Graphics g)
    {
        final Graphics2D g2d = (Graphics2D)g;


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);


        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.black);

        final int minSize = Math.min(_width, _height);
        if (_width < _height)
        {
            final int offset =  _height - _width;
            int seccondCircleOffset = 5;
            g2d.drawOval(0+(seccondCircleOffset/2),offset-(offset/2)+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
            seccondCircleOffset = 18;
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(0+(seccondCircleOffset/2),offset-(offset/2)+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
        }
        else
        {
            final int offset = _width - _height;
            int seccondCircleOffset = 5;
            g2d.drawOval(offset-(offset/2)+(seccondCircleOffset/2),0+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
            seccondCircleOffset = 18;
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(offset-(offset/2)+(seccondCircleOffset/2),0+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
        }
    }

    @Override
    public void setBounds(final int x, final int y, final int width, final int height)
    {
        _width = width;
        _height = height;
        super.setBounds(x, y, width, height);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
