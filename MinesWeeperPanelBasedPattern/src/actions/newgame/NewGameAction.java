package actions.newgame;

import javax.swing.JFrame;

import actions.GenericAbstractInputObjectAction;
import dto.DeminorGameProperties;
import views.main.DeminorView;

public class NewGameAction extends GenericAbstractInputObjectAction<NewGameActionInput>{

	@Override
	public int execute(NewGameActionInput input) {
		DeminorGameProperties gameProperties = input.getDeminorProperties();

        JFrame deminorFrame = DeminorView.getFrame();
        deminorFrame.getContentPane().removeAll();
        DeminorView.createDeminorPanel(gameProperties);
        deminorFrame.add(DeminorView.getDeminorPanel());
        deminorFrame.repaint();

		return 0;
	}

}
