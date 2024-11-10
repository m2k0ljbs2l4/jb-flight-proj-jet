package je.jdbc.service;

import je.jdbc.dao.UserDao;
import je.jdbc.dto.CreateUserDto;
import je.jdbc.dto.UserDto;
import je.jdbc.entity.User;
import je.jdbc.exception.ValidationException;
import je.jdbc.mapper.UserMapper;
import je.jdbc.validator.CreateUserValidator;

import je.jdbc.mapper.CreateUserMapper;
import je.jdbc.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();


    public Optional<UserDto> login(String email, String password) throws ValidationException {
        return userDao.findByEmailAndPassword(email, password).map(userMapper::mapFrom);
    }


    public Integer create(CreateUserDto createUserDto) {
        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        User user = createUserMapper.mapFrom(createUserDto);
        User result = userDao.save(user);
        return result.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

}
