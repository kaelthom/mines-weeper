package views.panels;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HighscoreLevelPanel extends JPanel {

    private JButton levelButton;
    private static final long serialVersionUID = 1L;

    private HighscoreLevelPanel(String level)
    {
        levelButton = new JButton(ResourceBundle.getBundle("labels").getString("options.level." + level));        
        add(levelButton);
    }

	public static HighscoreLevelPanel createPanel(String level) {
		return new HighscoreLevelPanel(level);
	}

    public JButton getLevelButton() {
		return levelButton;
	}

	public void setLevelButton(JButton levelButton) {
		this.levelButton = levelButton;
	}

}
