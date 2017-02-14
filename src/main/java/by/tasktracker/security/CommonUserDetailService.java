package by.tasktracker.security;

import by.tasktracker.entity.User;
import by.tasktracker.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class CommonUserDetailService implements UserDetailsService {

    @Autowired private UserService service;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = service.getByName(name);
        if (user == null) {
            user = service.getByEmail(name);
        }
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", name));
        }
        if (user.getActivationCode() != null) {
            throw new UsernameNotFoundException(String.format("User %s does not activated!", name));
        }
        return new UserRepositoryUserDetails(user);
    }

    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private UserRepositoryUserDetails(User user) {super(user);}

        @JsonIgnore
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return new HashSet<>();
        }

        @JsonIgnore
        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @JsonIgnore
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @JsonIgnore
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @JsonIgnore
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @JsonIgnore
        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
