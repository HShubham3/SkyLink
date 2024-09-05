package com.scm.skylink.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SkyLinkSecurityConfig {

    @Bean
    SecurityFilterChain dSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll())
                .formLogin(flc -> flc.loginPage("/login")
                        .loginProcessingUrl("/authenticateUser")
                        .usernameParameter("email")
                        .passwordParameter("pwd")
                        .defaultSuccessUrl("/user/dashboard")
                        .failureForwardUrl("/login?error=true"))
                .logout(loc -> loc.logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/login?logout=true"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
