// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnexionFactory.java

package connections;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import actions.cellleftclick.CellLeftClickAction;

// Referenced classes of package connexions:
//            ConnexionProperties

public class ConnexionFactory
{

	private static final Logger LOGGER = LoggerFactory.getLogger(CellLeftClickAction.class);

    public ConnexionFactory()
    {
        dataSource = null;
    }

    public static Connection getConnectionInstance()
        throws PropertyVetoException, SQLException
    {
        if(connFactory == null)
        {
            connFactory = new ConnexionFactory();
        }
        conn = connFactory.getConnection();
        return conn;
    }

    private Connection getConnection()
        throws PropertyVetoException, SQLException
    {
        dataSource = getDataSource();
        conn = dataSource.getConnection();
        LOGGER.info((new StringBuilder("Connected to ")).append(ConnexionProperties.DBMS).append(" database").toString());
        return conn;
    }

    public ComboPooledDataSource getDataSource()
        throws PropertyVetoException
    {
        return dataSource != null ? dataSource : initDataSource();
    }

    public void setDataSource(ComboPooledDataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    private static ComboPooledDataSource initDataSource()
        throws PropertyVetoException
    {
        String url = null;
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        if(ConnexionProperties.DBMS.equals("mysql"))
        {
            url = (new StringBuilder("jdbc:")).append(ConnexionProperties.DBMS).append("://").append(ConnexionProperties.SERVER_NAME).append(":").append(ConnexionProperties.PORT_NUMBER).append("/").toString();
            cpds.setDriverClass("org.mysql.Driver");
            cpds.setUser(ConnexionProperties.USER_NAME);
            cpds.setPassword(ConnexionProperties.PASSWORD);
        } else
        if(ConnexionProperties.DBMS.equals("derby"))
        {
            url = (new StringBuilder("jdbc:")).append(ConnexionProperties.DBMS).append(":").append(ConnexionProperties.DB_NAME).append(";create=true").toString();
            cpds.setDriverClass("org.apache.derby.jdbc.EmbeddedDriver");
        } else
        if(ConnexionProperties.DBMS.equals("postgresql"))
        {
            url = (new StringBuilder("jdbc:")).append(ConnexionProperties.DBMS).append("://").append(ConnexionProperties.SERVER_NAME).append(":").append(ConnexionProperties.PORT_NUMBER).append("/").append(ConnexionProperties.DB_NAME).toString();
            cpds.setDriverClass("org.apache.postgresql.jdbc.Driver");
            cpds.setUser(ConnexionProperties.USER_NAME);
            cpds.setPassword(ConnexionProperties.PASSWORD);
        }
        cpds.setJdbcUrl(url);
//        cpds.setMinPoolSize(ConnexionProperties.MIN_POOL_SIZE);
//        cpds.setAcquireIncrement(ConnexionProperties.ACQUIRE_INCREMENT);
//        cpds.setMaxPoolSize(ConnexionProperties.MAX_POOL_SIZE);
        return cpds;
    }

    public static boolean close()
    {
        try
        {
            conn.close();
            LOGGER.info("connection closed.");
        }
        catch(SQLException e)
        {
            LOGGER.error("Error while closing connection",e);
            return false;
        }
        return true;
    }

    private static ConnexionFactory connFactory = null;
    private static Connection conn = null;
    private ComboPooledDataSource dataSource;

}
