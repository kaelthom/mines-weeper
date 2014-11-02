package views.panels;

import java.awt.Dimension;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import languages.LanguageFactory;
import datas.DataManager;
import datas.Highscore;

public class HighscorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
    public static final String NAME_COLUMN_NAME = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.name");
    public static final String DATE_COLUMN_NAME = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.date");
    public static final String SCORE_COLUMN_NAME = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.score");

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
		Object data[][] = new Object[10][3];
		for(int i = 0; i < highscores.size(); i++)
		{
		    data[i][0] = ((Highscore)highscores.get(i)).getName();
		    data[i][1] = ((Highscore)highscores.get(i)).getDate();
		    data[i][2] = Long.toString(((Highscore)highscores.get(i)).getScore());
		}
		if (highscores.size()<10) {
			for(int i = highscores.size(); i < 10; i++)
			{
			    data[i][0] = "";
			    data[i][1] = "";
			    data[i][2] = "";
			}
		}
		

		String columnNames[] = {
		    NAME_COLUMN_NAME, DATE_COLUMN_NAME, SCORE_COLUMN_NAME
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
