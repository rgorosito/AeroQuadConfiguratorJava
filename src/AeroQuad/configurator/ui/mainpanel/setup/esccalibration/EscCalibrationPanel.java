package AeroQuad.configurator.ui.mainpanel.setup.esccalibration;

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

public class EscCalibrationPanel extends JPanel implements IEscCalibrationPanel
{
    private final IEscCalibrationPanelController _controller;
    private JButton _sendHighCommandButton;
    private JButton sendLowCommandButton;

    private final float FONT_SIZE = 30;

    public EscCalibrationPanel(final IEscCalibrationPanelController controller)
    {
        _controller = controller;

        init();

        _sendHighCommandButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.setHighThrottleCommand();
            }
        });

        sendLowCommandButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                _controller.setLowThrottleCommand();
            }
        });
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
        final JLabel topHeaderLabel = new JLabel("ESC CALIBRATION");
        topHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(topHeaderLabel, BorderLayout.NORTH);

        final JLabel warningLabel = new JLabel("PLEASE, REMOVE PROPELLER, THIS CAN MAKE MOTOR SPIN FULL THROTTLE");
        warningLabel.setFont(warningLabel.getFont().deriveFont(25F));
        warningLabel.setForeground(Color.red);
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(warningLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        final JLabel bottomLabel = new JLabel("To clibrate esc, follow those 4 step really carefully");
        bottomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(bottomLabel,BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
    }

    private void initCenterPanel()
    {
        final JPanel centerPanel = new JPanel(new GridLayout(4,1));

        final JPanel line1Panel = new JPanel(new BorderLayout());
        line1Panel.setBorder(new LineBorder(Color.black,3));
        final JLabel label1 = new JLabel(" 1. ");
        label1.setBorder(new LineBorder(Color.black,1));
        label1.setFont(label1.getFont().deriveFont(FONT_SIZE));
        line1Panel.add(label1,BorderLayout.WEST);
        final JLabel line1Text = new JLabel("<HTML><CENTER>Make sure ESC have no power in it.<br>Disconnect the battery.</CENTER></HTML>");
        line1Text.setHorizontalAlignment(SwingConstants.CENTER);
        line1Text.setFont(line1Text.getFont().deriveFont(FONT_SIZE));
        line1Panel.add(line1Text,BorderLayout.CENTER);
        centerPanel.add(line1Panel);

        final JPanel line2Panel = new JPanel(new BorderLayout());
        line2Panel.setBorder(new LineBorder(Color.black,3));
        final JLabel label2 = new JLabel(" 2. ");
        label2.setFont(label2.getFont().deriveFont(FONT_SIZE));
        line2Panel.add(label2, BorderLayout.WEST);
        _sendHighCommandButton = new JButton("<HTML><CENTER>Press here, this will send a max throttle to the ESC.</CENTER></HTML>");
        _sendHighCommandButton.setFont(_sendHighCommandButton.getFont().deriveFont(FONT_SIZE));
        line2Panel.add(_sendHighCommandButton, BorderLayout.CENTER);
        centerPanel.add(line2Panel);

        final JPanel line3Panel = new JPanel(new BorderLayout());
        line3Panel.setBorder(new LineBorder(Color.black,3));
        final JLabel label3 = new JLabel(" 3. ");
        label3.setFont(label3.getFont().deriveFont(FONT_SIZE));
        label3.setBorder(new LineBorder(Color.black,1));
        line3Panel.add(label3, BorderLayout.WEST);
        final JLabel line3Text = new JLabel("<HTML><CENTER>Connect the battery.<br>Motor should not spin and ESC should play the enter calibration tone.</CENTER></HTML>");
        line3Text.setFont(line3Text.getFont().deriveFont(FONT_SIZE));
        line3Text.setHorizontalAlignment(SwingConstants.CENTER);
        line3Panel.add(line3Text, BorderLayout.CENTER);
        centerPanel.add(line3Panel);

        final JPanel line4Panel = new JPanel(new BorderLayout());
        line4Panel.setBorder(new LineBorder(Color.black,3));
        final JLabel label4 = new JLabel(" 4. ");
        label4.setFont(label4.getFont().deriveFont(FONT_SIZE));
        line4Panel.add(label4, BorderLayout.WEST);
        sendLowCommandButton = new JButton("<HTML><CENTER>Press here, this will send a low throttle to the ESC.<br>ESC should play the end calibration tone.</CENTER></HTML>");
        sendLowCommandButton.setFont(sendLowCommandButton.getFont().deriveFont(FONT_SIZE));
        line4Panel.add(sendLowCommandButton, BorderLayout.CENTER);
        centerPanel.add(line4Panel);

        add(centerPanel, BorderLayout.CENTER);
    }
}
