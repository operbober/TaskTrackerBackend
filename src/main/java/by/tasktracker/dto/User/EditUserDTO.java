package by.tasktracker.dto.User;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;


public class EditUserDTO {

    private String editCode;

    @Email
    private String email;

    @Size(min = 8, max = 25)
    private String password;

    @Size(min = 8, max = 25)
    private String confirmPassword;

    @AssertTrue(message="password field should be equal confirmPassword field")
    private boolean isValid() {
        return (password == null && confirmPassword == null) || (this.password == null || this.password.equals(this.confirmPassword));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getEditCode() {
        return editCode;
    }

    public void setEditCode(String editCode) {
        this.editCode = editCode;
    }
}