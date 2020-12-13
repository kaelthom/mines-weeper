package actions.extractcsvtodb;

import java.io.File;

public class ExtractCSVtoDBActionInput {

    private File csvFile;

    public ExtractCSVtoDBActionInput(File csvFile) {
        this.setCsvFile(csvFile);
    }

    File getCsvFile() {
        return csvFile;
    }

    private void setCsvFile(File csvFile) {
        this.csvFile = csvFile;
    }

}
