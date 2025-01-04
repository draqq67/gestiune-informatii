package com.dragos.gestiune_informatii.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/competitions/add").hasRole("ADMIN")  // Admin restricted pages
                        .requestMatchers("/register", "/login").permitAll()  // Permit register and login pages
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/")  // Redirect to home page after successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Logout URL
                        .logoutSuccessUrl("/")  // Redirect to home page after logout
                );
//                .csrf().disable();  // Disable CSRF for simplicity (Not recommended for production)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
