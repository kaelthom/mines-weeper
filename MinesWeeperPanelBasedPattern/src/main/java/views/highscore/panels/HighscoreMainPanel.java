package views.highscore.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighscoreMainPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private HighscorePanel highscorePanel;

    private HighscoreMainPanel(int level) {
        super(new BorderLayout(10, 10));
        HighscoreMainLevelPanel highscoreMainLevelPanel = HighscoreMainLevelPanel.createPanel();
        highscoreMainLevelPanel.getHighscoreLevelEasyPanel().getLevelButton().setActionCommand("easy");
        highscoreMainLevelPanel.getHighscoreLevelEasyPanel().getLevelButton().addActionListener(this);
        highscoreMainLevelPanel.getHighscoreLevelMediumPanel().getLevelButton().setActionCommand("medium");
        highscoreMainLevelPanel.getHighscoreLevelMediumPanel().getLevelButton().addActionListener(this);
        highscoreMainLevelPanel.getHighscoreLevelExpertPanel().getLevelButton().setActionCommand("expert");
        highscoreMainLevelPanel.getHighscoreLevelExpertPanel().getLevelButton().addActionListener(this);
        highscorePanel = HighscorePanel.createPanel(level);
        add(highscoreMainLevelPanel, BorderLayout.NORTH);
        add(highscorePanel, BorderLayout.CENTER);
    }

    public static HighscoreMainPanel createPanel(int level) {
        return new HighscoreMainPanel(level);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int level = 3;
        if (e.getActionCommand().equals(HighscoreMainLevelPanel.EASY_LEVEL)) {
            level = 0;
        } else if (e.getActionCommand().equals(HighscoreMainLevelPanel.MEDIUM_LEVEL)) {
            level = 1;
        } else if (e.getActionCommand().equals(HighscoreMainLevelPanel.EXPERT_LEVEL)) {
            level = 2;
        }
        highscorePanel.setVisible(false);
        remove(highscorePanel);
        highscorePanel = HighscorePanel.createPanel(level);
        add(highscorePanel);
        highscorePanel.setVisible(true);
    }

}
