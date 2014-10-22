// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnexionProperties.java

package connections;

import java.util.ResourceBundle;

public class ConnexionProperties
{

    public static String DBMS = ResourceBundle.getBundle("connections").getString("dbms");
    public static String SERVER_NAME = ResourceBundle.getBundle("connections").getString("servername");
    public static String PORT_NUMBER = ResourceBundle.getBundle("connections").getString("portnumber");
    public static String DB_NAME = ResourceBundle.getBundle("connections").getString("dbname");
    public static String USER_NAME = ResourceBundle.getBundle("connections").getString("username");
    public static String PASSWORD = ResourceBundle.getBundle("connections").getString("password");
    public static int MIN_POOL_SIZE = Integer.parseInt(ResourceBundle.getBundle("connections").getString("minpoolsize"));
    public static int MAX_POOL_SIZE = Integer.parseInt(ResourceBundle.getBundle("connections").getString("maxpoolsize"));
    public static int ACQUIRE_INCREMENT = Integer.parseInt(ResourceBundle.getBundle("connections").getString("acquireincrement"));

}
