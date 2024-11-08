package je.jdbc.dao;

import je.jdbc.entity.Ticket;
import je.jdbc.exception.DaoException;
import je.jdbc.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private static final String FIND_ALL_SQL =
//            "SELECT * FROM ticket";
            "SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost FROM ticket";

    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                tickets.add(
                        buildTicket(result)
                );
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tickets;
    }

    private static Ticket buildTicket(ResultSet result) throws SQLException {
        return new Ticket(result.getLong("id"),
                result.getString("passenger_no"),
                result.getString("passenger_name"),
                result.getLong("flight_id"),
                result.getString("seat_no"),
                result.getBigDecimal("cost")
        );
    }

    private static final String FIND_BY_ID_SQL =
//            "SELECT * FROM ticket";
            "SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost FROM ticket WHERE id = ?;";


    public Optional<Ticket> findById(Long id) {
        Ticket ticket = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ticket = buildTicket(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(ticket);
    }

    private static final String UPDATE_SQL =
            "UPDATE ticket " +
            "SET passenger_no = ?, " +
            "    passenger_name = ?, " +
            "    flight_id = ?," +
            "    seat_no = ?, " +
            "    cost = ? " +
            "WHERE id = ?";

    public boolean update(Ticket ticket) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, ticket.getPassangerNo());
            statement.setString(2, ticket.getPassangerName());
            statement.setLong(3, ticket.getFlightId());
            statement.setString(4, ticket.getSeatNo());
            statement.setBigDecimal(5, ticket.getCost());
            statement.setLong(6, ticket.getId());
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
