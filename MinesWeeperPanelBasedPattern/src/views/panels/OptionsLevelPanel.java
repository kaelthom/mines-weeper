package views.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import constants.Levels;
import dto.DeminorGameProperties;
import dto.DeminorGamePropertiesFactory;
import messages.Labels;

public class OptionsLevelPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 4413639017518473446L;

	private static final String[] LEVEL_LABEL = {
			                                     Labels.OPTIONS_LEVEL_EASY,
			                                     Labels.OPTIONS_LEVEL_MEDIUM,
			                                     Labels.OPTIONS_LEVEL_EXPERT,
			                                     Labels.OPTIONS_LEVEL_CUSTOM
			                                    };
	private static int MnemonicByLevel[] = {KeyEvent.VK_E,KeyEvent.VK_I,KeyEvent.VK_H,KeyEvent.VK_C};
	private static int bombsByLevel                [] = {10,40,300,0};
	private static int deminorCellsPerWidthByLevel [] = {10,15, 40,0};
	private static int deminorCellsPerHeightByLevel[] = {10,15, 40,0};
	private List<JRadioButton> levelButtons = new ArrayList<JRadioButton>();
	
	private ButtonGroup group;
	private int level;
	
	public OptionsLevelPanel() {
		super();
		setLayout(new GridLayout(4,1));

		group = new ButtonGroup();
		for (int iLevel = 0 ; iLevel < LEVEL_LABEL.length ; iLevel++) {
			JRadioButton levelButton = new JRadioButton(deminorCellsPerWidthByLevel[iLevel] + "x" + deminorCellsPerHeightByLevel[iLevel] + " " + bombsByLevel[iLevel] + " bombs " + LEVEL_LABEL[iLevel]);
			levelButton.setMnemonic(MnemonicByLevel[iLevel]);
			levelButton.addActionListener(this);
			levelButton.setActionCommand(LEVEL_LABEL[iLevel]);
			levelButtons.add(levelButton);
			add(levelButton);
			group.add(levelButton);
		}
		int level = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance().getLevel();
		levelButtons.get(level).setSelected(true);
		setLevel(level);
		
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

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int iLevel = 0 ; iLevel < LEVEL_LABEL.length ; iLevel++) {
			if (e.getActionCommand().equals(LEVEL_LABEL[iLevel])) {
				System.out.println("iLevel : " + iLevel);
				setLevel(iLevel);
				DeminorGameProperties gameProperties = new DeminorGameProperties(iLevel);
				CustomLevelPanel.getCellsPerColumnTextField().setText(Integer.toString(gameProperties.getCellsPerColumn()));
				CustomLevelPanel.getCellsPerWidthTextField().setText(Integer.toString(gameProperties.getCellsPerLine()));
				CustomLevelPanel.getnBombsTextField().setText(Integer.toString(gameProperties.getnBombs()));
				boolean editable = iLevel == Levels.CUSTOM? true:false;
				CustomLevelPanel.getCellsPerColumnTextField().setEditable(editable);
				CustomLevelPanel.getCellsPerWidthTextField().setEditable(editable);
				CustomLevelPanel.getnBombsTextField().setEditable(editable);
			}
		}
	}

	public static String[] getLevelLabel() {
		return LEVEL_LABEL;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
