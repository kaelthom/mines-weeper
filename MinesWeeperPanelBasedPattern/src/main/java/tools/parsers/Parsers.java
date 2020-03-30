package tools.parsers;

import datas.Highscore;
import tools.parsers.csv.CsvParser;

public class Parsers {

	private static CsvParser<Highscore> csvHighscoreParser;

	public static CsvParser<Highscore> getCsvHighscoreParser() {
		return csvHighscoreParser;
	}

	public static void setCsvHighscoreParser(CsvParser<Highscore> csvHighscoreParser) {
		Parsers.csvHighscoreParser = csvHighscoreParser;
	}

	public static int init() {
		try {
			csvHighscoreParser = new CsvParser<Highscore>(Highscore.class,";");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
