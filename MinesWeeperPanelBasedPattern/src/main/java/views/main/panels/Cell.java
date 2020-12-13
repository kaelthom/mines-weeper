package views.main.panels;

import images.ImageHandler;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    private static final long serialVersionUID = 8266984880552648355L;
    private static double bombProbability;
    private int nBombsAround;
    private int xOcc;
    private int yOcc;
    private int index;
    private boolean mined;
    private boolean hidden;
    private boolean flagged;

    public Cell(Rectangle bounds, int xOcc, int yOcc, int index) {
        setIcon(ImageHandler.getHiddenIcon());
        setBounds(bounds);
        setMined();
        setHidden(true);
        setFlagged(false);
        setIndex(index);
        setxOcc(xOcc);
        setyOcc(yOcc);
        setnBombsAround(0);
    }

    static void setBombProbability(double bombProbability) {
        Cell.bombProbability = bombProbability;
    }

    public boolean isMined() {
        return mined;
    }

    private void setMined() {
        double rand = Math.random();
        mined = rand < bombProbability;
    }

    int getnBombsAround() {
        return nBombsAround;
    }

    private void setnBombsAround(int nBombsAround) {
        this.nBombsAround = nBombsAround;
    }

    void incrementNBombsaround() {
        nBombsAround++;
    }

    public int getxOcc() {
        return xOcc;
    }

    private void setxOcc(int xOcc) {
        this.xOcc = xOcc;
    }

    public int getyOcc() {
        return yOcc;
    }

    private void setyOcc(int yOcc) {
        this.yOcc = yOcc;
    }

    public int getIndex() {
        return index;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    public boolean isHidden() {
        return hidden;
    }

    private void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void showCell(CellsPanel panel) {
        panel.incrementDiscoveredCells();
        setHidden(false);
        setIcon(null);
        if (isMined()) {
            setIcon(ImageHandler.getBombIcon());
        } else {
            setBackground(Color.gray);
            if (getnBombsAround() != 0) setText(String.valueOf(getnBombsAround()));
        }
    }

    public void flagCell() {
        setIcon(ImageHandler.getFlagIcon());
        setFlagged(true);
    }

    public void unFlagCell() {
        setIcon(ImageHandler.getHiddenIcon());
        setFlagged(false);
    }

    public boolean isFlagged() {
        return flagged;
    }

    private void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
}
