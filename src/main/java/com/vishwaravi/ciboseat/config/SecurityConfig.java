package com.vishwaravi.ciboseat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vishwaravi.ciboseat.services.WaitStaffService;

/*
 * Security Configuration For Authentication and Authorization.
 * for User Name Password Authentication using DAO Authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    WaitStaffService waitStaffService;
    @Autowired
    CustomUserDetailService customUserDetailService;

    /**
     * Filter Chain with WHITE LISTED Endpoints and Protected Endpoints.
     * Diabled CSRF for testing Purpose.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/addstaff").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Function for Configuring Authentication Manager With Dao Authentocation Provider.
     * @param userDetailsService - for User Information
     * @param passwordEncoder - BCrypt Password Encoder
     * @return
     */
    @Bean
    AuthenticationManager authenticationManager(@Qualifier("CustomUserDetailService") UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
