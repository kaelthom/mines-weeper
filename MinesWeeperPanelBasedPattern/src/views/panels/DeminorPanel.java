package views.panels;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import views.components.Cell;

public class DeminorPanel extends JPanel {

	private static final String GAMELOST = ResourceBundle.getBundle("labels").getString("main.gamelost");
	private static final String GAMEWON = ResourceBundle.getBundle("labels").getString("main.gamewon");

	private static final long serialVersionUID = 1L;

	private static int cellWidth = 25;
	private static int cellHeight = 25;

	private static int DeminorX = 0;
	private static int DeminorY = 0;
	private static Rectangle DeminorBounds;
	
	private static int minesPanelX = 10;
	private static int minesPanelY = 10;
	private static Rectangle minesPanelBounds;
	
	private static CellsPanel cellsPanel;
	
	public DeminorPanel() {
	}

	public static DeminorPanel createDeminorPanel(int iLevel) {
		int cellsPerWidth  = 0; 
		int cellsPerHeight = 0;
		int nBombs = 0;
		
		if (iLevel >= 0 && iLevel <= 2) {
			cellsPerWidth = OptionsLevelPanel.getDeminorCellsPerWidthByLevel()[iLevel];
			cellsPerHeight = OptionsLevelPanel.getDeminorCellsPerHeightByLevel()[iLevel];
			nBombs = OptionsLevelPanel.getBombsByLevel()[iLevel];
		} else {
			cellsPerWidth = CustomLevelPanel.getCellsPerWidth();
			cellsPerHeight = CustomLevelPanel.getCellsPerColumn();
			nBombs = CustomLevelPanel.getnBombs();
		}

		int minesPanelWidth = cellsPerWidth*cellWidth;
		int minesPanelHeight = cellsPerHeight*cellHeight;
		minesPanelBounds = new Rectangle(minesPanelX,minesPanelY,minesPanelWidth, minesPanelHeight);

		int deminorWidth = minesPanelWidth+minesPanelX*2;
		int deminorHeight = minesPanelHeight+minesPanelY*2;
		DeminorBounds = new Rectangle(DeminorX,DeminorY,deminorWidth, deminorHeight);

		DeminorPanel deminorPanel = new DeminorPanel();
		deminorPanel.setLayout(null);
		deminorPanel.setBounds(DeminorBounds);

		cellsPanel = new CellsPanel(minesPanelBounds,cellsPerWidth,cellsPerHeight,cellWidth,cellHeight,nBombs);

		for (int iOcc = 0 ; iOcc < cellsPerWidth * cellsPerHeight ; iOcc++){
			Cell button = cellsPanel.getCells().get(iOcc);
			button.setActionCommand("button" + iOcc);
			button.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					if (!CellsPanel.isLost() && !CellsPanel.isWon()){
						Object source = arg0.getSource();
						if (source instanceof Cell){
							Cell cell = (Cell) source;
							int index = cell.getIndex();
							if (SwingUtilities.isLeftMouseButton(arg0) && !cell.isFlagged()) {
								if (cell.isMined()) {
									CellsPanel.setLost(true);
									Iterator<Integer> it = cellsPanel.getMinedCells().iterator();
									cell.showCell(cellsPanel);
									while (it.hasNext()){
										int minedCellsIndex = it.next();
										if(index != minedCellsIndex) {
											Cell minedCell = cellsPanel.getCells().get(minedCellsIndex);
											minedCell.showCell(cellsPanel);
										}
									}
									JOptionPane.showMessageDialog(cellsPanel, GAMELOST);
								} else {
									cellsPanel.showCurrentAndNeighbourCells(cell.getxOcc(), cell.getyOcc(),cell);
									int nCellsToWin = CellsPanel.getCellsPerHeight()*CellsPanel.getCellsPerHeight()-CellsPanel.getnBombs();
									if (cellsPanel.getDiscoveredCells() == nCellsToWin) {
										CellsPanel.setWon(true);
										JOptionPane.showMessageDialog(cellsPanel, GAMEWON);
									}
								}
							} else if (SwingUtilities.isRightMouseButton(arg0)) {
								if (cell.isHidden()) {
									if (!cell.isFlagged()) {
										cell.flagCell();
									} else {
										cell.unFlagCell();
									}
								}
							}
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

			});
			cellsPanel.add(button);
		}
//		JScrollPane cellScrollPane = new JScrollPane(cellsPanel);
//		deminorPanel.add(cellScrollPane);
		deminorPanel.add(cellsPanel);
		return deminorPanel;
	}
	
	public static Rectangle getMinesPanelBounds() {
		return minesPanelBounds;
	}

	public static void setMinesPanelBounds(Rectangle minesPanelBounds) {
		DeminorPanel.minesPanelBounds = minesPanelBounds;
	}

	public static int getCellWidth() {
		return cellWidth;
	}

	public static void setCellWidth(int cellWidth) {
		DeminorPanel.cellWidth = cellWidth;
	}

	public static int getCellHeight() {
		return cellHeight;
	}

	public static void setCellHeight(int cellHeight) {
		DeminorPanel.cellHeight = cellHeight;
	}

	public static CellsPanel getCellsPanel() {
		return cellsPanel;
	}

	public static void setCellsPanel(CellsPanel cellsPanel) {
		DeminorPanel.cellsPanel = cellsPanel;
	}

}

