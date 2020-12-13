package actions.cellleftclick;

import dto.DeminorGameProperties;
import views.main.panels.Cell;
import views.main.panels.CellsPanel;

public class LeftClickActionInput {

    private Cell cell;

    private CellsPanel cellsPanel;

    private DeminorGameProperties gameProperties;

    public LeftClickActionInput(Cell cell, CellsPanel cellsPanel, DeminorGameProperties gameProperties) {
        setCell(cell);
        setCellsPanel(cellsPanel);
        setGameProperties(gameProperties);
    }

    Cell getCell() {
        return cell;
    }

    private void setCell(Cell cell) {
        this.cell = cell;
    }

    CellsPanel getCellsPanel() {
        return cellsPanel;
    }

    private void setCellsPanel(CellsPanel cellsPanel) {
        this.cellsPanel = cellsPanel;
    }

    public DeminorGameProperties getGameProperties() {
        return gameProperties;
    }

    public void setGameProperties(DeminorGameProperties gameProperties) {
        this.gameProperties = gameProperties;
    }

}
