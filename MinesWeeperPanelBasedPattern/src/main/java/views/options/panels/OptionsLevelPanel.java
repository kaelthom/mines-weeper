package views.options.panels;

import constants.Levels;
import dto.DeminorGameProperties;
import dto.DeminorGamePropertiesFactory;
import messages.Labels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class OptionsLevelPanel extends JPanel implements ActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionsLevelPanel.class);

    private static final long serialVersionUID = 4413639017518473446L;

    private static final String[] LEVEL_LABEL = {
            Labels.OPTIONS_LEVEL_EASY,
            Labels.OPTIONS_LEVEL_MEDIUM,
            Labels.OPTIONS_LEVEL_EXPERT,
            Labels.OPTIONS_LEVEL_CUSTOM
    };
    private static int[] mnemonicByLevel = {KeyEvent.VK_E, KeyEvent.VK_I, KeyEvent.VK_H, KeyEvent.VK_C};
    private static int[] bombsByLevel = {10, 40, 300, 0};
    private static int[] deminorCellsPerWidthByLevel = {10, 15, 40, 0};
    private static int[] deminorCellsPerHeightByLevel = {10, 15, 40, 0};
    private List<JRadioButton> levelButtons = new ArrayList<>();

    private ButtonGroup group;
    private int level;

    public OptionsLevelPanel() {
        super();
        setLayout(new GridLayout(4, 1));

        group = new ButtonGroup();
        for (int iLevel = 0; iLevel < LEVEL_LABEL.length; iLevel++) {
            JRadioButton levelButton = new JRadioButton(deminorCellsPerWidthByLevel[iLevel] + "x" + deminorCellsPerHeightByLevel[iLevel] + " " + bombsByLevel[iLevel] + " bombs " + LEVEL_LABEL[iLevel]);
            levelButton.setMnemonic(mnemonicByLevel[iLevel]);
            levelButton.addActionListener(this);
            levelButton.setActionCommand(LEVEL_LABEL[iLevel]);
            levelButtons.add(levelButton);
            add(levelButton);
            group.add(levelButton);
        }
        int currentLevel = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance().getLevel();
        levelButtons.get(currentLevel).setSelected(true);
        setLevel(currentLevel);

    }

    public static int[] getDeminorCellsPerWidthByLevel() {
        return deminorCellsPerWidthByLevel;
    }

    public static void setDeminorCellsPerWidthByLevel(
            int[] deminorCellsPerWidthByLevel) {
        OptionsLevelPanel.deminorCellsPerWidthByLevel = deminorCellsPerWidthByLevel;
    }

    public static int[] getBombsByLevel() {
        return bombsByLevel;
    }

    public static void setBombsByLevel(int[] bombsByLevel) {
        OptionsLevelPanel.bombsByLevel = bombsByLevel;
    }

    public static int[] getDeminorCellsPerHeightByLevel() {
        return deminorCellsPerHeightByLevel;
    }

    public static void setDeminorCellsPerHeightByLevel(
            int[] deminorCellsPerHeightByLevel) {
        OptionsLevelPanel.deminorCellsPerHeightByLevel = deminorCellsPerHeightByLevel;
    }

    public static String[] getLevelLabel() {
        return LEVEL_LABEL;
    }

    public ButtonGroup getGroup() {
        return group;
    }

    public void setGroup(ButtonGroup group) {
        this.group = group;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int iLevel = 0; iLevel < LEVEL_LABEL.length; iLevel++) {
            if (e.getActionCommand().equals(LEVEL_LABEL[iLevel])) {
                LOGGER.info("iLevel : {}", iLevel);
                setLevel(iLevel);
                DeminorGameProperties gameProperties = new DeminorGameProperties(iLevel);
                CustomLevelPanel.getCellsPerColumnTextField().setText(Integer.toString(gameProperties.getCellsPerColumn()));
                CustomLevelPanel.getCellsPerWidthTextField().setText(Integer.toString(gameProperties.getCellsPerLine()));
                CustomLevelPanel.getnBombsTextField().setText(Integer.toString(gameProperties.getnBombs()));
                boolean editable = iLevel == Levels.CUSTOM;
                CustomLevelPanel.getCellsPerColumnTextField().setEditable(editable);
                CustomLevelPanel.getCellsPerWidthTextField().setEditable(editable);
                CustomLevelPanel.getnBombsTextField().setEditable(editable);
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
