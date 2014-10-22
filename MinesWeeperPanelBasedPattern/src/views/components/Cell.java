package views.components;

import images.ImageHandler;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JButton;

import views.panels.CellsPanel;

public class Cell extends JButton {

	private static final long serialVersionUID = 8266984880552648355L;
	private static double bombProbability;
	private int nBombsaround;
	private int xOcc;
	private int yOcc;
	private int index;
	private boolean mined;
	private boolean hidden;
	private boolean blank;
	private boolean flagged;
	
	public Cell(Rectangle bounds, int xOcc, int yOcc, int index){
		setIcon(ImageHandler.getHiddenIcon());
		setBounds(bounds);
		setMined();
		setHidden(true);
		setFlagged(false);
		setBlank(false);
		setIndex(index);
		setxOcc(xOcc);
		setyOcc(yOcc);
		setnBombsaround(0);
	}

	public boolean isMined() {
		return mined;
	}

	public void setMined() {
		double rand = Math.random();
		mined = false;
		if (rand < bombProbability) {
			mined = true;
		}
	}

	public static double getBombProbability() {
		return bombProbability;
	}

	public static void setBombProbability(double bombProbability) {
		Cell.bombProbability = bombProbability;
	}

	public int getnBombsaround() {
		return nBombsaround;
	}

	public void setnBombsaround(int nBombsaround) {
		this.nBombsaround = nBombsaround;
	}

	public void incrementNBombsaround() {
		nBombsaround++;
	}

	public int getxOcc() {
		return xOcc;
	}

	public void setxOcc(int xOcc) {
		this.xOcc = xOcc;
	}

	public int getyOcc() {
		return yOcc;
	}

	public void setyOcc(int yOcc) {
		this.yOcc = yOcc;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public void showCell(CellsPanel panel){
		panel.incrementDiscoveredCells();
		setHidden(false);
		setIcon(null);
		if (isMined()) {
			setIcon(ImageHandler.getBombIcon());
		} else {
			setBackground(Color.gray);
			if (getnBombsaround() != 0) setText(String.valueOf(getnBombsaround()));
		}
	}

	public boolean isBlank() {
		return blank;
	}

	public void setBlank(boolean blank) {
		this.blank = blank;
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

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
}
