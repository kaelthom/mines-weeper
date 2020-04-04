// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreDAO.java

package daos;

import datas.DataManager;
import datas.Highscore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.parsers.Parsers;
import tools.parsers.csv.CsvParser;

import java.io.File;
import java.util.Collections;
import java.util.List;


// Referenced classes of package datas:
//            Highscore

public class HighscoreCSVFileDAO {

    private static final int HIGHSCORES_MAX_SIZE = 10;
    private static final String HIGHSCORES_FILE_PATH = "highscores/highscore_";
    private static final String CSV_EXTENSION = ".csv";
    private static final Logger LOGGER = LoggerFactory.getLogger(HighscoreCSVFileDAO.class);

    public List<Highscore> getHighscoreList(int level) {
        CsvParser<Highscore> csvHighscoreParser = Parsers.getCsvHighscoreParser();
        String highscoresFilePath = HIGHSCORES_FILE_PATH + level + CSV_EXTENSION;
        LOGGER.info("Working Directory = {}", System.getProperty("user.dir"));
        List<Highscore> highscores = csvHighscoreParser.parse(new File(highscoresFilePath));
        Collections.sort(highscores);
        return highscores;
    }

    public void addHighscore(Highscore highscore, int level) {
        List<Highscore> highscores = DataManager.getHighscores(level);
        LOGGER.info("size table : {}", highscores.size());
        if (highscores.size() < HIGHSCORES_MAX_SIZE) {
            addHighscore(highscores, highscore, null);
        } else {
            Highscore maxHighscore = Collections.max(highscores);
            LOGGER.info("maxscore : {}", maxHighscore.getScore());
            LOGGER.info("maxpercent : {}", maxHighscore.getPercent());
            LOGGER.info("score : {}", highscore.getScore());
            LOGGER.info("percent : {}", highscore.getPercent());
            if (maxHighscore.compareTo(highscore) > 0) {
                addHighscore(highscores, highscore, maxHighscore);
            }
        }
        CsvParser<Highscore> csvHighscoreParser = Parsers.getCsvHighscoreParser();
        String highscoresFilePath = HIGHSCORES_FILE_PATH + level + CSV_EXTENSION;
        csvHighscoreParser.unParse(highscores, highscoresFilePath);
    }

    private void addHighscore(List<Highscore> highscores, Highscore highscore, Highscore maxHighscore) {
        if (maxHighscore != null) {
            highscores.remove(maxHighscore);
        }
        highscores.add(highscore);
    }

}
