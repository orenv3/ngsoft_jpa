package com.demo.ngsoft.configurations;

import com.demo.ngsoft.security.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final AuthFilter authFilter;
    private final AuthenticationProvider authProvider;

    @Bean // SecurityFilterChain is responsible/config for all http security of our APP
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(req->  req.getRequestURI().contains("swagger-ui"))
                .permitAll()
                .requestMatchers(req->  req.getRequestURI().contains("api-docs"))
                .permitAll()
                .requestMatchers(req->  req.getRequestURI().contains("/auth/login"))
                .permitAll()
                .requestMatchers(req->  req.getRequestURI().contains("/user/")).hasAuthority("USER")
                .requestMatchers(req-> req.getRequestURI().contains("/admin/")).hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //AuthenticationProvider --> Data access object which responsible to fetch user details/encode password etc.
                // an Authentication request is processed by an AuthenticationProvider,
                .authenticationProvider(authProvider)
                //UsernamePasswordAuthenticationFilter  by default responds to the URL /login. Therefor authFilter need to be first
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}
