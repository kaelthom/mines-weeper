// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreDAO.java

package daos;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connections.ConnexionFactory;
import datas.DataManager;
import datas.Highscore;


// Referenced classes of package datas:
//            Highscore

public class HighscoreDBDAO
{
	private static final Logger LOGGER = LoggerFactory.getLogger(HighscoreDBDAO.class);
	private static final ResourceBundle datasBundle = ResourceBundle.getBundle("datas");
	private static final String ID_COLUMN_NAME = datasBundle.getString("highscore.id");
	private static final String NAME_COLUMN_NAME = datasBundle.getString("highscore.name");
	private static final String DATE_COLUMN_NAME = datasBundle.getString("highscore.date");
	private static final String SCORE_COLUMN_NAME = datasBundle.getString("highscore.score");
	private static final String PERCENT_COLUMN_NAME = datasBundle.getString("highscore.percent");

	public List<Highscore> getHighscoreList(int level) {
        String levelS = Integer.toString(level);
    	List<Highscore> highscores = new ArrayList<>();
        Connection conn = null;
        try
        {
            conn = ConnexionFactory.getConnectionInstance();
            String query = "SELECT * FROM HIGHSCORE_" + levelS;
            Statement statement = conn.createStatement();
            String name;
            String date;
            long score;
            long id;
            int percent;
            for(ResultSet rs = statement.executeQuery(query); rs.next();)
            {
                name = rs.getString(NAME_COLUMN_NAME);
                date = rs.getString(DATE_COLUMN_NAME);
                score = rs.getLong(SCORE_COLUMN_NAME);
                percent = rs.getInt(PERCENT_COLUMN_NAME);
                id = rs.getInt(ID_COLUMN_NAME);
                
                highscores.add(new Highscore(id, name, date, score, percent));
            }
            Collections.sort(highscores);
        }
        catch(Exception e)
        {
			try {
				createTable(conn,level);
			} catch (SQLException e1) {
				LOGGER.error("Error while creating highscore table : ", e1);
			}
			LOGGER.error("Error while getting highscore list", e);
			return new ArrayList<>();
        } finally {
            if (conn != null) ConnexionFactory.close();
        }
        return highscores;
    }

	public void addHighscore(Highscore highscore, int level) {
		List<Highscore> highscores = DataManager.getHighscores(level);
		LOGGER.info("size table : {}", highscores.size());
		if (highscores.size()<10) {
	    	addHighscore(highscore, level, null);
		} else {
			Highscore maxHighscore = Collections.max(highscores);
			LOGGER.info("maxscore : {}",maxHighscore.getScore());
			LOGGER.info("maxpercent : {}",maxHighscore.getPercent());
			LOGGER.info("score : {}",highscore.getScore());
			LOGGER.info("percent : {}",highscore.getPercent());
			if (maxHighscore.compareTo(highscore)>0) {
		    	addHighscore(highscore, level, maxHighscore);
			}
		}
	}

	public boolean addHighscore(Highscore highscore, int level, Highscore maxHighscore)
    {
        try (Connection conn = ConnexionFactory.getConnectionInstance()){
			if (maxHighscore!=null) {
				deleteHighscore(maxHighscore, conn, level);
			}
	        insertHighscore(highscore, level, conn);
	        return true;
		} catch (SQLException | PropertyVetoException e) {
			LOGGER.error("Error while inserting highscore.", e);
		}
        return false;
    }

	private void insertHighscore(Highscore highscore, int level, Connection conn)
			throws SQLException {
		String query = new StringBuilder("INSERT INTO HIGHSCORE_")
		        .append(level)
		        .append("(NAME,DATE,SCORE,PERCENT) VALUES ('")
                .append(highscore.getName())
                .append("','")
                .append(highscore.getDate())
                .append("',")
                .append(highscore.getScore())
                .append(",")
                .append(highscore.getPercent())
                .append(")").toString();
		LOGGER.info(query);
		try (Statement statement = conn.createStatement()) {
			statement.executeUpdate(query);
		}
	}

	private void createTable(Connection conn, int inputLevel) throws SQLException {
		String level = Integer.toString(inputLevel);
		String query = new StringBuilder("CREATE TABLE HIGHSCORE_")
		                                .append(level)
		                                .append(" (MAP_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
		                                		+ "NAME varchar(255),"
		                                		+ "DATE varchar(255),"
		                                		+ "SCORE int,"
				                                + "PERCENT int )").toString();
		LOGGER.info(query);
		try (Statement statement = conn.createStatement()) {
			statement.executeUpdate(query);
		}
	}

	public void deleteHighscore (Highscore highscore, Connection conn, int iLevel) throws SQLException {
		String level = Integer.toString(iLevel);
		String highscoreTable = "HIGHSCORE_" + level;
		String query = new StringBuilder("DELETE FROM ")
		                                .append(highscoreTable)
		                                .append(" WHERE MAP_ID = ? ").toString();
		LOGGER.info(query);
	    try(PreparedStatement statement = conn.prepareStatement(query)) {
		    statement.setLong(1, highscore.getId());
		    statement.executeUpdate();
	    }
	}
}
