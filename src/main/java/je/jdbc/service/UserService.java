package je.jdbc.service;

import je.jdbc.dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();

    public Integer create(CreateUserDto createUserDto) {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }

}
