package views.highscore.panels;

import javax.swing.*;
import java.awt.*;

public class HighscoreMainLevelPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private HighscoreLevelPanel highscoreLevelEasyPanel;
    private HighscoreLevelPanel highscoreLevelMediumPanel;
    private HighscoreLevelPanel highscoreLevelExpertPanel;

    private HighscoreMainLevelPanel() {
        setLayout(new GridLayout(1, 3));
        highscoreLevelEasyPanel = HighscoreLevelPanel.createPanel("easy");
        highscoreLevelMediumPanel = HighscoreLevelPanel.createPanel("medium");
        highscoreLevelExpertPanel = HighscoreLevelPanel.createPanel("expert");
        add(highscoreLevelEasyPanel);
        add(highscoreLevelMediumPanel);
        add(highscoreLevelExpertPanel);
    }

    public static HighscoreMainLevelPanel createPanel() {
        return new HighscoreMainLevelPanel();
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

}
