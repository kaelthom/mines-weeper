package views.main.panels;

import java.awt.Rectangle;

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
import views.listeners.MouseReleasedOnlyListener;

public class DeminorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static CellsPanel cellsPanel;
	
	public DeminorPanel(Rectangle bounds) {
		this.setLayout(null);
		this.setBounds(bounds);
	}


	public static DeminorPanel createPanel(DeminorGameProperties properties) {

		int cellsPerLine  = properties.getCellsPerLine(); 
		int cellsPerColumn = properties.getCellsPerColumn();
		int nBombs = properties.getnBombs();

		ImageHandler.createImages();

		properties.setTime(System.currentTimeMillis());
		properties.setPercent(0);

		final Rectangle bounds = calculateDeminorBounds(cellsPerLine, cellsPerColumn);

		DeminorPanel deminorPanel = new DeminorPanel(bounds);

		cellsPanel = new CellsPanel(cellsPerLine,cellsPerColumn,nBombs);

		for (int iOcc = 0 ; iOcc < cellsPerLine* cellsPerColumn ; iOcc++){
			Cell button = cellsPanel.getCells().get(iOcc);
			button.setActionCommand("button" + iOcc);
			button.addMouseListener((MouseReleasedOnlyListener) mouseEvent -> {
				if (!CellsPanel.isLost() && !CellsPanel.isWon() && mouseEvent.getSource() instanceof Cell){
					Cell cell = (Cell) mouseEvent.getSource();
					if (SwingUtilities.isRightMouseButton(mouseEvent)) {
						new CellRightClickAction().execute(new RightClickActionInput(cell));
					} else if (SwingUtilities.isLeftMouseButton(mouseEvent) && !cell.isFlagged()) {
						LeftClickActionInput input = new LeftClickActionInput(cell, cellsPanel,	DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance());
						new CellLeftClickAction().execute(input);
					}
				}
			});
			cellsPanel.add(button);
		}
		//TODO		JScrollPane cellScrollPane = new JScrollPane(cellsPanel);
		//TODO		deminorPanel.add(cellScrollPane);
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
		
		return new Rectangle(deminorPanelX,deminorPanelY,deminorWidth, deminorHeight); 
	}
	
	public static CellsPanel getCellsPanel() {
		return cellsPanel;
	}

	public static void setCellsPanel(CellsPanel cellsPanel) {
		DeminorPanel.cellsPanel = cellsPanel;
	}

}

