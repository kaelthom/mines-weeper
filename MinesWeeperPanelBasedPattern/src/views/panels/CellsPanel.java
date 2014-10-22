package views.panels;

import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import views.components.Cell;

public class CellsPanel extends JPanel {

	private static final long serialVersionUID = -5474923082252270189L;
	private static int buttonsPanelXMargin = 0;
	private static int buttonsPanelYMargin = 0;
	private static int xSpaceBtwButtons = 0;
	private static int ySpaceBtwButtons = 0;
	private static int cellsPerWidth;
	private static int cellsPerHeight;
	private static int nBombs;
	private List<Cell> cells = new ArrayList<Cell>(); 
	private List<Integer> minedCells = new ArrayList<Integer>(); 
	private int discoveredCells; 

	private static boolean lost; 
	private static boolean won; 

	public CellsPanel(Rectangle bounds, int cellsPerWidth, int cellsPerHeight, int cellWidth, int cellHeight, int nBombs) {
		super();
		setLayout(null);
		setBounds(bounds);
		setOpaque(true);

		setCellsPerHeight(cellsPerHeight);
		setCellsPerWidth(cellsPerWidth);
		setLost(false);
		setWon(false);
		setDiscoveredCells(0);
		setnBombs(nBombs);
		
		int nRemainingBombs=nBombs;
		int nRemainingCells=cellsPerWidth*cellsPerHeight;
		double bombprobability = nRemainingBombs*1d/nRemainingCells;
		Cell.setBombProbability(bombprobability);
		
		for (int jOcc = 0 ; jOcc < cellsPerHeight ; jOcc++){
			for (int iOcc = 0 ; iOcc < cellsPerWidth ; iOcc++){
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
		for (int jOcc = 0 ; jOcc < cellsPerHeight ; jOcc++){
			for (int iOcc = 0 ; iOcc < cellsPerWidth ; iOcc++){
				Cell cell = getCells().get(getCellIndex(iOcc, jOcc));
				if (!cell.isMined()) { 
					countBombsAround(iOcc,jOcc,cell);
				}
			}
		}
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setButtons(List<Cell> buttons) {
		this.cells = buttons;
	}

	public int getCellIndex(int x, int y) {
		int index = (y) * cellsPerWidth + x;
		return index;
		
	}

	public Point getCellPosition(int index) {
		Point cellPoint = new Point(index % cellsPerWidth,index/cellsPerWidth);
		return cellPoint;
		
	}

	public static int getCellsPerWidth() {
		return cellsPerWidth;
	}

	public static void setCellsPerWidth(int cellsPerWidth) {
		CellsPanel.cellsPerWidth = cellsPerWidth;
	}

	public static int getCellsPerHeight() {
		return cellsPerHeight;
	}

	public static void setCellsPerHeight(int cellsPerHeight) {
		CellsPanel.cellsPerHeight = cellsPerHeight;
	}

	public static int getnBombs() {
		return nBombs;
	}

	public static void setnBombs(int nBombs) {
		CellsPanel.nBombs = nBombs;
	}

	public void countBombsAround(int iOcc, int jOcc, Cell cell) {
		if (iOcc > 0 && iOcc < cellsPerWidth-1){
			if (jOcc > 0 && jOcc < cellsPerHeight-1){
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerHeight-1) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
			}
		} else if (iOcc == 0) {
			if (jOcc > 0 && jOcc < cellsPerHeight-1){
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerHeight-1) {
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isMined()) cell.incrementNBombsaround();
			}
		} else if (iOcc == cellsPerWidth-1) {
			if (jOcc > 0 && jOcc < cellsPerHeight-1){
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isMined()) cell.incrementNBombsaround();
			} else if (jOcc == cellsPerHeight-1) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isMined()) cell.incrementNBombsaround();
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isMined()) cell.incrementNBombsaround();
			}
		}
	}

	public void showNeighbourBlankCells(int iOcc, int jOcc) {
		if (iOcc > 0 && iOcc < cellsPerWidth-1){
			if (jOcc > 0 && jOcc < cellsPerHeight-1){
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc-1,getCells().get(getCellIndex(iOcc-1, jOcc-1)));
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc-1,getCells().get(getCellIndex(iOcc+1, jOcc-1)));
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc+1,getCells().get(getCellIndex(iOcc-1, jOcc+1)));
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc+1,getCells().get(getCellIndex(iOcc+1, jOcc+1)));
			} else if (jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc+1,getCells().get(getCellIndex(iOcc-1, jOcc+1)));
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc+1,getCells().get(getCellIndex(iOcc+1, jOcc+1)));
			} else if (jOcc == cellsPerHeight-1) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc-1,getCells().get(getCellIndex(iOcc-1, jOcc-1)));
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc-1,getCells().get(getCellIndex(iOcc+1, jOcc-1)));
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
			}
		} else if (iOcc == 0) {
			if (jOcc > 0 && jOcc < cellsPerHeight-1){
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc-1,getCells().get(getCellIndex(iOcc+1, jOcc-1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc+1,getCells().get(getCellIndex(iOcc+1, jOcc+1)));
			} else if (jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc+1,getCells().get(getCellIndex(iOcc+1, jOcc+1)));
			} else if (jOcc == cellsPerHeight-1) {
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc-1,getCells().get(getCellIndex(iOcc+1, jOcc-1)));
				if (getCells().get(getCellIndex(iOcc+1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc+1, jOcc  ,getCells().get(getCellIndex(iOcc+1, jOcc  )));
			}
		} else if (iOcc == cellsPerWidth-1) {
			if (jOcc > 0 && jOcc < cellsPerHeight-1){
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc-1,getCells().get(getCellIndex(iOcc-1, jOcc-1)));
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc+1,getCells().get(getCellIndex(iOcc-1, jOcc+1)));
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
			} else if (jOcc == 0) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
				if (getCells().get(getCellIndex(iOcc-1, jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc+1,getCells().get(getCellIndex(iOcc-1, jOcc+1)));
				if (getCells().get(getCellIndex(iOcc  , jOcc+1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc+1,getCells().get(getCellIndex(iOcc  , jOcc+1)));
			} else if (jOcc == cellsPerHeight-1) {
				if (getCells().get(getCellIndex(iOcc-1, jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc-1,getCells().get(getCellIndex(iOcc-1, jOcc-1)));
				if (getCells().get(getCellIndex(iOcc  , jOcc-1)).isHidden()) showCurrentAndNeighbourCells(iOcc  , jOcc-1,getCells().get(getCellIndex(iOcc  , jOcc-1)));
				if (getCells().get(getCellIndex(iOcc-1, jOcc  )).isHidden()) showCurrentAndNeighbourCells(iOcc-1, jOcc  ,getCells().get(getCellIndex(iOcc-1, jOcc  )));
			}
		}
	}

	public void showCurrentAndNeighbourCells(int iOcc, int jOcc, Cell cell) {
		if (cell.isHidden()) cell.showCell(this);
		if (cell.getnBombsaround() == 0 && !cell.isMined())
			showNeighbourBlankCells(iOcc, jOcc);
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
