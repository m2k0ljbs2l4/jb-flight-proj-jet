package je.jdbc.mapper;

import je.jdbc.dto.CreateUserDto;
import je.jdbc.entity.Gender;
import je.jdbc.entity.Role;
import je.jdbc.entity.User;
import je.jdbc.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateUserMapper implements Mapper<User, CreateUserDto> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFrom(CreateUserDto createUserDto) {
        return User.builder()
                .name(createUserDto.getName())
                .birthday(LocalDateFormatter.format(createUserDto.getBirthday()))
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .role(Role.valueOf(createUserDto.getRole()))
                .gender(Gender.valueOf(createUserDto.getGender()))
                .build();
    }
}
