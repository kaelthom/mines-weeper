package actions.cellleftclick;

import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import actions.GenericAbstractInputObjectAction;
import datas.DataManager;
import datas.Highscore;
import dto.DeminorGameProperties;
import languages.LanguageFactory;
import views.components.Cell;
import views.panels.CellsPanel;
import views.panels.DeminorPanel;

public class CellLeftClickAction extends GenericAbstractInputObjectAction<LeftClickActionInput> {

	private static final String GAMELOST = ResourceBundle.getBundle("labels",LanguageFactory.getInstance().getLocale()).getString("main.gamelost");
	private static final String GAMEWON = ResourceBundle.getBundle("labels",LanguageFactory.getInstance().getLocale()).getString("main.gamewon");
	@Override
	public int execute(LeftClickActionInput input) {
		Cell cell = input.getCell();
		CellsPanel cellsPanel = input.getCellsPanel();
		DeminorGameProperties gameProperties = input.getGameProperties();
		long time = gameProperties.getTime();
		int percent = gameProperties.getPercent();
//		int cellsPerColumn = gameProperties.getCellsPerColumn();
//		int cellsPerLine = gameProperties.getCellsPerLine();
		int cellsPerColumn = CellsPanel.getCellsPerColumn();
		int cellsPerLine = CellsPanel.getCellsPerLine();
		int nBombs = gameProperties.getnBombs();
//		int level = gameProperties.getCellsPerLine();
		int level = DeminorPanel.getLevel();
		
		int returnCode = 0;
		int index = cell.getIndex();
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
			time = System.currentTimeMillis() - time;time=time/1000;
			JOptionPane.showMessageDialog(cellsPanel, GAMELOST);
			percent = (cellsPanel.getDiscoveredCells()-CellsPanel.getnBombs())*100/
					  (cellsPerLine*cellsPerColumn-nBombs);
			DataManager.insertHighscore(new Highscore("", new Date().toLocaleString(), time, percent), level);
		} else {
			cellsPanel.showCurrentAndNeighbourCells(cell.getxOcc(), cell.getyOcc(),cell);
			int nCellsToWin = CellsPanel.getCellsPerColumn()*CellsPanel.getCellsPerColumn()-CellsPanel.getnBombs();
			if (cellsPanel.getDiscoveredCells() == nCellsToWin) {
				CellsPanel.setWon(true);
				JOptionPane.showMessageDialog(cellsPanel, GAMEWON);
				time=System.currentTimeMillis()-time;time=time/1000;
				percent = 100;
				System.out.println("time : " + time);
				System.out.println("percent : " + percent);
				DataManager.insertHighscore(new Highscore("", new Date().toLocaleString(), time, percent), level);
			}
		}
		return returnCode;
	}

}
