package com.example.demo.config;

import com.example.demo.dao.TodoUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final TodoUserService todoUserService;

    public SecurityConfig(PasswordEncoder passwordEncoder, TodoUserService todoUserService) {

        this.passwordEncoder = passwordEncoder;
        this.todoUserService = todoUserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/", "/login").permitAll()
//                .antMatchers("/api/todo-user/get-users").hasRole(TodoUserRole.ROLE_ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .httpBasic();

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }

//    @Bean
//    protected UserDetailsService userDetailsService() {
//
//        UserDetails user = User.builder()
//                .username("jelia")
//                .password(passwordEncoder.encode("123"))
//                .roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(todoUserService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(todoUserService);

        return provider;
    }
}
