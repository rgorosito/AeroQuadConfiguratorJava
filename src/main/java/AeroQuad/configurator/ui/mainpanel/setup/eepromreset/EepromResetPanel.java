package AeroQuad.configurator.ui.mainpanel.setup.eepromreset;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class EepromResetPanel extends JPanel implements IEepromResetPanel
{

    private final float FONT_SIZE = 30;
    private final IEepromResetController _controller;

    private JButton _resetEepromButton;

    public EepromResetPanel(final IEepromResetController controller)
    {
        _controller = controller;
        _controller.setPanel(this);

        init();
    }

    private void init()
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,3));

        initTopPanel();

        initCenterPanel();
    }

    private void initTopPanel()
    {
        final JPanel topPanel = new JPanel(new BorderLayout());
        final JLabel topHeaderLabel = new JLabel("EEPROM RESET");
        topHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(topHeaderLabel, BorderLayout.NORTH);

        final JLabel warningLabel = new JLabel("PLEASE, NOTE THAT ALL VALUE WILL BE RESET TO DEFAUTLT");
        warningLabel.setFont(warningLabel.getFont().deriveFont(25F));
        warningLabel.setForeground(Color.red);
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(warningLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        final JLabel bottomLabel = new JLabel("<html><center>EVEN IF SOME SOME DEFAULT VALUES MAY WORK<br> WE STRONGLY ADVISE TO REDO ALL CALIBRATION PROCESS</html>");
        bottomLabel.setFont(warningLabel.getFont().deriveFont(25F));
        bottomLabel.setForeground(Color.yellow);
        bottomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(bottomLabel,BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
    }

    private void initCenterPanel()
    {
        final JPanel centerPanel = new JPanel(new GridLayout(3,1));

        final JPanel line1Panel = new JPanel(new BorderLayout());
        line1Panel.setBorder(new LineBorder(Color.black, 3));
        final JLabel line1Text = new JLabel();
        line1Text.setHorizontalAlignment(SwingConstants.CENTER);
        line1Text.setFont(line1Text.getFont().deriveFont(FONT_SIZE));
        line1Panel.add(line1Text,BorderLayout.CENTER);
        centerPanel.add(line1Panel);

        final JPanel line2Panel = new JPanel(new BorderLayout());
        line2Panel.setBorder(new LineBorder(Color.black, 3));
        _resetEepromButton = new JButton("<HTML><CENTER>PRESS HERE TO RESET EEPROM</CENTER></HTML>");
        _resetEepromButton.setFont(_resetEepromButton.getFont().deriveFont(FONT_SIZE));
        line2Panel.add(_resetEepromButton, BorderLayout.CENTER);
        centerPanel.add(line2Panel);

        final JPanel line3Panel = new JPanel(new BorderLayout());
        line3Panel.setBorder(new LineBorder(Color.black,3));
        final JLabel line3Text = new JLabel();
        line3Text.setFont(line3Text.getFont().deriveFont(FONT_SIZE));
        line3Text.setHorizontalAlignment(SwingConstants.CENTER);
        line3Panel.add(line3Text, BorderLayout.CENTER);
        centerPanel.add(line3Panel);

        add(centerPanel, BorderLayout.CENTER);

        _resetEepromButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.resetEepromButtonPressed();
            }
        });
    }

}
