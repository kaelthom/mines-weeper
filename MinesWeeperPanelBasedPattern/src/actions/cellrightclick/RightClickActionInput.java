package actions.cellrightclick;

import views.main.Cell;

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
