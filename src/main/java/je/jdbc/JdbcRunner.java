package je.jdbc;

import je.jdbc.utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        try (Connection connection = ConnectionManager.get();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("passenger_name"));
                System.out.println(resultSet.getLong("id"));
            }
        }

        System.out.println(getTicketsByFlightId(8L));
        System.out.println(getTicketsByFlightIdPrepare(8L));

        System.out.println(getFlightsBetween(LocalDate.of(2020, 4, 1).atStartOfDay(),
                LocalDate.of(2020, 8, 1).atStartOfDay()));

        checkMetaData();
    }

    public static List<Long> getTicketsByFlightId(Long flightId) {
        List<Long> tickets = new ArrayList<>();
        String sql = """
                SELECT * FROM ticket
                WHERE flight_id = %s;
                """.formatted(flightId);
        try (Connection connection = ConnectionManager.get();
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

    public static List<Long> getTicketsByFlightIdPrepare(Long flightId) {
        List<Long> tickets = new ArrayList<>();
        String sql = """
                SELECT * FROM ticket
                WHERE flight_id = ?;
                """;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setFetchSize(2);
            statement.setMaxRows(2);
            statement.setQueryTimeout(1);
            statement.setLong(1, flightId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tickets.add(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tickets;
    }


    public static List<Long> getFlightsBetween(LocalDateTime start, LocalDateTime end) {
        List<Long> flights = new ArrayList<>();
        String sql = """
                SELECT * FROM flight
                WHERE departure_date 
                BETWEEN ? AND ?
                """;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, Timestamp.valueOf(start));
            statement.setTimestamp(2, Timestamp.valueOf(end));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                flights.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    public static void checkMetaData() throws SQLException {
        try (Connection connection = ConnectionManager.get()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet tables = databaseMetaData.getCatalogs();
            while (tables.next()) {
                System.out.println(tables.getString(1));
            }
        }
    }

}