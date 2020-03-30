package actions.extractcsvtodb;

import java.io.File;

public class ExtractCSVtoDBActionInput {

	private File csvFile;

	public ExtractCSVtoDBActionInput(File csvFile) {
		this.setCsvFile(csvFile);
	}

	public File getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}

}
