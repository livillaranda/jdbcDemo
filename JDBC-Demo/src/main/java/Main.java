import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        Connection connection = ConnectionManager.connect();

        try {
            System.out.println(connection.getClientInfo().isEmpty());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}