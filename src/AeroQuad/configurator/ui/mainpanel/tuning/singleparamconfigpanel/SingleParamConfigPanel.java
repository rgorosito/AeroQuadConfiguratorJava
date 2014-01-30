package AeroQuad.configurator.ui.mainpanel.tuning.singleparamconfigpanel;

import AeroQuad.configurator.ui.uiutils.IntegerFilterKeyAdapter;
import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class SingleParamConfigPanel extends JPanel
{
    private final JTextField _textField = new JTextField("0");

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
    }

    public void setText(final String stickScalling)
    {
        _textField.setText(stickScalling);
    }
}
