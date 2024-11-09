package je.jdbc.mapper;

import je.jdbc.dto.CreateUserDto;
import je.jdbc.entity.Gender;
import je.jdbc.entity.Role;
import je.jdbc.entity.User;

public class CreateUserMapper implements Mapper<User, CreateUserDto {
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
