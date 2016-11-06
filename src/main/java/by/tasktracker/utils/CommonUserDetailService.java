package by.tasktracker.utils;

import by.tasktracker.entity.Role;
import by.tasktracker.entity.User;
import by.tasktracker.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Service
public class CommonUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return new UserRepositoryUserDetails(user);
    }

    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private UserRepositoryUserDetails(User user) {
            super(user.getUsername(), user.getPassword(), user.getRole());
            setId(user.getId());
        }

        @JsonIgnore
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return new HashSet<Role>(Collections.singletonList(getRole()));
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
