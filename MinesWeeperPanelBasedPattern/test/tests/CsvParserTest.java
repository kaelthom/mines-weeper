package tests;

import java.io.File;
import java.util.List;

import datas.Highscore;
import tools.parsers.csv.CsvParser;

public class CsvParserTest {

	public static void main(String[] args) {
		
		File example1InputFile = new File("test/highscore_example_input_2.csv");
		
		System.out.println(example1InputFile.getAbsolutePath());
		try {
			CsvParser<Highscore> csvParser = new CsvParser<Highscore>(Highscore.class,";");
			List<Highscore> highscores = csvParser.parse(example1InputFile);
			for (int iHighscore = 0; iHighscore<highscores.size(); iHighscore++) {
				Highscore highscore = highscores.get(iHighscore);
				System.out.println("highscore(" + iHighscore + ").name : " + highscore.getName());
				System.out.println("highscore(" + iHighscore + ").id : " + highscore.getId());
				System.out.println("highscore(" + iHighscore + ").date : " + highscore.getDate());
				System.out.println("highscore(" + iHighscore + ").score : " + highscore.getScore());
				System.out.println("highscore(" + iHighscore + ").percent : " + highscore.getPercent());
			}
			
			String example1OutputFilePath = "test/highscore_example_2.csv";
			File outputPath = csvParser.unParse(highscores, example1OutputFilePath);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
