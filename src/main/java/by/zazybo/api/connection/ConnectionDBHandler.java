package by.zazybo.api.connection;

import by.zazybo.api.config.DBConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDBHandler {

    public Connection getConnection() throws SQLException {

        Connection connection;
        Properties connectionProps = new Properties();
        connectionProps.put("user", DBConfig.dbUser);
        connectionProps.put("password", DBConfig.dbPass);

        connection = DriverManager.getConnection(
        "jdbc:" + DBConfig.dbType + "://" +
                        DBConfig.dbHost +
                        ":" + DBConfig.dbPort + "/"+ DBConfig.dbName,
                connectionProps);
        return connection;
    }
}
