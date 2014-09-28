package AeroQuad.configurator.ui.mainpanel.tuning.singleparamconfigpanel;

import AeroQuad.configurator.ui.uiutils.IntegerFilterKeyAdapter;
import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SingleParamConfigPanel extends JPanel
{
    private final JTextField _textField = new JTextField("0");

    private final List<ActionListener> _actionListenerList = new ArrayList<ActionListener>(1);

    public SingleParamConfigPanel(final String header)
    {
        initPanel(header);
    }

    private void initPanel(final String header)
    {
        setLayout(new BorderLayout());

        final JLabel headerLabel = new JLabel(header);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_SMALL_PREFERED_HEIGHT*2));
        headerLabel.setBorder(new LineBorder(Color.black, 1));
        add(headerLabel, BorderLayout.NORTH);

        _textField.setHorizontalAlignment(SwingConstants.CENTER);
        _textField.addKeyListener(new IntegerFilterKeyAdapter());
        add(_textField, BorderLayout.CENTER);

        _textField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(final KeyEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for (final ActionListener actionListener : _actionListenerList)
                        {
                            actionListener.actionPerformed(null);
                        }
                    }
                });
            }
        });
    }

    public void setText(final String stickScalling)
    {
        _textField.setText(stickScalling);
    }

    public void addActionListener(final ActionListener actionListener)
    {
        _actionListenerList.add(actionListener);
    }

    public String getText()
    {
        return _textField.getText();
    }
}
