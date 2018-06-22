package actions.extractdbtocsv;

import datas.Highscore;

public class ExtractDBtoCSVActionInput {

	private Highscore highscore;

	public ExtractDBtoCSVActionInput(Highscore highscore) {
		this.setHighscore(highscore);
	}

	public Highscore getHighscore() {
		return highscore;
	}

	public void setHighscore(Highscore highscore) {
		this.highscore = highscore;
	}

}
