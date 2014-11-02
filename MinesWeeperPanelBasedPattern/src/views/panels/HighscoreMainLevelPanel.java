package views.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class HighscoreMainLevelPanel extends JPanel {
    private HighscoreLevelPanel highscoreLevelEasyPanel;
    private HighscoreLevelPanel highscoreLevelMediumPanel;
    private HighscoreLevelPanel highscoreLevelExpertPanel;
    private static final long serialVersionUID = 1L;

    private HighscoreMainLevelPanel()
    {
    	setLayout(new GridLayout(3,1));
    	highscoreLevelEasyPanel = HighscoreLevelPanel.createPanel("easy");
    	highscoreLevelMediumPanel = HighscoreLevelPanel.createPanel("medium");
    	highscoreLevelExpertPanel = HighscoreLevelPanel.createPanel("expert");
        add(highscoreLevelEasyPanel);
        add(highscoreLevelMediumPanel);
        add(highscoreLevelExpertPanel);
    }

	public HighscoreLevelPanel getHighscoreLevelEasyPanel() {
		return highscoreLevelEasyPanel;
	}

	public void setHighscoreLevelEasyPanel(
			HighscoreLevelPanel highscoreLevelEasyPanel) {
		this.highscoreLevelEasyPanel = highscoreLevelEasyPanel;
	}

	public HighscoreLevelPanel getHighscoreLevelMediumPanel() {
		return highscoreLevelMediumPanel;
	}

	public void setHighscoreLevelMediumPanel(
			HighscoreLevelPanel highscoreLevelMediumPanel) {
		this.highscoreLevelMediumPanel = highscoreLevelMediumPanel;
	}

	public HighscoreLevelPanel getHighscoreLevelExpertPanel() {
		return highscoreLevelExpertPanel;
	}

	public void setHighscoreLevelExpertPanel(
			HighscoreLevelPanel highscoreLevelExpertPanel) {
		this.highscoreLevelExpertPanel = highscoreLevelExpertPanel;
	}

	public static HighscoreMainLevelPanel createPanel() {
		return new HighscoreMainLevelPanel();
	}

}
