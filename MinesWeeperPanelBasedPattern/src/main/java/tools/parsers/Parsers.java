package tools.parsers;

import datas.Highscore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.parsers.csv.CsvParser;

public class Parsers {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parsers.class);
    private static CsvParser<Highscore> csvHighscoreParser;

    private Parsers() {
        //prevent from call from outside
    }

    public static CsvParser<Highscore> getCsvHighscoreParser() {
        return csvHighscoreParser == null ? createCsvParser() : csvHighscoreParser;
    }

    public static void setCsvHighscoreParser(CsvParser<Highscore> csvHighscoreParser) {
        Parsers.csvHighscoreParser = csvHighscoreParser;
    }

    public static int init() {
        createCsvParser();
        return 0;
    }

    private static CsvParser<Highscore> createCsvParser() {
        CsvParser<Highscore> csvParser = null;
        try {
            csvParser = new CsvParser<>(Highscore.class, ";");
        } catch (Exception e) {
            LOGGER.error("Error while creating CSV parser", e);
        }

        return csvParser;
    }
}
