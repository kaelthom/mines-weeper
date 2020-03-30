package views.main.panels;

import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import dto.DeminorPanelProperties;

public class CellsPanel extends JPanel {

	private static final long serialVersionUID = -5474923082252270189L;
	
	private static int cellsPerLine;
	private static int cellsPerColumn;
	private static int nBombs;
	
	private List<Cell> cells = new ArrayList<>(); 
	private List<Integer> minedCells = new ArrayList<>(); 
	private int discoveredCells; 

	private Rectangle bounds;
	
	private static boolean lost; 
	private static boolean won; 

	public CellsPanel(int cellsPerLine, int cellsPerColumn, int nBombs) {
		super();

		int cellWidth = DeminorPanelProperties.getCellWidth();
		int cellHeight = DeminorPanelProperties.getCellHeight();

		this.bounds = calculateMinesPanelBounds(cellsPerLine, cellsPerColumn);

		setLayout(null);
		setBounds(this.bounds);
		setOpaque(true);

		setCellsPerColumn(cellsPerColumn);
		setCellsPerLine(cellsPerLine);
		setLost(false);
		setWon(false);
		setDiscoveredCells(0);
		setnBombs(nBombs);
		
		int buttonsPanelXMargin = DeminorPanelProperties.getButtonsPanelXMargin();
		int buttonsPanelYMargin = DeminorPanelProperties.getButtonsPanelYMargin();
		int xSpaceBtwButtons = DeminorPanelProperties.getxSpaceBtwButtons();
		int ySpaceBtwButtons = DeminorPanelProperties.getySpaceBtwButtons();
		
		int nRemainingBombs=nBombs;
		int nRemainingCells=cellsPerLine*cellsPerColumn;
		double bombprobability = nRemainingBombs*1d/nRemainingCells;
		Cell.setBombProbability(bombprobability);
		
		for (int jOcc = 0 ; jOcc < cellsPerColumn ; jOcc++){
			for (int iOcc = 0 ; iOcc < cellsPerLine ; iOcc++){
				int cellX = buttonsPanelXMargin + iOcc * (cellWidth + xSpaceBtwButtons);
				int cellY = buttonsPanelYMargin + jOcc * (cellHeight + ySpaceBtwButtons);
				Rectangle cellBounds = new Rectangle(cellX,cellY,cellWidth, cellHeight);
				int index = getCellIndex(iOcc, jOcc);
				Cell cell = new Cell(cellBounds,iOcc,jOcc,index);
				cell.setBounds(cellBounds);
				Insets m = new Insets(0, 0, 0, 0);
				cell.setMargin(m);
				getCells().add(cell);
				if (cell.isMined()) {
					minedCells.add(index);
					nRemainingBombs--;
				}
				nRemainingCells--;
				if ( nRemainingCells != 0 )  bombprobability = nRemainingBombs*1d/nRemainingCells;
				Cell.setBombProbability(bombprobability);
			}
		}
		for (int jOcc = 0 ; jOcc < cellsPerColumn ; jOcc++){
			for (int iOcc = 0 ; iOcc < cellsPerLine ; iOcc++){
				Cell cell = getCells().get(getCellIndex(iOcc, jOcc));
				if (!cell.isMined()) { 
					countBombsAround(iOcc,jOcc,cell);
				}
			}
		}
	}

	private static Rectangle calculateMinesPanelBounds(int cellsPerLine, int cellsPerHeight) {
		int minesPanelX = DeminorPanelProperties.getMinesPanelX();
		int minesPanelY = DeminorPanelProperties.getMinesPanelY();
		int minesPanelWidth = cellsPerLine*DeminorPanelProperties.getCellWidth();
		int minesPanelHeight = cellsPerHeight*DeminorPanelProperties.getCellHeight();
		
		return new Rectangle(minesPanelX,minesPanelY,minesPanelWidth, minesPanelHeight);
	}
	
	public List<Cell> getCells() {
		return cells;
	}

	public void setButtons(List<Cell> buttons) {
		this.cells = buttons;
	}

	public int getCellIndex(int x, int y) {
		return y * cellsPerLine + x;
		
	}

	public Point getCellPosition(int index) {
		return new Point(index % cellsPerLine,index/cellsPerLine);
		
	}

	public static int getCellsPerLine() {
		return cellsPerLine;
	}

	public static void setCellsPerLine(int cellsPerWidth) {
		CellsPanel.cellsPerLine = cellsPerWidth;
	}

	public static int getCellsPerColumn() {
		return cellsPerColumn;
	}

	public static void setCellsPerColumn(int cellsPerHeight) {
		CellsPanel.cellsPerColumn = cellsPerHeight;
	}

	public static int getnBombs() {
		return nBombs;
	}

	public static void setnBombs(int nBombs) {
		CellsPanel.nBombs = nBombs;
	}

