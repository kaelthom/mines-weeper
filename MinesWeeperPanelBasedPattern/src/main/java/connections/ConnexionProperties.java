// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnexionProperties.java

package connections;

import java.util.ResourceBundle;

public class ConnexionProperties
{

    public final static String DBMS = ResourceBundle.getBundle("connections").getString("dbms");
    public final static String SERVER_NAME = ResourceBundle.getBundle("connections").getString("servername");
    public final static String PORT_NUMBER = ResourceBundle.getBundle("connections").getString("portnumber");
    public final static String DB_NAME = ResourceBundle.getBundle("connections").getString("dbname");
    public final static String USER_NAME = ResourceBundle.getBundle("connections").getString("username");
    public final static String PASSWORD = ResourceBundle.getBundle("connections").getString("password");
    public final static int MIN_POOL_SIZE = Integer.parseInt(ResourceBundle.getBundle("connections").getString("minpoolsize"));
    public final static int MAX_POOL_SIZE = Integer.parseInt(ResourceBundle.getBundle("connections").getString("maxpoolsize"));
    public final static int ACQUIRE_INCREMENT = Integer.parseInt(ResourceBundle.getBundle("connections").getString("acquireincrement"));

}
