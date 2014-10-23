// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OptionsView.java

package views;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import languages.LanguageFactory;
import views.frame.MenuFrame;
import views.panels.CustomLevelPanel;
import views.panels.OptionsLevelPanel;

// Referenced classes of package views:
//            DeminorView

public class OptionsView extends JPanel
    implements ActionListener
{

    public OptionsView()
    {
        setLayout(new GridLayout(3, 1, 20, 20));
        optionsLevelPanel = new OptionsLevelPanel();
        add(optionsLevelPanel);
        customPanel = new CustomLevelPanel();
        customPanel.createCustomPanel();
        add(customPanel);
        JButton OKButton = new JButton("OK");
        OKButton.addActionListener(this);
        OKButton.setActionCommand("OK");
        add(OKButton);
    }

    private static void createAndShowGUI(JPanel panel)
    {
        frame = new JFrame(OPTIONS_TITLE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(frameBounds);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void launchFrame(final JPanel panel)
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                OptionsView.createAndShowGUI(panel);
            }

        });
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("OK"))
        {
            int level = optionsLevelPanel.getLevel();
            frame.setVisible(false);
            MenuFrame deminorFrame = DeminorView.getFrame();
            deminorFrame.setVisible(true);
            deminorFrame.getContentPane().removeAll();
            DeminorView.createDeminorPanel(level);
            DeminorView.resize();
            deminorFrame.getContentPane().add(DeminorView.getDeminorPanel());
            deminorFrame.repaint();
        }
    }

    public static JFrame getFrame()
    {
        return frame;
    }

    public static void setFrame(JFrame frame)
    {
        OptionsView.frame = frame;
    }

    private static final String OPTIONS_TITLE = ResourceBundle.getBundle("labels", LanguageFactory.getInstance().getLocale()).getString("options.title");
    private static final long serialVersionUID = 0xd9a497f118f4e396L;
    private static JFrame frame;
    private static int frameWidth;
    private static int frameHeight;
    private static int frameX;
    private static int frameY;
    private static Rectangle frameBounds;
    private OptionsLevelPanel optionsLevelPanel;
    private CustomLevelPanel customPanel;

    static 
    {
        frameWidth = 500;
        frameHeight = 500;
        frameX = 0;
        frameY = 0;
        frameBounds = new Rectangle(frameX, frameY, frameWidth, frameHeight);
    }

}
