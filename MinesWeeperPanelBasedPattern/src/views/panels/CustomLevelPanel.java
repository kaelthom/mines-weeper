package views.panels;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.DeminorGameProperties;
import dto.DeminorGamePropertiesFactory;
import messages.Labels;

public class CustomLevelPanel extends JPanel{

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
		add(new JLabel(Labels.OPTIONS_CELLS_PER_HEIGHT),0);
		add(cellsPerColumnTextField,1);

		setCellsPerWidthTextField(new JTextField(Integer.toString(getCellsPerWidth())));
		cellsPerWidthTextField.setActionCommand("cellsPerWidth");
		cellsPerWidthTextField.setEditable(false);
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
		add(new JLabel(Labels.OPTIONS_CELLS_PER_WIDTH),2);
		add(cellsPerWidthTextField,3);

		setnBombsTextField(new JTextField(Integer.toString(getnBombs())));
		nBombsTextField.setActionCommand("nBombs");
		nBombsTextField.setEditable(false);
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
		add(new JLabel(Labels.OPTIONS_NUMBER_OF_BOMBS),4);
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
