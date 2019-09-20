package com.udacity.vehicles.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**",
            // cars api
            "/cars", "/cars/**" };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().antMatchers("/**/*")
                .denyAll();
    }
}