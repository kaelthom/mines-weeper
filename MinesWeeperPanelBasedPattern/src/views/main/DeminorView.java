// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeminorView.java

package views.main;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import constants.Profiles;
import datas.DataManager;
import dto.DeminorGameProperties;
import dto.DeminorGamePropertiesFactory;
import dto.DeminorPanelProperties;
import images.ImageHandler;
import languages.LanguageFactory;
import messages.Labels;
import tools.parsers.Parsers;
import views.main.menu.MainMenu;
import views.main.panels.CellsPanel;
import views.main.panels.DeminorPanel;

public class DeminorView extends JPanel
    implements ActionListener
{

    public DeminorView()
    {
    }

    public static void createDeminorPanel(DeminorGameProperties gameProperties)
    {
        deminorPanel = DeminorPanel.createPanel(gameProperties);
        ImageHandler.createImages();
        LanguageFactory.initLanguage();
    }

    public static void resize()
    {
        frame.pack();
        borderHeight = (frame.getHeight() - frame.getContentPane().getHeight()) / 2;
        borderWidth = (frame.getWidth() - frame.getContentPane().getWidth()) / 2;
        frameWidth = deminorPanel.getWidth() + borderWidth * 2;
        frameHeight = deminorPanel.getHeight() + borderHeight * 2;
        frameBounds = new Rectangle(DeminorPanelProperties.getFrameX(), DeminorPanelProperties.getFrameY(), frameWidth, frameHeight);
        frame.setBounds(frameBounds);
    }

    private static void createAndShowGUI(List<String> userRights)
    {
        frame = new JFrame(Labels.DEMINOR_TITLE);

        javax.swing.JMenuBar menubar = (new MainMenu(userRights)).getMenuBar();
        frame.setJMenuBar(menubar);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DeminorGameProperties gameProperties = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance();
        createDeminorPanel(gameProperties);
        resize();
        frame.add(deminorPanel);
        frame.setVisible(true);
    }

    public static void main(String args[])
    {
    	String userProfile = Profiles.ADMIN_USER;
    	String userRights[] = Profiles.rightsPerProfile.get(userProfile);
    	
    	Parsers.init();
    	
    	DataManager.getHighscores(DeminorGameProperties.getInitialLevel());
    	
    	SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                DeminorView.createAndShowGUI(Arrays.asList(userRights));
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

    public static JFrame getFrame()
    {
        return frame;
    }

    public static void setFrame(JFrame frame)
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

    private static final long serialVersionUID = 1L;
    private static JFrame frame;
    private static int frameWidth;
    private static int frameHeight;
    private static Rectangle frameBounds;
    private static int borderWidth;
    private static int borderHeight;
    private static CellsPanel cellsPanel;
    private static DeminorPanel deminorPanel;


}
