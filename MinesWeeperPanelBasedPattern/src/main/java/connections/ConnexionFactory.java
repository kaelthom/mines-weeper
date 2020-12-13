// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnexionFactory.java

package connections;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

// Referenced classes of package connexions:
//            ConnexionProperties

public class ConnexionFactory {

    private static final String JDBC = "jdbc:";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnexionFactory.class);
    private static ConnexionFactory connFactory = null;
    private static Connection conn = null;
    private ComboPooledDataSource dataSource;

    private ConnexionFactory() {
        dataSource = null;
    }

    public static Connection getConnectionInstance()
            throws PropertyVetoException, SQLException {
        if (connFactory == null) {
            connFactory = new ConnexionFactory();
        }
        conn = connFactory.getConnection();
        return conn;
    }

    private static ComboPooledDataSource initDataSource()
            throws PropertyVetoException {
        String url = null;
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        switch (ConnexionProperties.DBMS) {
            case "mysql":
                url = JDBC + ConnexionProperties.DBMS + "://" + ConnexionProperties.SERVER_NAME + ":" + ConnexionProperties.PORT_NUMBER + "/";
                cpds.setDriverClass("org.mysql.Driver");
                cpds.setUser(ConnexionProperties.USER_NAME);
                cpds.setPassword(ConnexionProperties.PASSWORD);
                break;
            case "derby":
                url = JDBC + ConnexionProperties.DBMS + ":" + ConnexionProperties.DB_NAME + ";create=true";
                cpds.setDriverClass("org.apache.derby.jdbc.EmbeddedDriver");
                break;
            case "postgresql":
                url = JDBC + ConnexionProperties.DBMS + "://" + ConnexionProperties.SERVER_NAME + ":" + ConnexionProperties.PORT_NUMBER + "/" + ConnexionProperties.DB_NAME;
                cpds.setDriverClass("org.apache.postgresql.jdbc.Driver");
                cpds.setUser(ConnexionProperties.USER_NAME);
                cpds.setPassword(ConnexionProperties.PASSWORD);
                break;
            default:
                LOGGER.error("Database {} not handled", ConnexionProperties.DBMS);
        }
        cpds.setJdbcUrl(url);
// TODO       cpds.setMinPoolSize(ConnexionProperties.MIN_POOL_SIZE);
// TODO       cpds.setAcquireIncrement(ConnexionProperties.ACQUIRE_INCREMENT);
// TODO       cpds.setMaxPoolSize(ConnexionProperties.MAX_POOL_SIZE);
        return cpds;
    }

    public static void close() {
        try {
            conn.close();
            LOGGER.info("connection closed.");
        } catch (SQLException e) {
            LOGGER.error("Error while closing connection", e);
        }
    }

    private Connection getConnection()
            throws PropertyVetoException, SQLException {
        setDataSource(getDataSource());
        conn = dataSource.getConnection();
        LOGGER.info("Connected to {}", ConnexionProperties.DBMS);
        return conn;
    }

    private ComboPooledDataSource getDataSource()
            throws PropertyVetoException {
        return dataSource != null ? dataSource : initDataSource();
    }

    private void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }


}
