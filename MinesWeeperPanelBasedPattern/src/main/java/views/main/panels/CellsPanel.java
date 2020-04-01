package views.main.panels;

import dto.DeminorPanelProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellsPanel extends JPanel {

    private static final long serialVersionUID = -5474923082252270189L;

    private static int cellsPerLine;
    private static int cellsPerColumn;
    private static int nBombs;
    private static boolean lost;
    private static boolean won;
    private List<Cell> cells = new ArrayList<>();
    private List<Integer> minedCells = new ArrayList<>();
    private int discoveredCells;

    public CellsPanel(int cellsPerLine, int cellsPerColumn, int nBombs) {
        super();

        int cellWidth = DeminorPanelProperties.getCellWidth();
        int cellHeight = DeminorPanelProperties.getCellHeight();

        Rectangle bounds = calculateMinesPanelBounds(cellsPerLine, cellsPerColumn);

        setLayout(null);
        setBounds(bounds);
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

        int nRemainingBombs = nBombs;
        int nRemainingCells = cellsPerLine * cellsPerColumn;
        double bombprobability = nRemainingBombs * 1d / nRemainingCells;
        Cell.setBombProbability(bombprobability);

        for (int jOcc = 0; jOcc < cellsPerColumn; jOcc++) {
            for (int iOcc = 0; iOcc < cellsPerLine; iOcc++) {
                int cellX = buttonsPanelXMargin + iOcc * (cellWidth + xSpaceBtwButtons);
                int cellY = buttonsPanelYMargin + jOcc * (cellHeight + ySpaceBtwButtons);
                Rectangle cellBounds = new Rectangle(cellX, cellY, cellWidth, cellHeight);
                int index = getCellIndex(iOcc, jOcc);
                Cell cell = new Cell(cellBounds, iOcc, jOcc, index);
                cell.setBounds(cellBounds);
                Insets m = new Insets(0, 0, 0, 0);
                cell.setMargin(m);
                getCells().add(cell);
                if (cell.isMined()) {
                    minedCells.add(index);
                    nRemainingBombs--;
                }
                nRemainingCells--;
                if (nRemainingCells != 0) bombprobability = nRemainingBombs * 1d / nRemainingCells;
                Cell.setBombProbability(bombprobability);
            }
        }
        for (int jOcc = 0; jOcc < cellsPerColumn; jOcc++) {
            for (int iOcc = 0; iOcc < cellsPerLine; iOcc++) {
                Cell cell = getCells().get(getCellIndex(iOcc, jOcc));
                if (!cell.isMined()) {
                    countBombsAround(iOcc, jOcc, cell);
                }
            }
        }
    }

    private static Rectangle calculateMinesPanelBounds(int cellsPerLine, int cellsPerHeight) {
        int minesPanelX = DeminorPanelProperties.getMinesPanelX();
        int minesPanelY = DeminorPanelProperties.getMinesPanelY();
        int minesPanelWidth = cellsPerLine * DeminorPanelProperties.getCellWidth();
        int minesPanelHeight = cellsPerHeight * DeminorPanelProperties.getCellHeight();

        return new Rectangle(minesPanelX, minesPanelY, minesPanelWidth, minesPanelHeight);
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

    public static boolean isLost() {
        return lost;
    }

    public static void setLost(boolean lost) {
        CellsPanel.lost = lost;
    }

    public static boolean isWon() {
        return won;
    }

    public static void setWon(boolean won) {
        CellsPanel.won = won;
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
        return new Point(index % cellsPerLine, index / cellsPerLine);

    }

    private void countBombsAround(int currentColumnIndex, int currentLineIndex, Cell cell) {

        int leftColumnIndex = currentColumnIndex - 1;
        int rightColumnIndex = currentColumnIndex + 1;
        int aboveLineIndex = currentLineIndex - 1;
        int belowLineIndex = currentLineIndex + 1;
        int lastColumnIndex = cellsPerLine - 1;
        int lastLineIndex = cellsPerColumn - 1;
        int firstColumnIndex = 0;
        int firstLineIndex = 0;

        checkNeighborCellAndIncrementIfMined(cell, leftColumnIndex, currentLineIndex, firstColumnIndex, firstLineIndex, lastColumnIndex, lastLineIndex);
        checkNeighborCellAndIncrementIfMined(cell, rightColumnIndex, currentLineIndex, firstColumnIndex, firstLineIndex, lastColumnIndex, lastLineIndex);
        checkNeighborCellAndIncrementIfMined(cell, leftColumnIndex, aboveLineIndex, firstColumnIndex, firstLineIndex, lastColumnIndex, lastLineIndex);
        checkNeighborCellAndIncrementIfMined(cell, currentColumnIndex, aboveLineIndex, firstColumnIndex, firstLineIndex, lastColumnIndex, lastLineIndex);
        checkNeighborCellAndIncrementIfMined(cell, rightColumnIndex, aboveLineIndex, firstColumnIndex, firstLineIndex, lastColumnIndex, lastLineIndex);
        checkNeighborCellAndIncrementIfMined(cell, leftColumnIndex, belowLineIndex, firstColumnIndex, firstLineIndex, lastColumnIndex, lastLineIndex);
        checkNeighborCellAndIncrementIfMined(cell, currentColumnIndex, belowLineIndex, firstColumnIndex, firstLineIndex, lastColumnIndex, lastLineIndex);
        checkNeighborCellAndIncrementIfMined(cell, rightColumnIndex, belowLineIndex, firstColumnIndex, firstLineIndex, lastColumnIndex, lastLineIndex);

    }

    private void checkNeighborCellAndIncrementIfMined(Cell cell, int columnIndex, int lineIndex, int firstColumnIndex, int firstLineIndex, int lastColumnIndex, int lastLineIndex) {
        if (columnIndex >= firstColumnIndex &&
                columnIndex <= lastColumnIndex &&
                lineIndex >= firstLineIndex &&
                lineIndex <= lastLineIndex &&
                getCells().get(getCellIndex(columnIndex, lineIndex)).isMined()) cell.incrementNBombsaround();
    }

    public void showNeighbourBlankCells(int currentColumnIndex, int currentLineIndex) {
        int leftColumnIndex = currentColumnIndex - 1;
        int rightColumnIndex = currentColumnIndex + 1;
        int aboveLineIndex = currentLineIndex - 1;
        int belowLineIndex = currentLineIndex + 1;

        checkAndShowCurrentAndNeighbourCells(leftColumnIndex, currentLineIndex);
        checkAndShowCurrentAndNeighbourCells(rightColumnIndex, currentLineIndex);
        checkAndShowCurrentAndNeighbourCells(leftColumnIndex, aboveLineIndex);
        checkAndShowCurrentAndNeighbourCells(currentColumnIndex, aboveLineIndex);
        checkAndShowCurrentAndNeighbourCells(rightColumnIndex, aboveLineIndex);
        checkAndShowCurrentAndNeighbourCells(leftColumnIndex, belowLineIndex);
        checkAndShowCurrentAndNeighbourCells(currentColumnIndex, belowLineIndex);
        checkAndShowCurrentAndNeighbourCells(rightColumnIndex, belowLineIndex);
    }

    private void checkAndShowCurrentAndNeighbourCells(int columnIndex, int lineIndex) {
        int lastColumnIndex = cellsPerLine - 1;
        int lastLineIndex = cellsPerColumn - 1;
        int firstColumnIndex = 0;
        int firstLineIndex = 0;
        if (columnIndex >= firstColumnIndex &&
                columnIndex <= lastColumnIndex &&
                lineIndex >= firstLineIndex &&
                lineIndex <= lastLineIndex) {
            Cell currentCell = getCells().get(getCellIndex(columnIndex, lineIndex));
            showCurrentAndNeighbourCellsIfHidden(columnIndex, lineIndex, currentCell);
        }
    }

    public void showCurrentAndNeighbourCellsIfHidden(int iOcc, int jOcc, Cell currentCell) {
        if (currentCell.isHidden()) {
            currentCell.showCell(this);
            if (currentCell.getnBombsaround() == 0 && !currentCell.isMined()) {
                showNeighbourBlankCells(iOcc, jOcc);
            }
        }
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

    public void incrementDiscoveredCells() {
        discoveredCells++;
    }
}
