package by.tasktracker.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for sign up
 *
 * Created by malets on 1/9/2017.
 */
public class UserDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 4, max = 16)
    private String name;

    @NotNull
    @Size(min = 8, max = 25)
    private String password;

    @NotNull
    @Size(min = 8, max = 25)
    private String confirmPassword;

    @AssertTrue(message="password field should be equal confirmPassword field")
    private boolean isValid() {
        return this.password.equals(this.confirmPassword);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

}