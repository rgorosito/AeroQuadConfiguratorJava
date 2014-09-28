package AeroQuad.configurator.ui.splashpanel;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SplashPanel extends JPanel
{
    public SplashPanel()
    {
        setLayout(new BorderLayout());

        URL imgUrl = this.getClass().getClassLoader().getResource("ressources/AeroQuad_1024x500.png");
    	ImageIcon imgIcon = new ImageIcon(imgUrl);
    	JLabel jLabel = new JLabel(imgIcon);
    	add(jLabel, BorderLayout.CENTER);

    }
}
