package je.jdbc.dao;

import je.jdbc.entity.Flight;
import je.jdbc.entity.Flight;
import je.jdbc.entity.FlightStatus;
import je.jdbc.entity.Ticket;
import je.jdbc.exception.DaoException;
import je.jdbc.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private final static FlightDao INSTANCE = new FlightDao();

    private static final String FIND_ALL_SQL =
//            "SELECT * FROM flight";
            "SELECT id, flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status " +
            "FROM flight";






    @Override
    public boolean update(Flight flight) {
        return false;
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> Flights = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Flights.add(buildFlight(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Flights;
    }

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL +
//            "SELECT * FROM flight";
            " WHERE id = ?";


    @Override
    public Optional<Flight> findById(Long id) {
        Flight flight = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                flight = buildFlight(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public Flight save(Flight flight) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
    private static Flight buildFlight(ResultSet result) throws SQLException {
        return new Flight(result.getLong("id"),
                result.getString("flight_no"),
                result.getTimestamp("departure_date").toLocalDateTime(),
                result.getString("departure_airport_code"),
                result.getTimestamp("arrival_date").toLocalDateTime(),
                result.getString("arrival_airport_code"),
                result.getInt("aircraft_id"),
                FlightStatus.valueOf(result.getString("status"))
        );
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }
    private FlightDao() {
    }
}
