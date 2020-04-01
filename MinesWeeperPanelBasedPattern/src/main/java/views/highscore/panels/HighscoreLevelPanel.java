package views.highscore.panels;

import javax.swing.*;
import java.util.ResourceBundle;

public class HighscoreLevelPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton levelButton;

    private HighscoreLevelPanel(String level) {
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
