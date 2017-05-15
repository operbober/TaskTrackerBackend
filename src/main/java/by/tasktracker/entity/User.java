package by.tasktracker.entity;

import by.tasktracker.entity.superclass.CommonEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.UUID;


@Entity
public class User extends CommonEntity {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 4, max = 16)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date signUpDate;

    @JsonIgnore
    private String activationCode;

    @JsonIgnore
    private String editCode;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private Set<Project> projects;

    private User(){
        signUpDate = new Date();
        activationCode = UUID.randomUUID().toString();
    }

    public User(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(User user) {
        this(user.getName(), user.getEmail(), user.getPassword());
        setId(user.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCodeNull() {
        activationCode = null;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public String getEditCode() {
        return editCode;
    }

    public void setEditCode(String editCode) {
        this.editCode = editCode;
    }
}
