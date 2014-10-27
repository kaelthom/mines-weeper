package views.panels;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import languages.LanguageFactory;

public class CustomLevelPanel extends JPanel{

    private static final String OPTIONS_CELLS_PER_WIDTH = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("options.cellsperwidth");
    private static final String OPTIONS_CELLS_PER_HEIGHT = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("options.cellsperheight");
    private static final String OPTIONS_NUMBER_OF_BOMBS = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("options.numberofbombs");

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

	public void createCustomPanel() {
		setLayout(new GridLayout(3,2));
		
		setCellsPerColumn(0);
		setCellsPerWidth(0);
		setnBombs(0);

		setCellsPerColumnTextField(new JTextField("0"));
		cellsPerColumnTextField.setActionCommand("cellsPerColumn");
		cellsPerColumnTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("Custom cellsPerColumn : " + Integer.valueOf(cellsPerColumnTextField.getText()));
				setCellsPerColumn(Integer.valueOf(cellsPerColumnTextField.getText()));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		add(new JLabel(OPTIONS_CELLS_PER_HEIGHT),0);
		add(cellsPerColumnTextField,1);

		setCellsPerWidthTextField(new JTextField("0"));
		cellsPerWidthTextField.setActionCommand("cellsPerWidth");
		cellsPerWidthTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				setCellsPerWidth(Integer.valueOf(cellsPerWidthTextField.getText()));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		add(new JLabel(OPTIONS_CELLS_PER_WIDTH),2);
		add(cellsPerWidthTextField,3);

		setnBombsTextField(new JTextField("0"));
		nBombsTextField.setActionCommand("nBombs");
		nBombsTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("Custom nBombs : " + Integer.valueOf(nBombsTextField.getText()));
				setnBombs(Integer.valueOf(nBombsTextField.getText()));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		add(new JLabel(OPTIONS_NUMBER_OF_BOMBS),4);
		add(nBombsTextField,5);
	}

	public static JTextField getCellsPerColumnTextField() {
		return cellsPerColumnTextField;
	}

	public void setCellsPerColumnTextField(JTextField cellsPerColumnTextField) {
		CustomLevelPanel.cellsPerColumnTextField = cellsPerColumnTextField;
	}

	public static JTextField getCellsPerWidthTextField() {
		return cellsPerWidthTextField;
	}

	public void setCellsPerWidthTextField(JTextField cellsPerWidthTextField) {
		CustomLevelPanel.cellsPerWidthTextField = cellsPerWidthTextField;
	}

	public static JTextField getnBombsTextField() {
		return nBombsTextField;
	}

	public void setnBombsTextField(JTextField nBombsTextField) {
		CustomLevelPanel.nBombsTextField = nBombsTextField;
	}

	public static int getCellsPerColumn() {
		return cellsPerColumn;
	}

	public void setCellsPerColumn(int cellsPerColumn) {
		CustomLevelPanel.cellsPerColumn = cellsPerColumn;
	}

	public static int getCellsPerWidth() {
		return cellsPerWidth;
	}

	public void setCellsPerWidth(int cellsPerWidth) {
		CustomLevelPanel.cellsPerWidth = cellsPerWidth;
	}

	public static int getnBombs() {
		return nBombs;
	}

	public void setnBombs(int nBombs) {
		CustomLevelPanel.nBombs = nBombs;
	}

}
