package je.jdbc.dto;

import lombok.*;

//@Getter @Setter
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@AllArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
}
