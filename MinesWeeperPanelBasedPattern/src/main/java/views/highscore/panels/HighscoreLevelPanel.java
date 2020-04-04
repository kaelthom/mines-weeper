package views.highscore.panels;

import javax.swing.*;
import java.util.ResourceBundle;

class HighscoreLevelPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton levelButton;

    private HighscoreLevelPanel(String level) {
        setLevelButton(new JButton(ResourceBundle.getBundle("labels").getString("options.level." + level)));
        add(levelButton);
    }

    static HighscoreLevelPanel createPanel(String level) {
        return new HighscoreLevelPanel(level);
    }

    JButton getLevelButton() {
        return levelButton;
    }

    private void setLevelButton(JButton levelButton) {
        this.levelButton = levelButton;
    }

}
