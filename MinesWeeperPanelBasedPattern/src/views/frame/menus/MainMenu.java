// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainMenu.java

package views.frame.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import actions.newgame.NewGameAction;
import actions.newgame.NewGameActionInput;
import dto.DeminorGamePropertiesFactory;
import messages.Labels;
import views.HighscoreView;
import views.OptionsView;

public class MainMenu
    implements ActionListener
{


    private JMenu menus[] = {
        new JMenu(Labels.GAME),
        new JMenu(Labels.OPTIONS)
    };
    private JMenuItem items[][] = {
        {
            new JMenuItem(Labels.GAME_NEW), 
            new JMenuItem(Labels.GAME_OPTIONS), 
            new JMenuItem(Labels.GAME_CLOSE)
        }, {
            new JMenuItem(Labels.OPTIONS_HIGHSCORES)
        }
    };
    private String actionCommands[][] = {
        {
            (new StringBuilder(String.valueOf(ResourceBundle.getBundle("labels", new Locale("en", "EN")).getString("menu.game")))).append(ResourceBundle.getBundle("labels", new Locale("en", "EN")).getString("menu.gamenew")).toString(), (new StringBuilder(String.valueOf(ResourceBundle.getBundle("labels", new Locale("en", "EN")).getString("menu.game")))).append(ResourceBundle.getBundle("labels", new Locale("en", "EN")).getString("menu.gameoptions")).toString(), (new StringBuilder(String.valueOf(ResourceBundle.getBundle("labels", new Locale("en", "EN")).getString("menu.game")))).append(ResourceBundle.getBundle("labels", new Locale("en", "EN")).getString("menu.gameclose")).toString()
        }, {
            (new StringBuilder(String.valueOf(ResourceBundle.getBundle("labels", new Locale("en", "EN")).getString("menu.options")))).append(ResourceBundle.getBundle("labels", new Locale("en", "EN")).getString("menu.optionshighscore")).toString()
        }
    };
    JMenuBar menuBar;
    public MainMenu()
    {
        setMenuBar();
    }

    public JMenuBar getMenuBar()
    {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar)
    {
        this.menuBar = menuBar;
    }

    public void setMenuBar()
    {
        for(int i = 0; i < items.length; i++)
        {
            for(int j = 0; j < items[i].length; j++)
            {
                menus[i].add(items[i][j]);
                items[i][j].setActionCommand(actionCommands[i][j]);
                items[i][j].addActionListener(this);
                items[i][j].setMnemonic(items[i][j].getText().charAt(0));
            }

        }

        menuBar = new JMenuBar();
        for(int iMenu = 0; iMenu < menus.length; iMenu++)
        {
            menus[iMenu].setMnemonic(menus[iMenu].getText().charAt(0));
            menuBar.add(menus[iMenu]);
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println("actionPerformed from MainMenu called!");
        System.out.println((new StringBuilder("actionCommand : ")).append(e.getActionCommand()).toString());
        if(e.getActionCommand().equals("FileClose"))
            System.exit(0);
        else
        if(e.getActionCommand().equals("FileOptions"))
        {
            JFrame optionsFrame = OptionsView.getFrame();
            if(optionsFrame == null)
                OptionsView.launchFrame(new OptionsView());
            else
                optionsFrame.setVisible(true);
        } else if(e.getActionCommand().equals("FileNew"))
        {
        	NewGameActionInput input = new NewGameActionInput(DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance());
        	new NewGameAction().execute(input);
        } else if(e.getActionCommand().equals("OptionsHighscores")) 
        {
        	int level = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance().getLevel();
        	HighscoreView.launchFrame(new HighscoreView(level));
        }
    }
}
