package dto;

import constants.Levels;
import views.options.panels.CustomLevelPanel;
import views.options.panels.OptionsLevelPanel;

public class DeminorGameProperties {

    private static int initialLevel = Levels.EASY;

    private int nBombs;

    private int level;

    private int cellsPerLine;

    private int cellsPerColumn;

    private long time;

    private int percent;

    public DeminorGameProperties() {
        this(initialLevel);
        setInitialLevel(initialLevel);
    }

    public DeminorGameProperties(int level) {
        this.level = level;
        calculateGamePropertiesFromLevel();
    }

    public DeminorGameProperties(int level, int nBombs, int cellsPerLine, int cellsPerColumn) {
        this.nBombs = nBombs;
        this.level = level;
        this.cellsPerLine = cellsPerLine;
        this.cellsPerColumn = cellsPerColumn;
    }

    public static int getInitialLevel() {
        return initialLevel;
    }

    private static void setInitialLevel(int initialLevel) {
        DeminorGameProperties.initialLevel = initialLevel;
    }

    void calculateGamePropertiesFromLevel() {
        this.cellsPerLine = 0;
        this.cellsPerColumn = 0;
        this.nBombs = 0;

        if (level >= 0 && level <= 2) {
            setCellsPerLine(OptionsLevelPanel.getDeminorCellsPerWidthByLevel()[this.level]);
            setCellsPerColumn(OptionsLevelPanel.getDeminorCellsPerHeightByLevel()[this.level]);
            setnBombs(OptionsLevelPanel.getBombsByLevel()[this.level]);
        } else {
            setCellsPerLine(Integer.parseInt(CustomLevelPanel.getCellsPerWidthTextField().getText()));
            setCellsPerColumn(Integer.parseInt(CustomLevelPanel.getCellsPerColumnTextField().getText()));
            setnBombs(Integer.parseInt(CustomLevelPanel.getnBombsTextField().getText()));
        }

    }

    public int getnBombs() {
        return nBombs;
    }

    private void setnBombs(int nBombs) {
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

    private void setCellsPerLine(int cellsPerLine) {
        this.cellsPerLine = cellsPerLine;
    }

    public int getCellsPerColumn() {
        return cellsPerColumn;
    }

    private void setCellsPerColumn(int cellsPerColumn) {
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
