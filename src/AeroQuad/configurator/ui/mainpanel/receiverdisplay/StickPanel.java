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

        setPreferredSize(new Dimension(175,175));
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
        final int dotSize = (int) (minSize*0.12);

        if (_width < _height)
        {
            g.fillOval(x-(dotSize/2)+offsetX,y-(dotSize/2)+(offsetY/2),dotSize,dotSize);
        }
        else
        {
            g.fillOval(x-(dotSize/2)+offsetX/2,y-(dotSize/2)+(offsetY),dotSize,dotSize);
        }
    }


    private void drawCircles(final Graphics g)
    {
        final Graphics2D g2d = (Graphics2D)g;


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(5));
        final int minSize = Math.min(_width, _height);
        if (_width < _height)
        {
            final int offset =  _height - _width;
            int seccondCircleOffset = 5;
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0+(seccondCircleOffset/2),offset-(offset/2)+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
            g2d.setColor(Color.black);
            g2d.drawRect(0+(seccondCircleOffset/2),offset-(offset/2)+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
            seccondCircleOffset = 18;
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(0+(seccondCircleOffset/2),offset-(offset/2)+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
        }
        else
        {
            final int offset = _width - _height;
            int seccondCircleOffset = 5;
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(offset-(offset/2)+(seccondCircleOffset/2),0+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
            g2d.setColor(Color.black);
            g2d.drawRect(offset-(offset/2)+(seccondCircleOffset/2),0+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
            seccondCircleOffset = 18;
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(offset-(offset/2)+(seccondCircleOffset/2),0+(seccondCircleOffset/2),minSize-seccondCircleOffset,minSize-seccondCircleOffset);
        }
    }

    public void setParentSize(final int width, final int height)
    {
        final int maxSize = Math.max(width,height);
        _width = (maxSize/3);
        _height = _width-7;
        setPreferredSize(new Dimension(maxSize/3,maxSize/3));
    }
}
