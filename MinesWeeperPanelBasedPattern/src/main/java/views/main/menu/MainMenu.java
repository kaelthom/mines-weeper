// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainMenu.java

package views.main.menu;

import actions.extractcsvtodb.ExtractCSVtoDBAction;
import actions.extractdbtocsv.ExtractDBtoCSVAction;
import actions.newgame.NewGameAction;
import actions.newgame.NewGameActionInput;
import constants.Rights;
import dto.DeminorGamePropertiesFactory;
import messages.Labels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.highscore.HighscoreView;
import views.options.OptionsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainMenu implements ActionListener {

    private static final String MENU_OPTIONSHIGHSCORE = "menu.optionshighscore";

    private static final String MENU_OPTIONS = "menu.options";

    private static final String MENU_ADMINCONFIG = "menu.adminconfig";

    private static final String MENU_ADMINDBTOCSV = "menu.admindbtocsv";

    private static final String MENU_ADMINCSVTODB = "menu.admincsvtodb";

    private static final String MENU_ADMIN = "menu.admin";

    private static final String MENU_GAMECLOSE = "menu.gameclose";

    private static final String MENU_GAMEOPTIONS = "menu.gameoptions";

    private static final String MENU_GAMENEW = "menu.gamenew";

    private static final String MENU_GAME = "menu.game";

    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);
    private JMenuBar menuBar;
    private JMenu[] menus = {
            new JMenu(Labels.GAME),
            new JMenu(Labels.OPTIONS),
            new JMenu(Labels.ADMIN)
    };
    private JMenuItem[][] items = {
            {
                    new JMenuItem(Labels.GAME_NEW),
                    new JMenuItem(Labels.GAME_OPTIONS),
                    new JMenuItem(Labels.GAME_CLOSE)
            }, {
            new JMenuItem(Labels.OPTIONS_HIGHSCORES)
    }, {
            new JMenuItem(Labels.ADMIN_CSVTODB),
            new JMenuItem(Labels.ADMIN_DBTOCSV),
            new JMenuItem(Labels.ADMIN_CONFIG)
    }
    };
    private ResourceBundle labelsBundle = ResourceBundle.getBundle("labels", new Locale("en", "EN"));
    private String[][] actionCommands = {
            {
                    labelsBundle.getString(MENU_GAME) + labelsBundle.getString(MENU_GAMENEW),
                    labelsBundle.getString(MENU_GAME) + labelsBundle.getString(MENU_GAMEOPTIONS),
                    labelsBundle.getString(MENU_GAME) + labelsBundle.getString(MENU_GAMECLOSE)
            }, {
            labelsBundle.getString(MENU_OPTIONS) + labelsBundle.getString(MENU_OPTIONSHIGHSCORE)
    }, {
            labelsBundle.getString(MENU_ADMIN) + labelsBundle.getString(MENU_ADMINCSVTODB),
            labelsBundle.getString(MENU_ADMIN) + labelsBundle.getString(MENU_ADMINDBTOCSV),
            labelsBundle.getString(MENU_ADMIN) + labelsBundle.getString(MENU_ADMINCONFIG)
    }
    };
    private String[] rights = {
            Rights.GET_GAME_MENU,
            Rights.GET_OPTIONS_MENU,
            Rights.GET_ADMIN_MENU,
    };

    public MainMenu(List<String> userRights) {
        setMenuBar(userRights);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    private void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    private void setMenuBar(List<String> userRights) {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                if (userRights.contains(rights[i])) {
                    menus[i].add(items[i][j]);
                    items[i][j].setActionCommand(actionCommands[i][j]);
                    items[i][j].addActionListener(this);
                    items[i][j].setMnemonic(items[i][j].getText().charAt(0));
                }
            }
        }

        setMenuBar(new JMenuBar());
        for (int iMenu = 0; iMenu < menus.length; iMenu++) {
            if (userRights.contains(rights[iMenu])) {
                menus[iMenu].setMnemonic(menus[iMenu].getText().charAt(0));
                menuBar.add(menus[iMenu]);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        LOGGER.info("actionPerformed from MainMenu called!");
        LOGGER.info("actionCommand : {}", e.getActionCommand());

        switch (e.getActionCommand()) {
            case "GameClose":
                System.exit(0);
                break;
            case "GameOptions":
                JFrame optionsFrame = OptionsView.getFrame();
                if (optionsFrame == null) {
                    OptionsView.launchFrame(new OptionsView());
                } else {
                    optionsFrame.setVisible(true);
                }
                break;
            case "GameNew":
                NewGameActionInput input = new NewGameActionInput(DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance());
                new NewGameAction().execute(input);
                break;
            case "OptionsHighscores":
                int level = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance().getLevel();
                HighscoreView.launchFrame(new HighscoreView(level));
                break;
            case "AdministrationExtract highscores from CSV file to DB":
                //TODO : correct this why inject null
                new ExtractCSVtoDBAction().execute(null);
                break;
            case "AdministrationExtract highscores from DB to CSV file":
                new ExtractDBtoCSVAction().execute(null);
                break;
            default:
                LOGGER.error("Action Command [{}] not handled", e.getActionCommand());
        }
    }
}
