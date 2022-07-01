import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    /* Single reference - "singleton" : when instantiated, always referencing
    the same instance of this ConnectionManager class */

    public static ConnectionManager connectionManager;

    // Connection instance
    public static Connection connection;

    // private Constructor (special Method)
    private ConnectionManager() {

    }

    // private get method : initializes connectionManager instance
    private ConnectionManager getConnectionManager() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }


    public static Connection getConnection() {
        if (connection == null) {
            connection = connect();
        }

        return connection;
    }

    public static Connection connect() {
        try {

            // New 'properties' list with no values
            Properties props = new Properties();

            /* Using FileReader to read contents of '.properties' file. */
            FileReader fileReader = new FileReader("src/main/resources/jdbc.properties");

            /* By loading contents of '.properties' file into a list, access to values
            is possible with the set keys
             */
            props.load(fileReader);

            /*
            Database URL is an address pointing to the database to be used, AKA the
            'JDBC' string. The format of this URL varies between database vendors or
            DBMS.

            For Postgres, the URL consists of the following:
            jdbc:postgresql://hostname:port/databaseName
             */

            StringBuilder sb = new StringBuilder();
            sb.append("jdbc:postgresql://");
            sb.append(props.get("hostname"));
            sb.append(":");
            sb.append(props.get("port"));
            sb.append("/");
            sb.append(props.get("database"));

            /* '.toString' method needs to be called on 'sb' (StringBuilder)
            to make the code more readable.
             */
            String connectionURL = sb.toString();

            String user = String.valueOf(props.get("user"));
            String password = String.valueOf(props.get("password"));

            connection = DriverManager.getConnection(connectionURL, user, password);

            System.out.println(connectionURL.toString());
            System.out.println(connection.toString());

        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }



        return connection;
    }






}