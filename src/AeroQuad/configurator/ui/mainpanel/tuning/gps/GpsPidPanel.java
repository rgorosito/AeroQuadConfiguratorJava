package AeroQuad.configurator.ui.mainpanel.tuning.gps;

import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class GpsPidPanel extends JPanel implements IGpsPidPanel
{
    private final IGpsPidPanelController _controller;

    public GpsPidPanel(final IGpsPidPanelController gpsPidPanelController)
    {
        _controller = gpsPidPanelController;
        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());
        final JLabel headerLabel = new JLabel("GPS");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_PREFERED_HEIGHT));
        add(headerLabel);
    }

}
