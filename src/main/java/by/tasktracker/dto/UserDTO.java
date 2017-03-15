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

    @NotNull(message = "email may not be null")
    @Email
    private String email;

    @NotNull(message = "name may not be null")
    @Size(min = 4, max = 16)
    private String name;

    @NotNull(message = "password may not be null")
    @Size(min = 8, max = 25)
    private String password;

    @NotNull(message = "confirmPassword may not be null")
    @Size(min = 8, max = 25)
    private String confirmPassword;

    @AssertTrue(message="password field should be equal confirmPassword field")
    private boolean isValid() {
        return this.password.equals(this.confirmPassword);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}