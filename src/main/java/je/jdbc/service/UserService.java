package je.jdbc.service;

import je.jdbc.dao.UserDao;
import je.jdbc.dto.CreateUserDto;
import je.jdbc.entity.User;
import je.jdbc.mapper.CreateUserMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    public Integer create(CreateUserDto createUserDto) {
        User user = createUserMapper.mapFrom(createUserDto);
        User result = userDao.save(user);
        return result.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

}
