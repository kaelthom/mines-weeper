// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainMenu.java

package views.main.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actions.extractcsvtodb.ExtractCSVtoDBAction;
import actions.extractdbtocsv.ExtractDBtoCSVAction;
import actions.newgame.NewGameAction;
import actions.newgame.NewGameActionInput;
import constants.Rights;
import dto.DeminorGamePropertiesFactory;
import messages.Labels;
import views.highscore.HighscoreView;
import views.options.OptionsView;

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
            (new StringBuilder(String.valueOf(labelsBundle.getString(MENU_GAME))))
                                      .append(labelsBundle.getString(MENU_GAMENEW)).toString(),
            (new StringBuilder(String.valueOf(labelsBundle.getString(MENU_GAME))))
                                      .append(labelsBundle.getString(MENU_GAMEOPTIONS)).toString(), 
            (new StringBuilder(String.valueOf(labelsBundle.getString(MENU_GAME))))
                                      .append(labelsBundle.getString(MENU_GAMECLOSE)).toString()
        }, {
            (new StringBuilder(String.valueOf(labelsBundle.getString(MENU_OPTIONS))))
                                     .append(labelsBundle.getString(MENU_OPTIONSHIGHSCORE)).toString()
        }, {
        	(new StringBuilder(String.valueOf(labelsBundle.getString(MENU_ADMIN))))
        	                         .append(labelsBundle.getString(MENU_ADMINCSVTODB)).toString(),
        	(new StringBuilder(String.valueOf(labelsBundle.getString(MENU_ADMIN))))
                                     .append(labelsBundle.getString(MENU_ADMINDBTOCSV)).toString(),
            (new StringBuilder(String.valueOf(labelsBundle.getString(MENU_ADMIN))))
                                    .append(labelsBundle.getString(MENU_ADMINCONFIG)).toString()
        }
    };
    private String[] rights = {
            Rights.GET_GAME_MENU,
            Rights.GET_OPTIONS_MENU,
            Rights.GET_ADMIN_MENU,
        };
    JMenuBar menuBar;
    public MainMenu(List<String> userRights)
    {
        setMenuBar(userRights);
    }

    public JMenuBar getMenuBar()
    {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar)
    {
        this.menuBar = menuBar;
    }

    public void setMenuBar(List<String> userRights)
    {
        for(int i = 0; i < items.length; i++)
        {
            for(int j = 0; j < items[i].length; j++)
            {
            	if (userRights.contains(rights[i])) {
                	menus[i].add(items[i][j]);
                    items[i][j].setActionCommand(actionCommands[i][j]);
                    items[i][j].addActionListener(this);
                    items[i][j].setMnemonic(items[i][j].getText().charAt(0));
            	}
            }
        }

        menuBar = new JMenuBar();
        for(int iMenu = 0; iMenu < menus.length; iMenu++)
        {
        	if (userRights.contains(rights[iMenu])) {
        		menus[iMenu].setMnemonic(menus[iMenu].getText().charAt(0));
        		menuBar.add(menus[iMenu]);
        	}
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        LOGGER.info("actionPerformed from MainMenu called!");
        LOGGER.info("actionCommand : {}",e.getActionCommand());
        
        if(e.getActionCommand().equals("GameClose"))
            System.exit(0);
        else
        if(e.getActionCommand().equals("GameOptions"))
        {
            JFrame optionsFrame = OptionsView.getFrame();
            if(optionsFrame == null)
                OptionsView.launchFrame(new OptionsView());
            else
                optionsFrame.setVisible(true);
        } else if(e.getActionCommand().equals("GameNew"))
        {
        	NewGameActionInput input = new NewGameActionInput(DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance());
        	new NewGameAction().execute(input);
        } else if(e.getActionCommand().equals("OptionsHighscores")) 
        {
        	int level = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance().getLevel();
        	HighscoreView.launchFrame(new HighscoreView(level));
        } else if(e.getActionCommand().equals("AdministrationExtract highscores from CSV file to DB"))
        {
        	new ExtractCSVtoDBAction().execute(null);
        } else if(e.getActionCommand().equals("AdministrationExtract highscores from DB to CSV file"))
        {
        	new ExtractDBtoCSVAction().execute(null);
        }
    }
}
