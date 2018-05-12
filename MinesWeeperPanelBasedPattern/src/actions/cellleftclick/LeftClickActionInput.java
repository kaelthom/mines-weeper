package actions.cellleftclick;

import dto.DeminorGameProperties;
import views.main.Cell;
import views.main.panels.CellsPanel;

public class LeftClickActionInput {

	private Cell cell;

	private CellsPanel cellsPanel;
	
	private DeminorGameProperties gameProperties;
	
	public LeftClickActionInput(Cell cell, CellsPanel cellsPanel, DeminorGameProperties gameProperties) {
		this.cell = cell;
		this.cellsPanel = cellsPanel;
		this.gameProperties = gameProperties;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	public CellsPanel getCellsPanel() {
		return cellsPanel;
	}

	public void setCellsPanel(CellsPanel cellsPanel) {
		this.cellsPanel = cellsPanel;
	}

	public DeminorGameProperties getGameProperties() {
		return gameProperties;
	}

	public void setGameProperties(DeminorGameProperties gameProperties) {
		this.gameProperties = gameProperties;
	}

}
