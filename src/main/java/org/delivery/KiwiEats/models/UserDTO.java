package org.delivery.KiwiEats.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UserDTO {

    private Long id;

    @Length(max = 50)
    private String username;

    @NotNull
    @NotBlank(message = "First name cannot be empty")
    @Length(max = 50)
    private String firstName;

    @Length(max = 40)
    private String middleName;

    @NotNull
    @NotBlank(message = "First name cannot be empty")
    @Length(max = 50)
    private String lastName;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 20, message = "Password does not have correct length")
    @NotNull
    private String password;
}
