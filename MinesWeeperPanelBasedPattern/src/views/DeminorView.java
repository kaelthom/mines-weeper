// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeminorView.java

package views;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import datas.DataManager;
import dto.DeminorGameProperties;
import dto.DeminorGamePropertiesFactory;
import images.ImageHandler;
import languages.LanguageFactory;
import messages.Labels;
import views.frame.MenuFrame;
import views.panels.CellsPanel;
import views.panels.DeminorPanel;

public class DeminorView extends JPanel
    implements ActionListener
{

    public DeminorView()
    {
    }

    public static void createDeminorPanel(DeminorGameProperties gameProperties)
    {
    	int iLevel = gameProperties.getLevel();
        setLevel(iLevel);
        ImageHandler.createImages();
        deminorPanel = DeminorPanel.createPanel(gameProperties);
        LanguageFactory.initLanguage();
    }

    public static void resize()
    {
        frame.pack();
        borderHeight = (frame.getHeight() - frame.getContentPane().getHeight()) / 2;
        borderWidth = (frame.getWidth() - frame.getContentPane().getWidth()) / 2;
        frameWidth = deminorPanel.getWidth() + borderWidth * 2;
        frameHeight = deminorPanel.getHeight() + borderHeight * 2;
        frameBounds = new Rectangle(frameX, frameY, frameWidth, frameHeight);
        frame.setBounds(frameBounds);
    }

    private static void createAndShowGUI()
    {
        frame = new MenuFrame(Labels.DEMINOR_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DeminorGameProperties gameProperties = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance();
        createDeminorPanel(gameProperties);
        resize();
        frame.add(deminorPanel);
        frame.setVisible(true);
    }

    public static void main(String args[])
    {
    	DataManager.getHighscores(DeminorGameProperties.getInitialLevel());
    	SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                DeminorView.createAndShowGUI();
            }

        }
);
    }

    public static CellsPanel getCellsPanel()
    {
        return cellsPanel;
    }

    public static void setCellsPanel(CellsPanel cellsPanel)
    {
        DeminorView.cellsPanel = cellsPanel;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("OK"))
            System.out.println("Hello!!!!!");
    }

    public static MenuFrame getFrame()
    {
        return frame;
    }

    public static void setFrame(MenuFrame frame)
    {
    	DeminorView.frame = frame;
    }

    public static JPanel getDeminorPanel()
    {
        return deminorPanel;
    }

    public static void setDeminorPanel(DeminorPanel deminorPanel)
    {
    	DeminorView.deminorPanel = deminorPanel;
    }

    public static int getLevel()
    {
        return level;
    }

    public static void setLevel(int level)
    {
    	DeminorView.level = level;
    }

    private static final long serialVersionUID = 1L;
    private static MenuFrame frame;
    private static int frameWidth;
    private static int frameHeight;
    private static int frameX = 0;
    private static int frameY = 0;
    private static Rectangle frameBounds;
    private static int borderWidth;
    private static int borderHeight;
    private static CellsPanel cellsPanel;
    private static DeminorPanel deminorPanel;
    private static int level;


}
