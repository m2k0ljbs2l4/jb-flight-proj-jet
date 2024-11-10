package je.jdbc.validator;


import je.jdbc.dto.CreateUserDto;
import je.jdbc.entity.Gender;
import je.jdbc.entity.Role;
import je.jdbc.utils.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    public ValidationResult isValid(CreateUserDto userDto) {
        ValidationResult validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(userDto.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }
        if (Gender.find(userDto.getGender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        if (Role.find(userDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        return validationResult;

    }


    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
