package actions.extractcsvtodb;

import actions.GenericAbstractInputObjectAction;
import datas.Highscore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.parsers.Parsers;
import tools.parsers.csv.CsvParser;

import java.io.File;
import java.util.List;

public class ExtractCSVtoDBAction implements GenericAbstractInputObjectAction<ExtractCSVtoDBActionInput> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractCSVtoDBAction.class);
    private static final String HIGHSCORE_MESSAGE = "highscore({}).{} : {}";
    private static final String CSV_FILE_PATH_MESSAGE = "CSV file path : {}";
    private static final String NAME = "NAME";
    private static final String ID = "ID";
    private static final String DATE = "DATE";
    private static final String SCORE = "SCORE";
    private static final String PERCENT = "PERCENT";

    @Override
    public int execute(ExtractCSVtoDBActionInput input) {
        File csvFile = input.getCsvFile();

        LOGGER.debug("%n");
        LOGGER.debug("%n");
        LOGGER.debug(CSV_FILE_PATH_MESSAGE, csvFile.getAbsolutePath());
        try {
            CsvParser<Highscore> csvHighscoreParser = Parsers.getCsvHighscoreParser();
            List<Highscore> highscores = csvHighscoreParser.parse(csvFile);
            for (int iHighscore = 0; iHighscore < highscores.size(); iHighscore++) {
                Highscore highscore = highscores.get(iHighscore);
                LOGGER.debug(HIGHSCORE_MESSAGE, iHighscore, NAME, highscore.getName());
                LOGGER.debug(HIGHSCORE_MESSAGE, iHighscore, ID, highscore.getId());
                LOGGER.debug(HIGHSCORE_MESSAGE, iHighscore, DATE, highscore.getName());
                LOGGER.debug(HIGHSCORE_MESSAGE, iHighscore, SCORE, highscore.getScore());
                LOGGER.debug(HIGHSCORE_MESSAGE, iHighscore, PERCENT, highscore.getName());
            }

        } catch (Exception e) {
            LOGGER.error("Error while parsing CSV", e);
        }
        LOGGER.debug("%n");
        LOGGER.debug("%n");
        return 0;
    }


}
