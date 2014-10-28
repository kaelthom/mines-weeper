// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreDAO.java

package datas;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import connections.ConnexionFactory;


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

	public void addHighscore(Highscore highscore) {
		List<Highscore> highscores = DataManager.getHighscores();
		System.out.println("size table : " + highscores.size());
		if (highscores.size()<10) {
	    	addHighscore(highscore,null);
		} else {
			Highscore maxHighscore = Collections.max(highscores);
			System.out.println("maxHighscore : " + maxHighscore.getScore());
			System.out.println("highscore : " + highscore.getScore());
			if (maxHighscore.getScore()>highscore.getScore()) {
		    	addHighscore(highscore,maxHighscore);
			}
		}
	}

	public boolean addHighscore(Highscore highscore, Highscore maxHighscore)
    {
        Connection conn = null;
        try {
			conn = ConnexionFactory.getConnectionInstance();
			if (maxHighscore!=null) deleteHighscore(maxHighscore, conn);
	        insertHighscore(highscore, conn);
	        return true;
		} catch (SQLException e) {
			try {
				createTable(conn);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			addHighscore(highscore, null);
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} finally {
	        if (conn != null) ConnexionFactory.close();
		}
        return false;
    }

	private void insertHighscore(Highscore highscore, Connection conn)
			throws SQLException {
		String query = (new StringBuilder("INSERT INTO HIGHSCORE VALUES ('"))
                .append(highscore.getName()).append("','")
                .append(highscore.getDate()).append("',")
                .append(highscore.getScore())
                .append(")").toString();;
        		System.out.println(query);
		Statement statement = conn.createStatement();
		int rs = statement.executeUpdate(query);
	}

	private void createTable(Connection conn) throws SQLException {
		String query = new StringBuilder("CREATE TABLE HIGHSCORE (NAME varchar(255),DATE varchar(255),SCORE int )").toString();
		System.out.println(query);
		Statement statement = conn.createStatement();
		int rs = statement.executeUpdate(query);
	}

	public void deleteHighscore (Highscore highscore, Connection conn) throws SQLException {
			String query = new StringBuilder("DELETE FROM HIGHSCORE WHERE SCORE = ? AND ROWNUM = 1").toString();
			System.out.println(query);
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setLong(1, highscore.getScore());
	        int rs = statement.executeUpdate();
	}
}
