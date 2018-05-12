package views.highscore.panels;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import datas.DataManager;
import datas.Highscore;
import messages.Labels;

public class HighscorePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private HighscorePanel(int level) {
        List<Highscore> highscores = DataManager.getHighscores(level);
        if (highscores!=null) {
            JTable table = null;
            table = createTable(highscores);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
        }
		
	}

	private JTable createTable(List<Highscore> highscores) {
		Object data[][] = new Object[10][4];
		for(int i = 0; i < highscores.size(); i++)
		{
		    data[i][0] = ((Highscore)highscores.get(i)).getName();
		    data[i][1] = ((Highscore)highscores.get(i)).getDate();
		    data[i][2] = Long.toString(((Highscore)highscores.get(i)).getScore());
		    data[i][3] = Integer.toString(((Highscore)highscores.get(i)).getPercent());
		}
		if (highscores.size()<10) {
			for(int i = highscores.size(); i < 10; i++)
			{
			    data[i][0] = "";
			    data[i][1] = "";
			    data[i][2] = "";
			    data[i][3] = "";
			}
		}
		
		String columnNames[] = {
		    Labels.NAME_COLUMN_NAME, 
		    Labels.DATE_COLUMN_NAME, 
		    Labels.SCORE_COLUMN_NAME, 
		    Labels.PERCENT_COLUMN_NAME
		};

		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 11*15));
		table.setFillsViewportHeight(true);
		return table;
	}
	
	public static HighscorePanel createPanel(int level) {
		return new HighscorePanel(level);
	}

}
