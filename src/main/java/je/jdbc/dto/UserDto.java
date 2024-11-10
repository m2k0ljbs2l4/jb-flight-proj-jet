package je.jdbc.dto;

import je.jdbc.entity.Gender;
import je.jdbc.entity.Role;
import lombok.*;

import java.time.LocalDate;

//@Getter @Setter
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@AllArgsConstructor
@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    Role role;
    Gender gender;

}
