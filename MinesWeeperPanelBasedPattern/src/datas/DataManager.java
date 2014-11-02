// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataManager.java

package datas;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package datas:
//            HighscoreDAO

public class DataManager
{

    public DataManager()
    {
    }

    public static List<Highscore> getHighscores(int level)
    {
    	highscores = (new HighscoreDAO()).getHighscoreList(level);
        return highscores;
    }

    public static void setHighscores(List<Highscore> highscores)
    {
        DataManager.highscores = highscores;
    }

    private static List<Highscore> highscores = new ArrayList<>();

	public static void insertHighscore(Highscore highscore) {
	   	new HighscoreDAO().addHighscore(highscore);
	}

}
