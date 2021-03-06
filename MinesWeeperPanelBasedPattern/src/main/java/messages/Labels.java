package messages;

import languages.LanguageFactory;

import java.util.ResourceBundle;

public class Labels {

    private static final String LABELS_FILE_NAME = "labels";

    public static final String DEMINOR_TITLE = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("main.title");

    public static final String GAMELOST = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("main.gamelost");
    public static final String GAMEWON = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("main.gamewon");

    public static final String GAME = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.game");
    public static final String OPTIONS = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.options");
    public static final String ADMIN = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.admin");

    public static final String GAME_NEW = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.gamenew");
    public static final String GAME_OPTIONS = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.gameoptions");
    public static final String GAME_CLOSE = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.gameclose");
    public static final String OPTIONS_HIGHSCORES = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.optionshighscore");

    public static final String ADMIN_CSVTODB = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.admincsvtodb");
    public static final String ADMIN_DBTOCSV = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.admindbtocsv");
    public static final String ADMIN_CONFIG = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("menu.adminconfig");

    public static final String OPTIONS_CELLS_PER_WIDTH = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("options.cellsperwidth");
    public static final String OPTIONS_CELLS_PER_HEIGHT = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("options.cellsperheight");
    public static final String OPTIONS_NUMBER_OF_BOMBS = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("options.numberofbombs");

    public static final String NAME_COLUMN_NAME = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.name");
    public static final String DATE_COLUMN_NAME = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.date");
    public static final String SCORE_COLUMN_NAME = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.score");
    public static final String PERCENT_COLUMN_NAME = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("highscores.columnname.percent");

    public static final String OPTIONS_LEVEL_EASY = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("options.level.easy");
    public static final String OPTIONS_LEVEL_MEDIUM = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("options.level.medium");
    public static final String OPTIONS_LEVEL_EXPERT = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("options.level.expert");
    public static final String OPTIONS_LEVEL_CUSTOM = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("options.level.custom");

    public static final String HIGHSCORES_TITLE = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("highscores.title");

    public static final String OPTIONS_TITLE = ResourceBundle.getBundle(LABELS_FILE_NAME, LanguageFactory.getInstance().getLocale()).getString("options.title");

    private Labels() {
        //prevent from being called from outside
    }
}
