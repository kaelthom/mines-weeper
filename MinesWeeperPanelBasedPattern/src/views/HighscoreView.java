// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreView.java

package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

import languages.LanguageFactory;
import datas.Highscore;

public class HighscoreView extends JPanel
{

    public HighscoreView(List<Highscore> highscores)
    {
        super(new GridLayout(1, 0));
        DEBUG = false;
        Object data[][] = new Object[highscores.size()][3];
        for(int i = 0; i < highscores.size(); i++)
        {
            data[i][0] = ((Highscore)highscores.get(i)).getName();
            data[i][1] = ((Highscore)highscores.get(i)).getDate();
            data[i][2] = Long.valueOf(((Highscore)highscores.get(i)).getScore());
        }

        String columnNames[] = {
            NAME_COLUMN_NAME, DATE_COLUMN_NAME, SCORE_COLUMN_NAME
        };
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        if(DEBUG)
            table.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e)
                {
                    printDebugData(table);
                }

            });
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void printDebugData(JTable table)
    {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        TableModel model = table.getModel();
        System.out.println("Value of data: ");
        for(int i = 0; i < numRows; i++)
        {
            System.out.print((new StringBuilder("    row ")).append(i).append(":").toString());
            for(int j = 0; j < numCols; j++)
                System.out.print((new StringBuilder("  ")).append(model.getValueAt(i, j)).toString());

            System.out.println();
        }

        System.out.println("--------------------------");
    }

    private static void createAndShowGUI(JPanel panel)
    {
        JFrame frame = new JFrame(HIGHSCORES_TITLE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void launchFrame(final JPanel panel)
    {
        SwingUtilities.invokeLater(
        	new Runnable() {

        		public void run()
        		{
        			UIManager.put("swing.boldMetal", Boolean.FALSE);
        			HighscoreView.createAndShowGUI(panel);
        		}

        	});
    }

    private static final String HIGHSCORES_TITLE = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("highscores.title");
    public static final String NAME_COLUMN_NAME = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.name");
    public static final String DATE_COLUMN_NAME = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.date");
    public static final String SCORE_COLUMN_NAME = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.score");
    private static final long serialVersionUID = 1L;
    private boolean DEBUG;



}
