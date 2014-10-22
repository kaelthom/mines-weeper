package images;

import java.awt.Image;

import javax.swing.ImageIcon;

import views.panels.DeminorPanel;

public class ImageHandler {

	private static ImageIcon bombIcon;
	private static ImageIcon flagIcon;
	private static ImageIcon hiddenIcon;
	private static String path = "c:/Users/thomere/git/MinesWeeper/MinesWeeperPanelBasedPattern/images/";

	public static void createImages() {
		setBombIcon(new ImageIcon(path + "demineur.png"));
		setFlagIcon(new ImageIcon(path + "redflag.jpg"));
		setHiddenIcon(new ImageIcon(path + "hidden2.png"));
		
	}

	public static ImageIcon getBombIcon() {
		return bombIcon;
	}

	public static void setBombIcon(ImageIcon bombIcon) {
		Image img = bombIcon.getImage().getScaledInstance(DeminorPanel.getCellWidth(), DeminorPanel.getCellHeight(), Image.SCALE_FAST);
		ImageHandler.bombIcon = new ImageIcon(img);
	}

	public static ImageIcon getFlagIcon() {
		return flagIcon;
	}

	public static void setFlagIcon(ImageIcon flagIcon) {
		Image img = flagIcon.getImage().getScaledInstance(DeminorPanel.getCellWidth(), DeminorPanel.getCellHeight(), Image.SCALE_FAST);
		ImageHandler.flagIcon = new ImageIcon(img);
	}

	public static ImageIcon getHiddenIcon() {
		return hiddenIcon;
	}

	public static void setHiddenIcon(ImageIcon hiddenIcon) {
		Image img = hiddenIcon.getImage().getScaledInstance(DeminorPanel.getCellWidth(), DeminorPanel.getCellHeight(), Image.SCALE_FAST);
		ImageHandler.hiddenIcon = new ImageIcon(img);
	}

}
