package tools.parsers;

import datas.Highscore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.parsers.csv.CsvParser;

public class Parsers {

    private static final String CSV_SEPARATOR = ";";
    private static final Logger LOGGER = LoggerFactory.getLogger(Parsers.class);
    private static CsvParser<Highscore> csvHighscoreParser;

    private Parsers() {
        //prevent from call from outside
    }

    public static CsvParser<Highscore> getCsvHighscoreParser() {
        return csvHighscoreParser == null ? createCsvParser() : csvHighscoreParser;
    }

    private static void setCsvHighscoreParser(CsvParser<Highscore> csvHighscoreParser) {
        Parsers.csvHighscoreParser = csvHighscoreParser;
    }

    public static void init() {
        createCsvParser();
    }

    private static CsvParser<Highscore> createCsvParser() {
        csvHighscoreParser = null;
        try {
            setCsvHighscoreParser(new CsvParser<>(Highscore.class, CSV_SEPARATOR));
        } catch (Exception e) {
            LOGGER.error("Error while creating CSV parser", e);
        }

        return csvHighscoreParser;
    }
}
