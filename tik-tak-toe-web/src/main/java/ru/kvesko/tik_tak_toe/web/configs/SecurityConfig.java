package ru.kvesko.tik_tak_toe.web.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.kvesko.tik_tak_toe.web.configs.auth.PlayerInfoFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.addFilterAfter(new PlayerInfoFilter(), BasicAuthenticationFilter.class)
            .csrf().disable()
            .authorizeHttpRequests()
            .anyRequest()
            .permitAll();
        return http.build();
    }

}
