package AeroQuad.configurator.ui.mainpanel.tuning.pidpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PidPanel extends JPanel implements IPidPanel
{
    private final GridLayout _gridLayout = new GridLayout(1, 3);
    private final JPanel _centerPanel = new JPanel(_gridLayout);

    private final JPanel _pPanel = new JPanel(new BorderLayout());
    private final JPanel _iPanel = new JPanel(new BorderLayout());
    private final JPanel _dPanel = new JPanel(new BorderLayout());

    private final JTextField _pTextField = new JTextField("0");
    private final JTextField _iTextField = new JTextField("0");
    private final JTextField _dTextField = new JTextField("0");


    public PidPanel(final String header)
    {
        initPanel(header);
    }

    private void initPanel(final String header)
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,1));

        //setPreferredSize(new Dimension(100,100));

        final JLabel headerLabel = new JLabel(header);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(new LineBorder(Color.black, 1));
        add(headerLabel, BorderLayout.NORTH);

        final JLabel pLabel = new JLabel("P");
        pLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pLabel.setPreferredSize(new Dimension(0, 25));
        pLabel.setBorder(new LineBorder(Color.black, 1));
        _pPanel.add(pLabel, BorderLayout.NORTH);
        _pPanel.add(_pTextField, BorderLayout.CENTER);
        _pTextField.setHorizontalAlignment(SwingConstants.CENTER);
        _pTextField.addKeyListener(new IntegerKeyAdapter());
        _pTextField.setPreferredSize(new Dimension(50,0));
        _centerPanel.add(_pPanel);

        final JLabel iLabel = new JLabel("I");
        iLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iLabel.setPreferredSize(new Dimension(0, 25));
        iLabel.setBorder(new LineBorder(Color.black,1));
        _iPanel.add(iLabel, BorderLayout.NORTH);
        _iPanel.add(_iTextField, BorderLayout.CENTER);
        _iTextField.setHorizontalAlignment(SwingConstants.CENTER);
        _iTextField.addKeyListener(new IntegerKeyAdapter());
        _iTextField.setPreferredSize(new Dimension(50,0));
        _centerPanel.add(_iPanel);

        final JLabel dLabel = new JLabel("D");
        dLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dLabel.setPreferredSize(new Dimension(0, 25));
        dLabel.setBorder(new LineBorder(Color.black,1));
        _dPanel.add(dLabel, BorderLayout.NORTH);
        _dPanel.add(_dTextField, BorderLayout.CENTER);
        _dTextField.setHorizontalAlignment(SwingConstants.CENTER);
        _dTextField.addKeyListener(new IntegerKeyAdapter());
        _dTextField.setPreferredSize(new Dimension(50,0));
        _centerPanel.add(_dPanel);

        add(_centerPanel, BorderLayout.CENTER);
    }

    private class IntegerKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
            char c = e.getKeyChar();
            if (!((c >= '0') && (c <= '9') ||
                  (c == KeyEvent.VK_BACK_SPACE) ||
                  (c == KeyEvent.VK_DELETE))) {
                getToolkit().beep();
                e.consume();
            }
        }
    }
}
