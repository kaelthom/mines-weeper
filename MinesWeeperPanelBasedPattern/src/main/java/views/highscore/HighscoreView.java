// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreView.java

package views.highscore;

import messages.Labels;
import views.highscore.panels.HighscoreMainPanel;

import javax.swing.*;
import java.awt.*;

public class HighscoreView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JFrame frame;

    public HighscoreView(int level) {
        setLayout(new GridLayout(1, 1));
        add(HighscoreMainPanel.createPanel(level));
    }

    private static void createAndShowGUI(JPanel panel) {
        frame = new JFrame(Labels.HIGHSCORES_TITLE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void launchFrame(final JPanel panel) {
        SwingUtilities.invokeLater(
                () -> {
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    HighscoreView.createAndShowGUI(panel);
                });
    }

    public static JFrame getFrame() {
        return frame;
    }

}
