package AeroQuad.configurator.ui.uiutils;

import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntegerFilterKeyAdapter extends KeyAdapter
{
    @Override
    public void keyTyped(KeyEvent e)
    {
        final char c = e.getKeyChar();
        if (!((c >= '0') && (c <= '9') ||  (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            new JLabel().getToolkit().beep();
            e.consume();
        }
    }
}