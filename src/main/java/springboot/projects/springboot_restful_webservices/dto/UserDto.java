package springboot.projects.springboot_restful_webservices.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long Id;
    @NotEmpty(message = "firstName cannot be null or empty")
    private String firstName;

    @NotEmpty(message = "lastName cannot be null or empty")
    private String lastName;

    @NotEmpty(message="email cannot be null or empty")
    @Email(message="email should be valid")
    private String email;
}
