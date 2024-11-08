package je.jdbc;

import je.jdbc.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        DELETE FROM info;
        """;

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            System.out.println(statement.execute(sql));
        }
    }

}