package je.jdbc.dao;

import je.jdbc.entity.Ticket;
import je.jdbc.exception.DaoException;
import je.jdbc.utils.ConnectionManager;

import java.sql.*;

public class TicketDao {
    private final static TicketDao INSTANCE = new TicketDao();

    private final static String SAVE_SQL =
            "INSERT INTO ticket" +
            "(passenger_no, passenger_name, flight_id, seat_no, cost) " +
            "VALUES (?, ?, ?, ?, ?)";

    public Ticket save(Ticket ticket) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, ticket.getPassangerNo());
            statement.setString(2, ticket.getPassangerName());
            statement.setLong(3, ticket.getFlightId());
            statement.setString(4, ticket.getSeatNo());
            statement.setBigDecimal(5, ticket.getCost());

            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                ticket.setId(keys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return ticket;
    }

    private static final String DELETE_SQL =
            "DELETE FROM ticket " +
            "WHERE id = ?";

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    private TicketDao() {
    }
}
