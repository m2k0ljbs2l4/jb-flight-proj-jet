package je.jdbc.dao;

import je.jdbc.entity.Gender;
import je.jdbc.entity.Role;
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

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
            "SELECT * FROM flight_repository.public.users WHERE email = ? AND password = ?";

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

    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement ps = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = buildEntity(rs);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject( "name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject( "email", String.class))
                .password(resultSet.getObject( "password", String.class))
                .role(Role.find(resultSet.getObject( "role", String.class)).orElse( null))
                .gender (Gender.valueOf(resultSet.getObject( "gender", String.class)))
                .build();
    }





    @Override
    public boolean delete(Long id) {
        return false;
    }
}
