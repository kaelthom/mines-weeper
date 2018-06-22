// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreDAO.java

package daos;

import java.io.File;
import java.util.Collections;
import java.util.List;

import datas.DataManager;
import datas.Highscore;
import tools.parsers.Parsers;
import tools.parsers.csv.CsvParser;


// Referenced classes of package datas:
//            Highscore

public class HighscoreCSVFileDAO
{
    public HighscoreCSVFileDAO()
    {
    }

    public List<Highscore> getHighscoreList(int level)
    {
		CsvParser<Highscore> csvHighscoreParser = Parsers.getCsvHighscoreParser();
		String highscoresFilePath = "highscores/highscore_" + level + ".csv";
		List<Highscore> highscores = csvHighscoreParser.parse(new File(highscoresFilePath));
        Collections.sort(highscores);
        return highscores;
    }

	public void addHighscore(Highscore highscore, int level) {
		List<Highscore> highscores = DataManager.getHighscores(level);
		System.out.println("size table : " + highscores.size());
		if (highscores.size()<10) {
	    	addHighscore(highscores, highscore, level, null);
		} else {
			Highscore maxHighscore = Collections.max(highscores);
			System.out.println("maxscore : " + maxHighscore.getScore());
			System.out.println("maxpercent : " + maxHighscore.getPercent());
			System.out.println("score : " + highscore.getScore());
			System.out.println("percent : " + highscore.getPercent());
			if (maxHighscore.compareTo(highscore)==1) {
		    	addHighscore(highscores, highscore, level, maxHighscore);
			}
		}
		CsvParser<Highscore> csvHighscoreParser = Parsers.getCsvHighscoreParser();
		String highscoresFilePath = "highscores/highscore_" + level + ".csv";
		csvHighscoreParser.unParse(highscores, highscoresFilePath);
	}

	public boolean addHighscore(List<Highscore> highscores, Highscore highscore, int level, Highscore maxHighscore)
    {
		if (maxHighscore!=null) highscores.remove(maxHighscore);
	    highscores.add(highscore);
	    return true;
    }

}
