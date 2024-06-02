package com.example.FBJV24001114synrgy7josBinarFudch5.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.util.Arrays;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true) //secure definition
public class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final String[] ADMIN_ROLE = new String[]{
            "/merchants/**",
            "/products/**"
    };

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/user-register/**", "/auth/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/merchants/**", "/products/**")
                .permitAll()
                .antMatchers("/orders/**").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST, ADMIN_ROLE).hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT, ADMIN_ROLE).hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PATCH, ADMIN_ROLE).hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, ADMIN_ROLE).hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
        ;
    }
}


