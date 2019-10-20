package com.dombi.advanced.todomvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll() // allowed by anyone
                .antMatchers("/").permitAll() // allowed by anyone
                //.antMatchers("/public/**").permitAll() // allowed by anyone
                .antMatchers("/js/**").permitAll() // allowed by anyone
                .antMatchers("/css/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.POST, "/list/**").authenticated() // allowed only when signed in
                .antMatchers(HttpMethod.POST, "/todos/**").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .antMatchers(HttpMethod.PUT, "/todos/**").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .antMatchers(HttpMethod.DELETE, "/todos/**").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .antMatchers(HttpMethod.GET, "/todos/**").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .antMatchers(HttpMethod.POST, "/addTodo").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .anyRequest().denyAll() // anything else is denied
        .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);
    }
}