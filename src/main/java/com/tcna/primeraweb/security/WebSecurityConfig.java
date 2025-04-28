package com.tcna.primeraweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //esta anotación registra Beans en el contenedor de Spring Boot
public class WebSecurityConfig {

    //Registraremos usuarios en memoria (sin BD)
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user1 = User.builder()
                .username("user3")
                .password("{bcrypt}$2a$10$mnCAREL8Vizbm.qn9MxSX.6ULTx4em6TRFH0DCnQqmsW94YUOwWJi")
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("admin3")
                .password("{bcrypt}$2a$10$mnCAREL8Vizbm.qn9MxSX.6ULTx4em6TRFH0DCnQqmsW94YUOwWJi")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }

    //Creamos un FILTRO
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/personas").permitAll()
                        .requestMatchers("/personas/nuevo").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/personas/editar/*", "/personas/eliminar/*").hasRole("ADMIN")
                        .anyRequest().authenticated()
        )
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(e -> e.accessDeniedPage("/403.html"));
        return httpSecurity.build();
    }

}
