package com.github.olegbal.urlshortingtool.security;

import com.github.olegbal.urlshortingtool.filters.StatelessAuthenticationFilter;
import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import com.github.olegbal.urlshortingtool.services.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;
    private final TokenAuthenticationService tokenAuthenticationService;


    private final String secret = "MY_SECRET_TSSS";

    public SpringSecurityConfig() {
        super(true);
        this.userService = new UserServiceImpl();
        tokenAuthenticationService = new TokenAuthenticationService(secret, userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .headers().cacheControl().and()
                .authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/**.html").permitAll()
                .antMatchers("/**.css").permitAll()
                .antMatchers("/**.js").permitAll()

//                .antMatchers("/api/v1/links?userId=**").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/api/v1/links/{id}").hasRole("USER")
//                .antMatchers("/api/v1/account**").hasRole("USER")

                .antMatchers("/api/v1/login", "/api/v1/register", "/api/v1/links", "/api/v1/links?tag={tag}",
                        "/api/v1/shortlinks/{value}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/links/{id}").permitAll()

                .antMatchers(HttpMethod.DELETE, "/api/v1/user/{id}").hasRole("ADMIN")

                .antMatchers("/api/v1/users").hasRole("ADMIN")

                .anyRequest().authenticated().and()

                .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserServiceImpl userDetailsService() {
        return userService;
    }

    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }
}
