// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OptionsView.java

package views.options;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import actions.optionssubmit.OptionsSubmitAction;
import actions.optionssubmit.OptionsSubmitActionInput;
import dto.DeminorGameProperties;
import dto.DeminorGamePropertiesFactory;
import messages.Labels;
import views.options.panels.CustomLevelPanel;
import views.options.panels.OptionsLevelPanel;

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
        frame = new JFrame(Labels.OPTIONS_TITLE);
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
    		DeminorGameProperties gameProperties = DeminorGamePropertiesFactory.getDeminorGamePropertiesInstance();
    		int level = optionsLevelPanel.getLevel();
    		gameProperties.setLevel(level);
    		DeminorGamePropertiesFactory.updateDeminorGameProperties(gameProperties);
			new OptionsSubmitAction().execute(new OptionsSubmitActionInput(gameProperties));
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
