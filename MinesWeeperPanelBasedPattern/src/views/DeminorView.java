// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeminorView.java

package views;

import images.ImageHandler;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import languages.LanguageFactory;
import views.frame.MenuFrame;
import views.panels.CellsPanel;
import views.panels.DeminorPanel;

public class DeminorView extends JPanel
    implements ActionListener
{

    public DeminorView()
    {
    }

    public static void createDeminorPanel(int iLevel)
    {
        setLevel(iLevel);
        ImageHandler.createImages();
        deminorPanel = DeminorPanel.createDeminorPanel(iLevel);
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
        frame = new MenuFrame(DEMINOR_TITLE);
        frame.setDefaultCloseOperation(3);
        createDeminorPanel(initialLevel);
        resize();
        frame.add(deminorPanel);
        frame.setVisible(true);
    }

    public static void main(String args[])
    {
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

    private static final String DEMINOR_TITLE = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("main.title");
    private static final long serialVersionUID = 1L;
    private static MenuFrame frame;
    private static int initialLevel = 0;
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
