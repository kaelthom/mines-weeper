package actions.optionssubmit;

import javax.swing.JFrame;

import actions.GenericAbstractInputObjectAction;
import dto.DeminorGameProperties;
import views.main.DeminorView;
import views.main.panels.DeminorPanel;
import views.options.OptionsView;

public class OptionsSubmitAction extends GenericAbstractInputObjectAction<OptionsSubmitActionInput>{
	
	public int execute (OptionsSubmitActionInput input) {
		DeminorGameProperties gameProperties = input.getGameProperties();

		JFrame optionsFrame = OptionsView.getFrame();
		optionsFrame.setVisible(false);

		JFrame deminorFrame = DeminorView.getFrame();
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
