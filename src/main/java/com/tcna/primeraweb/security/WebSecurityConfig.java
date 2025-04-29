package com.tcna.primeraweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration //esta anotación registra Beans en el contenedor de Spring Boot
@EnableWebSecurity //habilitar la seguridad en la app y poder usar la BD sin problemas
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    //AUTENTIFICACION con una BD
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
    }

    //CREAMOS un FILTRO
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/personas").permitAll()
                        .requestMatchers("/personas/nuevo").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/personas/editar/*", "/personas/eliminar/*").hasRole("ADMIN")
                        .anyRequest().authenticated()
        )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/personas", true)
                        .permitAll())
                .logout(l -> l.permitAll())
                .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return httpSecurity.build();
    }

}
