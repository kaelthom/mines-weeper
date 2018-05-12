package views.main.panels;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import actions.cellleftclick.CellLeftClickAction;
import actions.cellleftclick.LeftClickActionInput;
import actions.cellrightclick.CellRightClickAction;
import actions.cellrightclick.RightClickActionInput;
import dto.DeminorGameProperties;
import dto.DeminorGamePropertiesFactory;
import dto.DeminorPanelProperties;
import images.ImageHandler;
import views.main.Cell;

public class DeminorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static int level;

	private static DeminorGameProperties gameProperties;
	
	private static int cellWidth = 23;
	private static int cellHeight = 23;

	private static Rectangle bounds;
	
	private static CellsPanel cellsPanel;
	
	public DeminorPanel(Rectangle bounds) {
		this.setLayout(null);
		this.setBounds(bounds);
	}


	public static DeminorPanel createPanel(DeminorGameProperties properties) {

		int iLevel = properties.getLevel();
		int cellsPerLine  = properties.getCellsPerLine(); 
		int cellsPerColumn = properties.getCellsPerColumn();
		int nBombs = properties.getnBombs();

		ImageHandler.createImages();
		DeminorPanel.setLevel(iLevel);
		DeminorPanel.setGameProperties(properties);

		properties.setTime(System.currentTimeMillis());
		properties.setPercent(0);

		bounds = calculateDeminorBounds(cellsPerLine, cellsPerColumn);

		DeminorPanel deminorPanel = new DeminorPanel(bounds);

		cellsPanel = new CellsPanel(cellsPerLine,cellsPerColumn,nBombs);

		for (int iOcc = 0 ; iOcc < cellsPerLine* cellsPerColumn ; iOcc++){
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
							if (SwingUtilities.isRightMouseButton(arg0)) {
								new CellRightClickAction().execute(new RightClickActionInput(cell));
							} else if (SwingUtilities.isLeftMouseButton(arg0) && !cell.isFlagged()) {
								LeftClickActionInput input = new LeftClickActionInput(cell,cellsPanel,DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance());
								new CellLeftClickAction().execute(input);
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
	
	private static Rectangle calculateDeminorBounds(int cellsPerLine, int cellsPerHeight) {
		int cellWidth = DeminorPanelProperties.getCellWidth();
		int cellHeight = DeminorPanelProperties.getCellHeight();
		int minesPanelX = DeminorPanelProperties.getMinesPanelX();
		int minesPanelY = DeminorPanelProperties.getMinesPanelY();
		int deminorPanelX = DeminorPanelProperties.getDeminorX();
		int deminorPanelY = DeminorPanelProperties.getDeminorY();
		
		int minesPanelWidth = cellsPerLine*cellWidth;
		int minesPanelHeight = cellsPerHeight*cellHeight;

		int deminorWidth = minesPanelWidth + minesPanelX*2;
		int deminorHeight = minesPanelHeight + minesPanelY*2;
		Rectangle bounds = new Rectangle(deminorPanelX,deminorPanelY,deminorWidth, deminorHeight);
		
		return bounds; 
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

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		DeminorPanel.level = level;
	}

	public static DeminorGameProperties getGameProperties() {
		return gameProperties;
	}

	public static void setGameProperties(DeminorGameProperties gameProperties) {
		DeminorPanel.gameProperties = gameProperties;
	}

}

