package actions.optionssubmit;

import javax.swing.JFrame;

import actions.GenericAbstractInputObjectAction;
import dto.DeminorGameProperties;
import views.main.DeminorView;
import views.main.menu.MenuFrame;
import views.main.panels.DeminorPanel;
import views.options.OptionsView;

public class OptionsSubmitAction extends GenericAbstractInputObjectAction<OptionsSubmitActionInput>{
	
	public int execute (OptionsSubmitActionInput input) {
		DeminorGameProperties gameProperties = input.getGameProperties();

		MenuFrame deminorFrame = DeminorView.getFrame();
		JFrame optionsFrame = OptionsView.getFrame();
		optionsFrame.setVisible(false);
		deminorFrame.setVisible(true);
		deminorFrame.getContentPane().removeAll();
		
		DeminorPanel deminorPanel = DeminorPanel.createPanel(gameProperties);
        DeminorView.setDeminorPanel(deminorPanel);
        DeminorView.resize();
        deminorFrame.getContentPane().add(deminorPanel);
		deminorFrame.repaint();
		return 0;
	}
}
