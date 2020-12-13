package actions.cellrightclick;

import views.main.panels.Cell;

public class RightClickActionInput {

    private Cell cell;

    public RightClickActionInput(Cell cell) {
        setCell(cell);
    }

    Cell getCell() {
        return cell;
    }

    private void setCell(Cell cell) {
        this.cell = cell;
    }

}
