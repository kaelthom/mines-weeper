package dto;

public class DeminorGameProperties {

	private int nBombs;
	
	private int level;
	
	private int cellsPerLine;

	private int cellsPerColumn;
	
	private long time;

	private int percent;

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
