package AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel;

import AeroQuad.configurator.ui.uiutils.MotorSlider;
import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MotorDisplayPanel extends JPanel implements IMotorDisplayPanel
{
    private final IMotorDisplayController _controller;

    final JPanel _motor1Panel = new JPanel(new BorderLayout());
    private final JLabel _motor1Label = new JLabel("Motor 1");
    private final MotorSlider _motor1Slider = new MotorSlider();
    private final JLabel _motor1ValueLabel = new JLabel("1000");

    final JPanel _motor2Panel = new JPanel(new BorderLayout());
    private final JLabel _motor2Label = new JLabel("Motor 2");
    private final MotorSlider _motor2Slider = new MotorSlider();
    private final JLabel _motor2ValueLabel = new JLabel("1000");

    final JPanel _motor3Panel = new JPanel(new BorderLayout());
    private final JLabel _motor3Label = new JLabel("Motor 3");
    private final MotorSlider _motor3Slider = new MotorSlider();
    private final JLabel _motor3ValueLabel = new JLabel("1000");

    final JPanel _motor4Panel = new JPanel(new BorderLayout());
    private final JLabel _motor4Label = new JLabel("Motor 4");
    private final MotorSlider _motor4Slider = new MotorSlider();
    private final JLabel _motor4ValueLabel = new JLabel("1000");

    final JPanel _motor5Panel = new JPanel(new BorderLayout());
    private final JLabel _motor5Label = new JLabel("Motor 5");
    private final MotorSlider _motor5Slider = new MotorSlider();
    private final JLabel _motor5ValueLabel = new JLabel("1000");

    final JPanel _motor6Panel = new JPanel(new BorderLayout());
    private final JLabel _motor6Label = new JLabel("Motor 6");
    private final MotorSlider _motor6Slider = new MotorSlider();
    private final JLabel _motor6ValueLabel = new JLabel("1000");

    final JPanel _motor7Panel = new JPanel(new BorderLayout());
    private final JLabel _motor7Label = new JLabel("Motor 7");
    private final MotorSlider _motor7Slider = new MotorSlider();
    private final JLabel _motor7ValueLabel = new JLabel("1000");

    final JPanel _motor8Panel = new JPanel(new BorderLayout());
    private final JLabel _motor8Label = new JLabel("Motor 8");
    private final MotorSlider _motor8Slider = new MotorSlider();
    private final JLabel _motor8ValueLabel = new JLabel("1000");

    final List<IUserMotorValueChangedListenrer> _userChangeListenerList = new ArrayList<IUserMotorValueChangedListenrer>(1);
    private JPanel _motorsPanesContainer = new JPanel();

    public MotorDisplayPanel(final IMotorDisplayController controller)
    {
        _controller = controller;

        _controller.setPanel(this);

        init();
    }

    private void init()
    {
        setLayout(new BorderLayout());
        final JLabel motorsLabel = new JLabel("MOTORS");
        motorsLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        motorsLabel.setBorder(new LineBorder(Color.BLACK,1));
        motorsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(motorsLabel, BorderLayout.NORTH);

        _motorsPanesContainer.setLayout(new BoxLayout(_motorsPanesContainer, BoxLayout.X_AXIS));

        setPreferredSize(new Dimension(0, 250));

        _motor1Label.setHorizontalAlignment(SwingConstants.CENTER);
        _motor1Label.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor1Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _motor1Panel.add(_motor1Label, BorderLayout.NORTH);
        _motor1Panel.add(_motor1Slider, BorderLayout.CENTER);
        _motor1ValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motor1ValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor1Panel.add(_motor1ValueLabel, BorderLayout.SOUTH);
        _motorsPanesContainer.add(_motor1Panel);

        _motor2Label.setHorizontalAlignment(SwingConstants.CENTER);
        _motor2Label.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor2Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _motor2Panel.add(_motor2Label, BorderLayout.NORTH);
        _motor2Panel.add(_motor2Slider, BorderLayout.CENTER);
        _motor2ValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motor2ValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor2Panel.add(_motor2ValueLabel, BorderLayout.SOUTH);
        _motorsPanesContainer.add(_motor2Panel);

        _motor3Label.setHorizontalAlignment(SwingConstants.CENTER);
        _motor3Label.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor3Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _motor3Panel.add(_motor3Label, BorderLayout.NORTH);
        _motor3Panel.add(_motor3Slider, BorderLayout.CENTER);
        _motor3ValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motor3ValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor3Panel.add(_motor3ValueLabel, BorderLayout.SOUTH);
        _motorsPanesContainer.add(_motor3Panel);

        _motor4Label.setHorizontalAlignment(SwingConstants.CENTER);
        _motor4Label.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor4Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _motor4Panel.add(_motor4Label, BorderLayout.NORTH);
        _motor4Panel.add(_motor4Slider, BorderLayout.CENTER);
        _motor4ValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motor4ValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor4Panel.add(_motor4ValueLabel, BorderLayout.SOUTH);
        _motorsPanesContainer.add(_motor4Panel);

        _motor5Label.setHorizontalAlignment(SwingConstants.CENTER);
        _motor5Label.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor5Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _motor5Panel.add(_motor5Label, BorderLayout.NORTH);
        _motor5ValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motor5ValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor5Panel.add(_motor5ValueLabel, BorderLayout.SOUTH);
        _motor5Panel.add(_motor5Slider, BorderLayout.CENTER);

        _motor6Label.setHorizontalAlignment(SwingConstants.CENTER);
        _motor6Label.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor6Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _motor6Panel.add(_motor6Label, BorderLayout.NORTH);
        _motor6ValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motor6ValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor6Panel.add(_motor6ValueLabel, BorderLayout.SOUTH);
        _motor6Panel.add(_motor6Slider, BorderLayout.CENTER);

        _motor7Label.setHorizontalAlignment(SwingConstants.CENTER);
        _motor7Label.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor7Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _motor7Panel.add(_motor7Label, BorderLayout.NORTH);
        _motor7ValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motor7ValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor7Panel.add(_motor7ValueLabel, BorderLayout.SOUTH);
        _motor7Panel.add(_motor7Slider, BorderLayout.CENTER);

        _motor8Label.setHorizontalAlignment(SwingConstants.CENTER);
        _motor8Label.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor8Label.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        _motor8Panel.add(_motor8Label, BorderLayout.NORTH);
        _motor8ValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _motor8ValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        _motor8Panel.add(_motor8ValueLabel, BorderLayout.SOUTH);
        _motor8Panel.add(_motor8Slider, BorderLayout.CENTER);

        add(_motorsPanesContainer, BorderLayout.CENTER);

        setBorder(new LineBorder(Color.BLACK,3));

        connectListener();
    }

    private void connectListener()
    {
        _motor1Slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                notifyListeners(1, _motor1Slider.getValue());
            }
        });
        _motor2Slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                notifyListeners(2, _motor2Slider.getValue());
            }
        });
        _motor3Slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                notifyListeners(3, _motor3Slider.getValue());
            }
        });
        _motor4Slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                notifyListeners(4, _motor4Slider.getValue());
            }
        });
        _motor5Slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                notifyListeners(5, _motor5Slider.getValue());
            }
        });
        _motor6Slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                notifyListeners(6, _motor6Slider.getValue());
            }
        });
        _motor7Slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                notifyListeners(7, _motor7Slider.getValue());
            }
        });
        _motor8Slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                notifyListeners(8, _motor8Slider.getValue());
            }
        });
    }

    private void notifyListeners(final int motor, final int value)
    {
        for (IUserMotorValueChangedListenrer listener: _userChangeListenerList)
        {
            listener.motorValueChangedByUser(motor,value);
        }
    }

    @Override
    public void setNbMotor(final int nbMotor)
    {
        if (nbMotor > 4)
        {
            _motorsPanesContainer.add(_motor5Panel);
            _motorsPanesContainer.add(_motor6Panel);
        }
        if (nbMotor > 6)
        {
            _motorsPanesContainer.add(_motor7Panel);
            _motorsPanesContainer.add(_motor8Panel);
        }
    }

    @Override
    public void setEditable(final boolean editable)
    {
        _motor1Slider.setEnabled(editable);
        _motor2Slider.setEnabled(editable);
        _motor3Slider.setEnabled(editable);
        _motor4Slider.setEnabled(editable);
        _motor5Slider.setEnabled(editable);
        _motor6Slider.setEnabled(editable);
        _motor7Slider.setEnabled(editable);
        _motor8Slider.setEnabled(editable);
    }

    @Override
    public void setMaxMotorsValue(final int maxValue)
    {
        _motor1Slider.setMaximum(maxValue);
        _motor2Slider.setMaximum(maxValue);
        _motor3Slider.setMaximum(maxValue);
        _motor4Slider.setMaximum(maxValue);
        _motor5Slider.setMaximum(maxValue);
        _motor6Slider.setMaximum(maxValue);
        _motor7Slider.setMaximum(maxValue);
        _motor8Slider.setMaximum(maxValue);
    }

    @Override
    public void setMotor1CommandValue(final String value)
    {
        _motor1Slider.setValue(Integer.parseInt(value));
        _motor1ValueLabel.setText(value);
    }

    @Override
    public void setMotor2CommandValue(final String value)
    {
        _motor2Slider.setValue(Integer.parseInt(value));
        _motor2ValueLabel.setText(value);
    }

    @Override
    public void setMotor3CommandValue(final String value)
    {
        _motor3Slider.setValue(Integer.parseInt(value));
        _motor3ValueLabel.setText(value);
    }

    @Override
    public void setMotor4CommandValue(final String value)
    {
        _motor4Slider.setValue(Integer.parseInt(value));
        _motor4ValueLabel.setText(value);
    }

    @Override
    public void setMotor5CommandValue(final String value)
    {
        _motor5Slider.setValue(Integer.parseInt(value));
        _motor5ValueLabel.setText(value);
    }

    @Override
    public void setMotor6CommandValue(final String value)
    {
        _motor6Slider.setValue(Integer.parseInt(value));
        _motor6ValueLabel.setText(value);
    }

    @Override
    public void setMotor7CommandValue(final String value)
    {
        _motor7Slider.setValue(Integer.parseInt(value));
        _motor7ValueLabel.setText(value);
    }

    @Override
    public void setMotor8CommandValue(final String value)
    {
        _motor8Slider.setValue(Integer.parseInt(value));
        _motor8ValueLabel.setText(value);
    }


    public void addUserMotorValueChangedByUser(final IUserMotorValueChangedListenrer listener)
    {
        _userChangeListenerList.add(listener);
    }

    @Override
    public void setMotorValue(final int motor, final Integer value)
    {
        switch (motor)
        {
            case 1:
                _motor1Slider.setValue(value);
                break;
            case 2:
                _motor2Slider.setValue(value);
                break;
            case 3:
                _motor3Slider.setValue(value);
                break;
            case 4:
                _motor4Slider.setValue(value);
                break;
            case 5:
                _motor5Slider.setValue(value);
                break;
            case 6:
                _motor6Slider.setValue(value);
                break;
            case 7:
                _motor7Slider.setValue(value);
                break;
            case 8:
                _motor8Slider.setValue(value);
                break;
        }
    }

    @Override
    public void setDisconnected()
    {
        _motorsPanesContainer.remove(_motor5Panel);
        _motorsPanesContainer.remove(_motor6Panel);
        _motorsPanesContainer.remove(_motor7Panel);
        _motorsPanesContainer.remove(_motor8Panel);
    }
}
