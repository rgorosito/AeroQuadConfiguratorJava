package AeroQuad.configurator.ui.mainpanel.tuning.syncedstate;

import AeroQuad.configurator.ui.uiutils.UiUtils;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class SyncedStatePanel extends JPanel implements ISyncedStatePanel
{
    private final JLabel _syncStateLabel = new JLabel();
    private boolean synced;

    public SyncedStatePanel()
    {
        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BorderLayout());

        final JLabel headerLabel = new JLabel("<HTML><CENTER>Sync<BR>State</CENTER></HTML>");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setPreferredSize(new Dimension(0, UiUtils.HEATHER_SMALL_PREFERED_HEIGHT * 2));
        headerLabel.setBorder(new LineBorder(Color.black, 1));
        add(headerLabel, BorderLayout.NORTH);

        _syncStateLabel.setOpaque(true);
        add(_syncStateLabel, BorderLayout.CENTER);
        setSynced(false);
    }

    public void setSynced(final boolean synced)
    {
        _syncStateLabel.setBackground(Color.yellow);
        if (synced)
        {
            _syncStateLabel.setBackground(Color.GREEN);
        }
    }
}
