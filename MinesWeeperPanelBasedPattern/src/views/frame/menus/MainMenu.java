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

import languages.LanguageFactory;
import views.DeminorView;
import views.HighscoreView;
import views.OptionsView;
import views.frame.MenuFrame;
import datas.DataManager;

public class MainMenu
    implements ActionListener
{

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
        } else
        if(e.getActionCommand().equals("FileNew"))
        {
            int level = DeminorView.getLevel();
            MenuFrame deminorFrame = DeminorView.getFrame();
            deminorFrame.getContentPane().removeAll();
            DeminorView.createDeminorPanel(level);
            deminorFrame.add(DeminorView.getDeminorPanel());
            deminorFrame.repaint();
        } else
        if(e.getActionCommand().equals("OptionsHighscores"))
            HighscoreView.launchFrame(new HighscoreView(DataManager.getHighscores()));
    }

    private JMenu menus[] = {
        new JMenu(ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("menu.game")), new JMenu(ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("menu.options"))
    };
    private JMenuItem items[][] = {
        {
            new JMenuItem(ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("menu.gamenew")), 
            new JMenuItem(ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("menu.gameoptions")), 
            new JMenuItem(ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("menu.gameclose"))
        }, {
            new JMenuItem(ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("menu.optionshighscore"))
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
}
