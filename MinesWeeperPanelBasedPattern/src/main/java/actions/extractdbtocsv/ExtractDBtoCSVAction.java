package actions.extractdbtocsv;

import actions.GenericAbstractInputObjectAction;
import datas.DataManager;
import datas.Highscore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.parsers.Parsers;
import tools.parsers.csv.CsvParser;

import java.util.List;

public class ExtractDBtoCSVAction implements GenericAbstractInputObjectAction<ExtractDBtoCSVActionInput> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractDBtoCSVAction.class);
    private static final String HIGHSCORE_MESSAGE = "Highscore({}).{} : {}";
    private static final String UNPARSE_MESSAGE = "Unparsing to CSV file path : {}";
    private static final String NAME = "NAME   ";
    private static final String ID = "ID     ";
    private static final String DATE = "DATE   ";
    private static final String SCORE = "SCORE  ";
    private static final String PERCENT = "PERCENT";

    @Override
    public int execute(ExtractDBtoCSVActionInput input) {

        try {
            CsvParser<Highscore> csvHighscoreParser = Parsers.getCsvHighscoreParser();
            for (int iLevel = 0; iLevel < 3; iLevel++) {
                List<Highscore> highscores = DataManager.getHighscores(iLevel);
                for (int iHighscore = 0; iHighscore < highscores.size(); iHighscore++) {
                    Highscore highscore = highscores.get(iHighscore);
                    LOGGER.info(HIGHSCORE_MESSAGE, iHighscore, NAME, highscore.getName());
                    LOGGER.info(HIGHSCORE_MESSAGE, iHighscore, ID, highscore.getId());
                    LOGGER.info(HIGHSCORE_MESSAGE, iHighscore, DATE, highscore.getDate());
                    LOGGER.info(HIGHSCORE_MESSAGE, iHighscore, SCORE, highscore.getScore());
                    LOGGER.info(HIGHSCORE_MESSAGE, iHighscore, PERCENT, highscore.getName());
                }
                String highscoresFilePath = String.format("/highscore_%d.csv", iLevel);

                LOGGER.info(UNPARSE_MESSAGE, highscoresFilePath);
                csvHighscoreParser.unParse(highscores, String.format(highscoresFilePath, iLevel));
            }

        } catch (Exception e) {
            LOGGER.error("Error while parsing csv file", e);
        }
        return 0;
    }


}
