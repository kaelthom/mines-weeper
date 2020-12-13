package images;

import dto.DeminorPanelProperties;

import javax.swing.*;
import java.awt.*;

public class ImageHandler {

    private static final String IMAGES_FILES_PATH = "src/main/resources/images/";
    private static ImageIcon bombIcon;
    private static ImageIcon flagIcon;
    private static ImageIcon hiddenIcon;

    private ImageHandler() {
        //prevent from being called from other classes
    }

    public static void createImages() {
        setBombIcon(new ImageIcon(IMAGES_FILES_PATH + "demineur.png"));
        setFlagIcon(new ImageIcon(IMAGES_FILES_PATH + "redflag.jpg"));
        setHiddenIcon(new ImageIcon(IMAGES_FILES_PATH + "hidden2.png"));

    }

    public static ImageIcon getBombIcon() {
        return bombIcon;
    }

    private static void setBombIcon(ImageIcon bombIcon) {
        Image img = bombIcon.getImage().getScaledInstance(DeminorPanelProperties.getCellWidth(), DeminorPanelProperties.getCellHeight(), Image.SCALE_FAST);
        ImageHandler.bombIcon = new ImageIcon(img);
    }

    public static ImageIcon getFlagIcon() {
        return flagIcon;
    }

    private static void setFlagIcon(ImageIcon flagIcon) {
        Image img = flagIcon.getImage().getScaledInstance(DeminorPanelProperties.getCellWidth(), DeminorPanelProperties.getCellHeight(), Image.SCALE_FAST);
        ImageHandler.flagIcon = new ImageIcon(img);
    }

    public static ImageIcon getHiddenIcon() {
        return hiddenIcon;
    }

    private static void setHiddenIcon(ImageIcon hiddenIcon) {
        Image img = hiddenIcon.getImage().getScaledInstance(DeminorPanelProperties.getCellWidth(), DeminorPanelProperties.getCellHeight(), Image.SCALE_FAST);
        ImageHandler.hiddenIcon = new ImageIcon(img);
    }

}
