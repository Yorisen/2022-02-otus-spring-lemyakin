package ru.otus.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.otus.homework.domain.User;
import ru.otus.homework.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers( "/img/**")
                .antMatchers( "/css/**" );
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
            .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .logout().permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        List<User> users = userService.findAll();
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authentication =
                auth.inMemoryAuthentication();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        for (User user: users) {
            String encodedPassword = encoder.encode(user.getPassword());
            authentication.withUser(user.getLogin())
                    .password(encodedPassword)
                    .roles(user.getRole());
        }
    }
}
