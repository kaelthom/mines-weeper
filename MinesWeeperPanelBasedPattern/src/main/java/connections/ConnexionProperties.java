// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnexionProperties.java

package connections;

import java.util.ResourceBundle;

public class ConnexionProperties {

    static final String SERVER_NAME = ResourceBundle.getBundle("connections").getString("servername");
    static final String PORT_NUMBER = ResourceBundle.getBundle("connections").getString("portnumber");
    static final String DB_NAME = ResourceBundle.getBundle("connections").getString("dbname");
    static final String USER_NAME = ResourceBundle.getBundle("connections").getString("username");
    static final String PASSWORD = ResourceBundle.getBundle("connections").getString("password");
    static final int MIN_POOL_SIZE = Integer.parseInt(ResourceBundle.getBundle("connections").getString("minpoolsize"));
    static final int MAX_POOL_SIZE = Integer.parseInt(ResourceBundle.getBundle("connections").getString("maxpoolsize"));
    static final int ACQUIRE_INCREMENT = Integer.parseInt(ResourceBundle.getBundle("connections").getString("acquireincrement"));
    private static final ResourceBundle CONNECTIONS_RESOURCE_BUNDLE = ResourceBundle.getBundle("connections");
    static final String DBMS = CONNECTIONS_RESOURCE_BUNDLE.getString("dbms");

    private ConnexionProperties() {
    }

}
