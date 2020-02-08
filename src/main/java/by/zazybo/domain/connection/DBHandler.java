package by.zazybo.domain.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHandler {

    Connection connection;

    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", DBConfig.dbUser);
        connectionProps.put("password", DBConfig.dbPass);

        conn = DriverManager.getConnection(
        "jdbc:" + DBConfig.dbType + "://" +
                        DBConfig.dbHost +
                        ":" + DBConfig.dbPort + "/"+ DBConfig.dbName,
                connectionProps);
        return conn;
    }

}
