// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MenuFrame.java

package views.main.menu;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class MenuFrame extends JFrame
{

    public MenuFrame(String title)
        throws HeadlessException
    {
        super(title);
        initUI();
    }

    public final void initUI()
    {
        javax.swing.JMenuBar menubar = (new MainMenu()).getMenuBar();
        setJMenuBar(menubar);
        setLocationRelativeTo(null);
    }

    private static final long serialVersionUID = 1L;
}
