// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreDAO.java

package datas;

import connections.ConnexionFactory;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// Referenced classes of package datas:
//            Highscore

public class HighscoreDAO
{
	private static final String NAME_COLUMN_NAME = ResourceBundle.getBundle("datas").getString("highscore.name");
	private static final String DATE_COLUMN_NAME = ResourceBundle.getBundle("datas").getString("highscore.date");
	private static final String SCORE_COLUMN_NAME = ResourceBundle.getBundle("datas").getString("highscore.score");
    public HighscoreDAO()
    {
    }

    public List<Highscore> getHighscoreList()
    {
        List<Highscore> highscores = new ArrayList<>();
        Connection conn = null;
        try
        {
            conn = ConnexionFactory.getConnectionInstance();
            String query = "SELECT * FROM HIGHSCORE ORDER BY SCORE";
            Statement statement = conn.createStatement();
            String name;
            String date;
            int score;
            for(ResultSet rs = statement.executeQuery(query); rs.next(); highscores.add(new Highscore(name, date, score)))
            {
                name = rs.getString(NAME_COLUMN_NAME);
                date = rs.getString(DATE_COLUMN_NAME);
                score = rs.getInt(SCORE_COLUMN_NAME);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        } finally {
            if (conn != null) ConnexionFactory.close();
        }
        return highscores;
    }

    public boolean addHighscore(Highscore highscore)
    {
        Connection conn = null;
        try {
			conn = ConnexionFactory.getConnectionInstance();
	        String query = (new StringBuilder("INSERT INTO HIGHSCORE VALUES ('"))
	        		                  .append(highscore.getName()).append("','")
	        		                  .append(highscore.getDate()).append("',")
	        		                  .append(highscore.getScore())
	        		                  .append(")").toString();
	        System.out.println(query);
	        Statement statement = conn.createStatement();
	        int rs = statement.executeUpdate(query);
	        return true;
		} catch (PropertyVetoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (conn != null) ConnexionFactory.close();
		}
        return false;
    }
}
