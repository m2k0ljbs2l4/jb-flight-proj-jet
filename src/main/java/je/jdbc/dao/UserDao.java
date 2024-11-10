package je.jdbc.dao;

import je.jdbc.entity.User;
import je.jdbc.utils.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
//import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL =
            "INSERT INTO flight_repository.public.users (name, birthday, email, password, role, gender) VALUES (?,?,?,?,?,?,?)";

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
//    @SneakyThrows
    public User save(User user) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement ps = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            ps.setObject(1, user.getName());
            ps.setObject(2, user.getBirthday());
            ps.setObject(3, user.getEmail());
            ps.setObject(4, user.getPassword());
            ps.setObject(5, user.getRole().name());
            ps.setObject(6, user.getGender().name());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getObject("id", Integer.class));

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean delete(Long id) {
        return false;
    }
}
