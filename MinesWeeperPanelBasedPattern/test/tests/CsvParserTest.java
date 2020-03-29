package tests;

import java.io.File;
import java.util.List;

import org.slf4j.LoggerFactory;

import datas.Highscore;
import jdk.internal.instrumentation.Logger;
import tools.parsers.csv.CsvParser;

public class CsvParserTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvParserTest.class);
	
	public static void main(String[] args) {
		
		File example1InputFile = new File("test/highscore_example_input_2.csv");
		
		LOGGER.info(example1InputFile.getAbsolutePath());
		try {
			CsvParser<Highscore> csvParser = new CsvParser<Highscore>(Highscore.class,";");
			List<Highscore> highscores = csvParser.parse(example1InputFile);
			for (int iHighscore = 0; iHighscore<highscores.size(); iHighscore++) {
				Highscore highscore = highscores.get(iHighscore);
				LOGGER.info("highscore(" + iHighscore + ").name : " + highscore.getName());
				LOGGER.info("highscore(" + iHighscore + ").id : " + highscore.getId());
				LOGGER.info("highscore(" + iHighscore + ").date : " + highscore.getDate());
				LOGGER.info("highscore(" + iHighscore + ").score : " + highscore.getScore());
				LOGGER.info("highscore(" + iHighscore + ").percent : " + highscore.getPercent());
			}
			
			String example1OutputFilePath = "test/highscore_example_2.csv";
			File outputPath = csvParser.unParse(highscores, example1OutputFilePath);

		} catch (Exception e) {
			LOGGER.error("Error while testing Csv parser", e);
		}
	}

}
