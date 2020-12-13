// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnexionProperties.java

package connections;

import java.util.ResourceBundle;

public class ConnexionProperties {

    private static final ResourceBundle CONNECTIONS_RESOURCE_BUNDLE = ResourceBundle.getBundle("connections");
    static final String SERVER_NAME = CONNECTIONS_RESOURCE_BUNDLE.getString("servername");
    static final String PORT_NUMBER = CONNECTIONS_RESOURCE_BUNDLE.getString("portnumber");
    static final String DB_NAME = CONNECTIONS_RESOURCE_BUNDLE.getString("dbname");
    static final String USER_NAME = CONNECTIONS_RESOURCE_BUNDLE.getString("username");
    static final String PASSWORD = CONNECTIONS_RESOURCE_BUNDLE.getString("password");
    static final String DBMS = CONNECTIONS_RESOURCE_BUNDLE.getString("dbms");
    static final int MIN_POOL_SIZE = Integer.parseInt(CONNECTIONS_RESOURCE_BUNDLE.getString("minpoolsize"));
    static final int MAX_POOL_SIZE = Integer.parseInt(CONNECTIONS_RESOURCE_BUNDLE.getString("maxpoolsize"));
    static final int ACQUIRE_INCREMENT = Integer.parseInt(CONNECTIONS_RESOURCE_BUNDLE.getString("acquireincrement"));

    private ConnexionProperties() {
    }

}
