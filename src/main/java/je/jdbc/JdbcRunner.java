package je.jdbc;

import je.jdbc.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
//        String sql = """
//                    CREATE TABLE info (
//                    id SERIAL PRIMARY KEY,
//                    data varchar(255)
//                );
//                """;

//        String sql = """
//                    INSERT INTO info (data)
//                    VALUES ('TEXT 1'),
//                    ('TEXT 2'),
//                    ('TEXT 3'),
//                    ('TEXT 4'),
//                    ('TEXT 5');
//                """;

//        String sql = """
//        DELETE FROM info;
//        """;
//        String sql = """
//        DROP TABLE info;
//        """;

        String sql = """
            SELECT * FROM ticket;
            """;

        try (Connection connection = ConnectionManager.open();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("passenger_name"));
                System.out.println(resultSet.getLong("id"));
            }
        }

        System.out.println(getTicketsByFlightId(8L));
    }

    public static List<Long> getTicketsByFlightId(Long flightId) {
        List<Long> tickets = new ArrayList<>();
        String sql = """
            SELECT * FROM ticket
            WHERE flight_id = %s;
            """.formatted(flightId);
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                tickets.add(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tickets;
    }

}