package AeroQuad.configurator.ui.mainpanel.tuning.pidpanel;

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
import java.awt.GridLayout;

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
    private boolean _isIVisible = true;
    private boolean _isDVisible = true;
    private String header;
    private JLabel _headerLabel;


    public PidPanel(final String header)
    {
        initPanel(header);
    }

    private void initPanel(final String header)
    {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black,1));

        //setPreferredSize(new Dimension(100,100));

        _headerLabel = new JLabel(header);
        _headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _headerLabel.setBorder(new LineBorder(Color.black, 1));
        add(_headerLabel, BorderLayout.NORTH);

        final JLabel pLabel = new JLabel("P");
        pLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_SMALL_PREFERED_HEIGHT));
        pLabel.setBorder(new LineBorder(Color.black, 1));
        _pPanel.add(pLabel, BorderLayout.NORTH);
        _pTextField.setHorizontalAlignment(SwingConstants.CENTER);
        _pTextField.addKeyListener(new IntegerFilterKeyAdapter());
        _pTextField.setPreferredSize(new Dimension(50,0));
        _pPanel.add(_pTextField, BorderLayout.CENTER);
        _centerPanel.add(_pPanel);

        final JLabel iLabel = new JLabel("I");
        iLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_SMALL_PREFERED_HEIGHT));
        iLabel.setBorder(new LineBorder(Color.black,1));
        _iPanel.add(iLabel, BorderLayout.NORTH);
        _iTextField.setHorizontalAlignment(SwingConstants.CENTER);
        _iTextField.addKeyListener(new IntegerFilterKeyAdapter());
        _iTextField.setPreferredSize(new Dimension(50,0));
        _iPanel.add(_iTextField, BorderLayout.CENTER);
        _centerPanel.add(_iPanel);

        final JLabel dLabel = new JLabel("D");
        dLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_SMALL_PREFERED_HEIGHT));
        dLabel.setBorder(new LineBorder(Color.black,1));
        _dPanel.add(dLabel, BorderLayout.NORTH);
        _dTextField.setHorizontalAlignment(SwingConstants.CENTER);
        _dTextField.addKeyListener(new IntegerFilterKeyAdapter());
        _dTextField.setPreferredSize(new Dimension(50,0));
        _dPanel.add(_dTextField, BorderLayout.CENTER);
        _centerPanel.add(_dPanel);

        add(_centerPanel, BorderLayout.CENTER);
    }

    public void setIVisible(final boolean visible)
    {
        _isIVisible = visible;
        updateCenterPanelFromVisibilityProperty();
    }

    public void setDVisible(final boolean visible)
    {
        _isDVisible = visible;
        updateCenterPanelFromVisibilityProperty();
    }

    private void updateCenterPanelFromVisibilityProperty()
    {
        _centerPanel.removeAll();
        _centerPanel.add(_pPanel);
        if (_isIVisible)
        {
            _centerPanel.add(_iPanel);
        }
        if (_isDVisible)
        {
            _centerPanel.add(_dPanel);
        }
    }

    public void setHeader(final String header)
    {
        _headerLabel.setText(header);
    }
}
