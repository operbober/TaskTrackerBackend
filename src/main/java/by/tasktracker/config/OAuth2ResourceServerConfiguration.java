package by.tasktracker.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/users/me").authenticated()
                .antMatchers(HttpMethod.POST, "/api/projects").hasRole("MANAGER")
                .antMatchers("/api/users/**").hasRole("MANAGER")
                .antMatchers(HttpMethod.PUT, "/api/tasks/switchStatus/**").hasRole("DEVELOPER")
                .antMatchers("/api/comments/**").hasRole("DEVELOPER")
                .antMatchers("/api/**").authenticated();
    }
}
