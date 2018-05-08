package actions.cellrightclick;

import actions.GenericAbstractInputObjectAction;
import views.components.Cell;

public class CellRightClickAction extends GenericAbstractInputObjectAction<RightClickActionInput> {
	
	@Override
	public int execute(RightClickActionInput input) {
		
		Cell cell = input.getCell();
		
		if (cell.isHidden()) {
			if (!cell.isFlagged()) {
				cell.flagCell();
			} else {
				cell.unFlagCell();
			}
		}
		return 0;

	}

}