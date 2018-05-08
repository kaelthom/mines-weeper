package dto;

import views.panels.CustomLevelPanel;
import views.panels.OptionsLevelPanel;

public class DeminorGameProperties {

	private int nBombs;
	
	private int level;
	
	private int cellsPerLine;

	private int cellsPerColumn;
	
	private long time;

	private int percent;

	public DeminorGameProperties(int level) {
		this.level = level;
		calculateGamePropertiesFromLevel(level);
	}

	private void calculateGamePropertiesFromLevel(int iLevel) {
		this.cellsPerLine  = 0; 
		this.cellsPerColumn = 0;
		this.nBombs = 0;
		
		if (level >= 0 && level <= 2) {
			this.cellsPerLine = OptionsLevelPanel.getDeminorCellsPerWidthByLevel()[iLevel];
			this.cellsPerColumn = OptionsLevelPanel.getDeminorCellsPerHeightByLevel()[iLevel];
			this.nBombs = OptionsLevelPanel.getBombsByLevel()[iLevel];
		} else {
			this.cellsPerLine = Integer.parseInt(CustomLevelPanel.getCellsPerWidthTextField().getText());
			this.cellsPerColumn = Integer.parseInt(CustomLevelPanel.getCellsPerColumnTextField().getText());
			this.nBombs = Integer.parseInt(CustomLevelPanel.getnBombsTextField().getText());
		}

	}

	public DeminorGameProperties(int level, int nBombs, int cellsPerLine, int cellsPerColumn) {
		this.nBombs = nBombs;
		this.level = level;
		this.cellsPerLine = cellsPerLine;
		this.cellsPerColumn = cellsPerColumn;
	}

	public int getnBombs() {
		return nBombs;
	}

	public void setnBombs(int nBombs) {
		this.nBombs = nBombs;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCellsPerLine() {
		return cellsPerLine;
	}

	public void setCellsPerLine(int cellsPerLine) {
		this.cellsPerLine = cellsPerLine;
	}

	public int getCellsPerColumn() {
		return cellsPerColumn;
	}

	public void setCellsPerColumn(int cellsPerColumn) {
		this.cellsPerColumn = cellsPerColumn;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

}
