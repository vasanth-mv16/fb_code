package com.facebook.DAOConnection;

import com.facebook.customException.WrongFileNameException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Provides a static method to retrieve a database connection using the postgreSQL driver
 *
 * @author vasanth
 * @version 1.1
 */
public class DatabaseAccessConnection {
    private static DatabaseAccessConnection connection;
    private static final Integer MAX_POOL_SIZE = 10;
    private static BlockingQueue<Connection> connectionPool;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private DatabaseAccessConnection() {
        connectionPool = new ArrayBlockingQueue<>(MAX_POOL_SIZE);

        createPool();
    }

    /**
     * <p>
     * Gets the instance of JDBC connection service
     * </p>
     *
     * @return Returns the instance of the JDBC connection class
     */
    public static DatabaseAccessConnection getInstance() {
        return null == connection ? connection = new DatabaseAccessConnection() : connection;
    }

    /**
     * <p>
     * Creates a connection pool by obtaining and adding connections to the pool
     * </p>
     */
    private static void createPool() {
        try {

            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                final Connection connection = getConnection();

                if (null != connection) {
                    connectionPool.offer(connection);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * <p>
     * Retrieves a connection using the postgreSQL driver
     * </p>
     *
     * @return Returns connection object
     */
    public static Connection getConnection() throws WrongFileNameException {
        try {
            final String filePath = "C:\\Users\\maria\\IdeaProjects\\maven_project\\facebook\\src\\test\\resources\\db.properties";
            final FileInputStream fileInputStream = new FileInputStream(filePath);
            final Properties properties = new Properties();

            properties.load(fileInputStream);
            final String SQL_URL = properties.getProperty("SQL_URL");
            final String USER_NAME = properties.getProperty("USER_NAME");
            final String PASSWORD = properties.getProperty("PASSWORD");

            return DriverManager.getConnection(SQL_URL, USER_NAME, PASSWORD);
        } catch (Exception exception) {
            throw new WrongFileNameException("File Not Found, Check it out");
        }
    }

    /**
     * <p>
     * Retrieves a connection from the connection pool
     * </p>
     *
     * @return Connection object retrieved from the connection pool, or null if an error occurs
     */
    public Connection get() {
        try {
            return connectionPool.take();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static void releaseConnection(final Connection connection) {
        if (null != connection) {
            connectionPool.offer(connection);
        }
    }

}


