package actions.newgame;

import actions.GenericAbstractInputObjectAction;
import dto.DeminorGameProperties;
import views.DeminorView;
import views.frame.MenuFrame;
import views.panels.DeminorPanel;

public class NewGameAction extends GenericAbstractInputObjectAction<NewGameActionInput>{

	@Override
	public int execute(NewGameActionInput input) {
		DeminorGameProperties deminorProperties = input.getDeminorProperties();

		MenuFrame deminorFrame = DeminorView.getFrame();
		deminorFrame.getContentPane().removeAll();;

		DeminorPanel deminorPanel = DeminorPanel.createPanel(deminorProperties);
		DeminorView.setDeminorPanel(deminorPanel);
		deminorFrame.add(deminorPanel);
		deminorFrame.repaint();

		return 0;
	}

}
