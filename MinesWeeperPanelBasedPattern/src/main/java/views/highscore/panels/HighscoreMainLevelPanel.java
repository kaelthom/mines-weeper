package views.highscore.panels;

import javax.swing.*;
import java.awt.*;

class HighscoreMainLevelPanel extends JPanel {
    static final String EASY_LEVEL = "easy";
    static final String MEDIUM_LEVEL = "medium";
    static final String EXPERT_LEVEL = "expert";
    private static final long serialVersionUID = 1L;
    private HighscoreLevelPanel highscoreLevelEasyPanel;
    private HighscoreLevelPanel highscoreLevelMediumPanel;
    private HighscoreLevelPanel highscoreLevelExpertPanel;

    private HighscoreMainLevelPanel() {
        setLayout(new GridLayout(1, 3));
        setHighscoreLevelEasyPanel(HighscoreLevelPanel.createPanel(EASY_LEVEL));
        setHighscoreLevelMediumPanel(HighscoreLevelPanel.createPanel(MEDIUM_LEVEL));
        setHighscoreLevelExpertPanel(HighscoreLevelPanel.createPanel(EXPERT_LEVEL));
        add(highscoreLevelEasyPanel);
        add(highscoreLevelMediumPanel);
        add(highscoreLevelExpertPanel);
    }

    static HighscoreMainLevelPanel createPanel() {
        return new HighscoreMainLevelPanel();
    }

    HighscoreLevelPanel getHighscoreLevelEasyPanel() {
        return highscoreLevelEasyPanel;
    }

    private void setHighscoreLevelEasyPanel(
            HighscoreLevelPanel highscoreLevelEasyPanel) {
        this.highscoreLevelEasyPanel = highscoreLevelEasyPanel;
    }

    HighscoreLevelPanel getHighscoreLevelMediumPanel() {
        return highscoreLevelMediumPanel;
    }

    private void setHighscoreLevelMediumPanel(
            HighscoreLevelPanel highscoreLevelMediumPanel) {
        this.highscoreLevelMediumPanel = highscoreLevelMediumPanel;
    }

    HighscoreLevelPanel getHighscoreLevelExpertPanel() {
        return highscoreLevelExpertPanel;
    }

    private void setHighscoreLevelExpertPanel(
            HighscoreLevelPanel highscoreLevelExpertPanel) {
        this.highscoreLevelExpertPanel = highscoreLevelExpertPanel;
    }

}
