package views.options.panels;

import dto.DeminorGameProperties;
import dto.DeminorGamePropertiesFactory;
import messages.Labels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomLevelPanel extends JPanel {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLevelPanel.class);

    private static final long serialVersionUID = -622076559152117047L;
    private static JTextField cellsPerColumnTextField;
    private static JTextField cellsPerWidthTextField;
    private static JTextField nBombsTextField;

    private static int cellsPerColumn;
    private static int cellsPerWidth;
    private static int nBombs;

    public CustomLevelPanel() {
        super();
    }

    public static JTextField getCellsPerColumnTextField() {
        return cellsPerColumnTextField;
    }

    public static void setCellsPerColumnTextField(JTextField cellsPerColumnTextField) {
        CustomLevelPanel.cellsPerColumnTextField = cellsPerColumnTextField;
    }

    public static JTextField getCellsPerWidthTextField() {
        return cellsPerWidthTextField;
    }

    public static void setCellsPerWidthTextField(JTextField cellsPerWidthTextField) {
        CustomLevelPanel.cellsPerWidthTextField = cellsPerWidthTextField;
    }

    public static JTextField getnBombsTextField() {
        return nBombsTextField;
    }

    public static void setnBombsTextField(JTextField nBombsTextField) {
        CustomLevelPanel.nBombsTextField = nBombsTextField;
    }

    public static int getCellsPerColumn() {
        return cellsPerColumn;
    }

    public static void setCellsPerColumn(int cellsPerColumn) {
        CustomLevelPanel.cellsPerColumn = cellsPerColumn;
    }

    public static int getCellsPerWidth() {
        return cellsPerWidth;
    }

    public static void setCellsPerWidth(int cellsPerWidth) {
        CustomLevelPanel.cellsPerWidth = cellsPerWidth;
    }

    public static int getnBombs() {
        return nBombs;
    }

    public static void setnBombs(int nBombs) {
        CustomLevelPanel.nBombs = nBombs;
    }

    public void createCustomPanel() {
        setLayout(new GridLayout(3, 2));

        DeminorGameProperties gameProperties = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance();
        setCellsPerColumn(gameProperties.getCellsPerColumn());
        setCellsPerWidth(gameProperties.getCellsPerLine());
        setnBombs(gameProperties.getnBombs());

        setCellsPerColumnTextField(new JTextField(Integer.toString(getCellsPerColumn())));
        cellsPerColumnTextField.setActionCommand("cellsPerColumn");
        cellsPerColumnTextField.setEditable(false);
        cellsPerColumnTextField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //Nothing to do
            }

            @Override
            public void keyReleased(KeyEvent e) {
                LOGGER.info("Custom cellsPerColumn : {}", Integer.valueOf(cellsPerColumnTextField.getText()));
                setCellsPerColumn(Integer.valueOf(cellsPerColumnTextField.getText()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Nothing to do
            }
        });
        add(new JLabel(Labels.OPTIONS_CELLS_PER_HEIGHT), 0);
        add(cellsPerColumnTextField, 1);

        setCellsPerWidthTextField(new JTextField(Integer.toString(getCellsPerWidth())));
        cellsPerWidthTextField.setActionCommand("cellsPerWidth");
        cellsPerWidthTextField.setEditable(false);
        cellsPerWidthTextField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //Nothing to do
            }

            @Override
            public void keyReleased(KeyEvent e) {
                setCellsPerWidth(Integer.valueOf(cellsPerWidthTextField.getText()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Nothing to do
            }
        });
        add(new JLabel(Labels.OPTIONS_CELLS_PER_WIDTH), 2);
        add(cellsPerWidthTextField, 3);

        setnBombsTextField(new JTextField(Integer.toString(getnBombs())));
        nBombsTextField.setActionCommand("nBombs");
        nBombsTextField.setEditable(false);
        nBombsTextField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //Nothing to do
            }

            @Override
            public void keyReleased(KeyEvent e) {
                LOGGER.info("Custom nBombs : {}", Integer.valueOf(nBombsTextField.getText()));
                setnBombs(Integer.valueOf(nBombsTextField.getText()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Nothing to do
            }
        });
        add(new JLabel(Labels.OPTIONS_NUMBER_OF_BOMBS), 4);
        add(nBombsTextField, 5);
    }

}