	public void countBombsAround(int iOcc, int jOcc, Cell cell) {
		
		if (iOcc > 0 && iOcc < cellsPerLine-1){
			if (jOcc > 0 && jOcc < cellsPerColumn-1){
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == 0 && jOcc != cellsPerColumn-1) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerColumn-1 && jOcc != 0) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerColumn-1 && jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
			}
		} else if (iOcc == 0 && iOcc != cellsPerLine-1) {
			if (jOcc > 0 && jOcc < cellsPerColumn-1){
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == 0 && jOcc != cellsPerColumn-1) {
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerColumn-1 && jOcc != 0) {
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerColumn-1 && jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
			}
		} else if (iOcc == cellsPerLine-1 && iOcc != 0) {
			if (jOcc > 0 && jOcc < cellsPerColumn-1){
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == 0 && jOcc != cellsPerColumn-1) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerColumn-1 && jOcc != 0) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerColumn-1 && jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
			}
		} else if (iOcc == cellsPerLine-1 && iOcc == 0) {
			if (jOcc > 0 && jOcc < cellsPerColumn-1){
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerColumn-1) {
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
			}
		}
	}

	public void showNeighbourBlankCells(int iOcc, int jOcc) {
		if (iOcc > 0 && iOcc < cellsPerLine-1){
			if (jOcc > 0 && jOcc < cellsPerColumn-1){
				showCurrentAndNeighbourCells(iOcc-1, jOcc-1,getCells().get(getCellIndex(iOcc-1, jOcc-1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc-1,getCells().get(getCellIndex(iOcc+1, jOcc-1)));
				showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc-1, jOcc+1,getCells().get(getCellIndex(iOcc-1, jOcc+1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc+1,getCells().get(getCellIndex(iOcc+1, jOcc+1)));
			} else if (jOcc == 0 && jOcc != cellsPerColumn-1) {
				showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc-1, jOcc+1,getCells().get(getCellIndex(iOcc-1, jOcc+1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc+1,getCells().get(getCellIndex(iOcc+1, jOcc+1)));
			} else if (jOcc == cellsPerColumn-1 && jOcc != 0) {
				showCurrentAndNeighbourCells(iOcc-1, jOcc-1,getCells().get(getCellIndex(iOcc-1, jOcc-1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc-1,getCells().get(getCellIndex(iOcc+1, jOcc-1)));
				showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
			} else if (jOcc == cellsPerColumn-1 && jOcc == 0) {
				showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
			}
		} else if (iOcc == 0 && iOcc != cellsPerLine-1) {
			if (jOcc > 0 && jOcc < cellsPerColumn-1){
				showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc-1,getCells().get(getCellIndex(iOcc+1, jOcc-1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc+1,getCells().get(getCellIndex(iOcc+1, jOcc+1)));
			} else if (jOcc == 0 && jOcc != cellsPerColumn-1) {
				showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc+1,getCells().get(getCellIndex(iOcc+1, jOcc+1)));
			} else if (jOcc == cellsPerColumn-1 && jOcc != 0) {
				showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc-1,getCells().get(getCellIndex(iOcc+1, jOcc-1)));
				showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
			} else if (jOcc == cellsPerColumn-1 && jOcc == 0) {
				showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
			}
		} else if (iOcc == cellsPerLine-1 && iOcc != 0) {
			if (jOcc > 0 && jOcc < cellsPerColumn-1){
				showCurrentAndNeighbourCells(iOcc-1, jOcc-1,getCells().get(getCellIndex(iOcc-1, jOcc-1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc-1, jOcc+1,getCells().get(getCellIndex(iOcc-1, jOcc+1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
			} else if (jOcc == 0 && jOcc != cellsPerColumn-1) {
				showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				showCurrentAndNeighbourCells(iOcc-1, jOcc+1,getCells().get(getCellIndex(iOcc-1, jOcc+1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
			} else if (jOcc == cellsPerColumn-1 && jOcc != 0) {
				showCurrentAndNeighbourCells(iOcc-1, jOcc-1,getCells().get(getCellIndex(iOcc-1, jOcc-1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
			} else if (jOcc == cellsPerColumn-1 && jOcc == 0) {
				showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
			}
		} else if (iOcc == cellsPerLine-1 && iOcc == 0) {
			if (jOcc > 0 && jOcc < cellsPerColumn-1){
				showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
			} else if (jOcc == 0) {
				showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
			} else if (jOcc == cellsPerColumn-1) {
				showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
			}
		}
	}

	public void showCurrentAndNeighbourCells(int iOcc, int jOcc, Cell currentCell) {
		if (currentCell.isHidden()) {
			currentCell.showCell(this);	
			if (currentCell.getnBombsaround() == 0 && !currentCell.isMined()) {
				showNeighbourBlankCells(iOcc, jOcc);
			}
		}
	}

	public static boolean isLost() {
		return lost;
	}

	public static void setLost(boolean lost) {
		CellsPanel.lost = lost;
	}

	public List<Integer> getMinedCells() {
		return minedCells;
	}

	public void setMinedCells(List<Integer> minedCells) {
		this.minedCells = minedCells;
	}

	public int getDiscoveredCells() {
		return discoveredCells;
	}

	public void setDiscoveredCells(int discoveredCells) {
		this.discoveredCells = discoveredCells;
	}

	public static boolean isWon() {
		return won;
	}

	public static void setWon(boolean won) {
		CellsPanel.won = won;
	}

	public void incrementDiscoveredCells() {
		discoveredCells++;
	}
}
