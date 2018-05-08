package actions.cellrightclick;

import views.components.Cell;

public class RightClickActionInput {

	private Cell cell;

	public RightClickActionInput(Cell cell) {
		this.cell = cell;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
}
