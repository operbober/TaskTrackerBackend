package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Entity
public class Role extends NamedEntity implements GrantedAuthority {
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_DEVELOPER = "ROLE_DEVELOPER";

    @JsonIgnore
    @Override
    public String getAuthority() {
        return getName();
    }

    protected Role() {}

    public Role(String name) {
        super(name);
    }
}
