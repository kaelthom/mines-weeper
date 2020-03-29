package actions.cellleftclick;

import java.util.Date;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actions.GenericAbstractInputObjectAction;
import datas.DataManager;
import datas.Highscore;
import dto.DeminorGameProperties;
import messages.Labels;
import views.main.panels.Cell;
import views.main.panels.CellsPanel;

public class CellLeftClickAction extends GenericAbstractInputObjectAction<LeftClickActionInput> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CellLeftClickAction.class);
	
	@Override
	public int execute(LeftClickActionInput input) {
		Cell cell = input.getCell();
		CellsPanel cellsPanel = input.getCellsPanel();
		DeminorGameProperties gameProperties = input.getGameProperties();
		long time = gameProperties.getTime();
		int percent;
		int cellsPerColumn = gameProperties.getCellsPerColumn();
		int cellsPerLine = gameProperties.getCellsPerLine();
		int nBombs = gameProperties.getnBombs();
		int level = gameProperties.getLevel();
		
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
			JOptionPane.showMessageDialog(cellsPanel, Labels.GAMELOST);
			percent = (cellsPanel.getDiscoveredCells()-CellsPanel.getnBombs())*100/
					  (cellsPerLine*cellsPerColumn-nBombs);
			DataManager.insertHighscore(new Highscore(0,"", new Date().toLocaleString(), time, percent), level);
		} else {
			cellsPanel.showCurrentAndNeighbourCells(cell.getxOcc(), cell.getyOcc(),cell);
			int nCellsToWin = cellsPerLine*cellsPerColumn-nBombs;
			if (cellsPanel.getDiscoveredCells() == nCellsToWin) {
				CellsPanel.setWon(true);
				JOptionPane.showMessageDialog(cellsPanel, Labels.GAMEWON);
				time=System.currentTimeMillis()-time;time=time/1000;
				percent = 100;
				LOGGER.info("time : {}",time);
				LOGGER.info("percent : {}",percent);
				DataManager.insertHighscore(new Highscore(0,"", new Date().toLocaleString(), time, percent), level);
			}
		}
		return returnCode;
	}

}
