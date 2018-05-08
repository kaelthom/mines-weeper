package actions.optionssubmit;

import javax.swing.JFrame;

import actions.GenericAbstractInputObjectAction;
import dto.DeminorGameProperties;
import views.DeminorView;
import views.OptionsView;
import views.frame.MenuFrame;
import views.panels.CustomLevelPanel;
import views.panels.DeminorPanel;
import views.panels.OptionsLevelPanel;

public class OptionsSubmitAction extends GenericAbstractInputObjectAction<OptionsSubmitActionInput>{
	
	public int execute (OptionsSubmitActionInput input) {
		OptionsLevelPanel optionsLevelPanel = input.getOptionsLevelPanel();
		int level = optionsLevelPanel.getLevel();
		int cellsPerLine = Integer.parseInt(CustomLevelPanel.getCellsPerWidthTextField().getText());
		int cellsPerColumn = Integer.parseInt(CustomLevelPanel.getCellsPerColumnTextField().getText());
		int nBombs = Integer.parseInt(CustomLevelPanel.getnBombsTextField().getText());
		System.out.println("level : " + level);
		System.out.println("cells per width  : " + cellsPerLine);
		System.out.println("cells per column : " + cellsPerColumn);
		System.out.println("number of bombs  : " + nBombs);
		
		MenuFrame deminorFrame = DeminorView.getFrame();
		JFrame optionsFrame = OptionsView.getFrame();
		optionsFrame.setVisible(false);
		deminorFrame.setVisible(true);
		deminorFrame.getContentPane().removeAll();
		
		DeminorPanel deminorPanel = DeminorPanel.createPanel(new DeminorGameProperties(level,nBombs,cellsPerLine,cellsPerColumn));
        DeminorView.setDeminorPanel(deminorPanel);
        DeminorView.resize();
        deminorFrame.getContentPane().add(deminorPanel);
		deminorFrame.repaint();
		return 0;
	}
}
