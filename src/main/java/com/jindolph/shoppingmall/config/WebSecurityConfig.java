package com.jindolph.shoppingmall.config;

import com.jindolph.shoppingmall.security.JwtAuthenticationFilter;

// import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private final String[] PUBLIC_URLs = { "/home", "/api/**", "/h2-console/**", "/auth/**" };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors() // default cors setting
                .and() //
                .csrf().disable() // no use
                .httpBasic().disable() // token based(bearer) => basic disable
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// no session base
                .and()//
                .authorizeRequests().antMatchers(PUBLIC_URLs).permitAll()//
                .anyRequest().authenticated();

        http.addFilterAfter(jwtAuthenticationFilter, SecurityContextPersistenceFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/built/bundle.js");
    }
}
