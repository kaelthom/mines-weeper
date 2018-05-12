package actions.newgame;

import actions.GenericAbstractInputObjectAction;
import dto.DeminorGameProperties;
import views.DeminorView;
import views.frame.MenuFrame;

public class NewGameAction extends GenericAbstractInputObjectAction<NewGameActionInput>{

	@Override
	public int execute(NewGameActionInput input) {
		DeminorGameProperties gameProperties = input.getDeminorProperties();

        MenuFrame deminorFrame = DeminorView.getFrame();
        deminorFrame.getContentPane().removeAll();
        DeminorView.createDeminorPanel(gameProperties);
        deminorFrame.add(DeminorView.getDeminorPanel());
        deminorFrame.repaint();

		return 0;
	}

}
