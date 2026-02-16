package org.gagan.intern_assignment_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/login",
            "/css/**",
            "/images/**",
            "/js/**",
            "/signup",
            "/error"
    };
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.authorizeHttpRequests(auth->{
            auth.requestMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated();
        })
                .formLogin(form->{
                    form.loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/device",true)
                            .permitAll();
                })
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}
