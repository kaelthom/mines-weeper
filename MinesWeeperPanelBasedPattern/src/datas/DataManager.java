// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataManager.java

package datas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package datas:
//            HighscoreDAO

public class DataManager
{

    public DataManager()
    {
    }

    public static List<Highscore> getHighscores()
    {
    	highscores = (new HighscoreDAO()).getHighscoreList();
        return highscores;
    }

    public static void setHighscores(List<Highscore> highscores)
    {
        DataManager.highscores = highscores;
    }

    private static List<Highscore> highscores = new ArrayList<>();

	public static void insertHighscore(Highscore highscore) {
		List<Highscore> highscores = DataManager.getHighscores();
		System.out.println("size table : " + highscores.size());
		if (highscores.size()<10) {
	    	new HighscoreDAO().addHighscore(highscore);
		} else {
			Highscore maxHighscore = Collections.max(highscores);
			System.out.println("maxHighscore : " + maxHighscore.getScore());
			System.out.println("highscore : " + highscore.getScore());
			if (maxHighscore.getScore()>highscore.getScore()) {
		    	new HighscoreDAO().addHighscore(highscore);
			}
		}
	}

}
