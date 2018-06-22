package actions.extractcsvtodb;

import java.io.File;
import java.util.List;

import actions.GenericAbstractInputObjectAction;
import datas.Highscore;
import tools.parsers.Parsers;
import tools.parsers.csv.CsvParser;

public class ExtractCSVtoDBAction extends GenericAbstractInputObjectAction<ExtractCSVtoDBActionInput> {

	@Override
	public int execute(ExtractCSVtoDBActionInput input) {
		File csvFile = input.getCsvFile();
		
		System.out.println(csvFile.getAbsolutePath());
		try {
			CsvParser<Highscore> csvHighscoreParser = Parsers.getCsvHighscoreParser();
			List<Highscore> highscores = csvHighscoreParser.parse(csvFile);
			for (int iHighscore = 0; iHighscore<highscores.size(); iHighscore++) {
				Highscore highscore = highscores.get(iHighscore);
				System.out.println("highscore(" + iHighscore + ").name : " + highscore.getName());
				System.out.println("highscore(" + iHighscore + ").id : " + highscore.getId());
				System.out.println("highscore(" + iHighscore + ").date : " + highscore.getDate());
				System.out.println("highscore(" + iHighscore + ").score : " + highscore.getScore());
				System.out.println("highscore(" + iHighscore + ").percent : " + highscore.getPercent());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	

}
