package tools.parsers.csv;

import datas.Highscore;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvParserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParserTest.class);
    private CsvParser<Highscore> csvParser = null;

    @Before
    public void setUp() throws Exception {
        csvParser = new CsvParser<>(Highscore.class,     ";");
    }

    @Test
    public void parse() {
        File example1InputFile = new File("test/highscore_example_input_2.csv");

        LOGGER.info(example1InputFile.getAbsolutePath());
        try {
            List<Highscore> highscores = csvParser.parse(example1InputFile);
            for (int iHighscore = 0; iHighscore<highscores.size(); iHighscore++) {
                Highscore highscore = highscores.get(iHighscore);
                LOGGER.info("highscore(" + iHighscore + ").name : " + highscore.getName());
                LOGGER.info("highscore(" + iHighscore + ").id : " + highscore.getId());
                LOGGER.info("highscore(" + iHighscore + ").date : " + highscore.getDate());
                LOGGER.info("highscore(" + iHighscore + ").score : " + highscore.getScore());
                LOGGER.info("highscore(" + iHighscore + ").percent : " + highscore.getPercent());
            }
            assertThat(highscores).hasSize(1);

        } catch (Exception e) {
            LOGGER.error("Error while testing Csv parser", e);
        }
    }

    @Test
    public void unParse() {
        List<Highscore> inputHighscores = givenInputHighScores();

        String example1OutputFilePath = "test/highscore_example_output_1.csv";
        File outputPath = csvParser.unParse(inputHighscores, example1OutputFilePath);
        List<String> outputLines = null;
        // read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(outputPath.getAbsolutePath()))) {
            outputLines = stream.collect(Collectors.toList());
        } catch (IOException | SecurityException e) {
            LOGGER.error("Error while parsing CSV file",e);
        }

        assertThat(outputLines).hasSize(1);
        assertThat(outputLines.get(0)).isEqualTo("Toto;1;2020-11-25T10:00;100;25;");
    }

    private List<Highscore> givenInputHighScores() {
        Highscore inputHighscore = new Highscore();
        inputHighscore.setDate(LocalDateTime.of(2020,11,25,10,0,0).toString());
        inputHighscore.setId(1l);
        inputHighscore.setName("Toto");
        inputHighscore.setPercent(100);
        inputHighscore.setScore(25);
        List<Highscore> inputHighscores = new ArrayList<>();
        inputHighscores.add(inputHighscore);
        return inputHighscores;
    }
}