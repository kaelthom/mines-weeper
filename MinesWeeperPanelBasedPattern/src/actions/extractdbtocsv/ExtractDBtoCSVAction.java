package actions.extractdbtocsv;

import java.util.List;

import actions.GenericAbstractInputObjectAction;
import datas.DataManager;
import datas.Highscore;
import tools.parsers.Parsers;
import tools.parsers.csv.CsvParser;

public class ExtractDBtoCSVAction extends GenericAbstractInputObjectAction<ExtractDBtoCSVActionInput> {

	@Override
	public int execute(ExtractDBtoCSVActionInput input) {

		try {
			CsvParser<Highscore> csvHighscoreParser = Parsers.getCsvHighscoreParser();
			for (int iLevel = 0; iLevel < 3; iLevel++) {
				List<Highscore> highscores = DataManager.getHighscores(iLevel);
				for (int iHighscore = 0; iHighscore<highscores.size(); iHighscore++) {
					Highscore highscore = highscores.get(iHighscore);
					System.out.println("highscore(" + iHighscore + ").name : " + highscore.getName());
					System.out.println("highscore(" + iHighscore + ").id : " + highscore.getId());
					System.out.println("highscore(" + iHighscore + ").date : " + highscore.getDate());
					System.out.println("highscore(" + iHighscore + ").score : " + highscore.getScore());
					System.out.println("highscore(" + iHighscore + ").percent : " + highscore.getPercent());
				}
				String highscoresFilePath = "highscores/highscore_" + iLevel + ".csv";
				csvHighscoreParser.unParse(highscores, highscoresFilePath);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	

}
