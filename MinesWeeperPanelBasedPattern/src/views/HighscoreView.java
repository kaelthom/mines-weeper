// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreView.java

package views;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import languages.LanguageFactory;
import views.panels.HighscoreMainPanel;

public class HighscoreView extends JPanel
{
    private static JFrame frame;

    public HighscoreView()
    {
    	setLayout(new GridLayout(1,1));
    	int currentLevel = DeminorView.getLevel();
        add(HighscoreMainPanel.createPanel(currentLevel));
    }

    private static void createAndShowGUI(JPanel panel)
    {
        frame = new JFrame(HIGHSCORES_TITLE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void launchFrame(final JPanel panel)
    {
        SwingUtilities.invokeLater(
        	new Runnable() {

        		public void run()
        		{
        			UIManager.put("swing.boldMetal", Boolean.FALSE);
        			HighscoreView.createAndShowGUI(panel);
        		}

        	});
    }

    private static final String HIGHSCORES_TITLE = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("highscores.title");
    private static final long serialVersionUID = 1L;

	public static JFrame getFrame() {
		return frame;
	}

}
