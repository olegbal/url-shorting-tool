package com.github.olegbal.urlshortingtool.security;

import com.github.olegbal.urlshortingtool.security.filters.StatelessAuthenticationFilter;
import com.github.olegbal.urlshortingtool.services.UserService;
import com.github.olegbal.urlshortingtool.services.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

//FIXME @ENABLE WEB SEC also has @configuration, so, it is redundant
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final TokenAuthenticationService tokenAuthenticationService;

    //TODO Get it from properties
    private final String secret = "MY_SECRET_TSSS";

    //FIXME make it better
    public SpringSecurityConfig(@Qualifier("customUserService") @Autowired UserService userService) {
        super(true);
        this.userService = userService;

        //FIXME
        tokenAuthenticationService = new TokenAuthenticationService(secret, this.userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .headers().and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/**.html").permitAll()
                .antMatchers("/**.css").permitAll()
                .antMatchers("/**.js").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/links/{\\d+}").hasRole("USER")
                .antMatchers("/api/v1/account**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/login", "/api/v1/register", "/api/v1/links", "/api/v1/links?tag={tag}",
                        "/api/v1/shortlinks/{value}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/links/{\\d+}").permitAll()
                .antMatchers("/api/v1/users").hasRole("ADMIN")
                .antMatchers("/api/v1/user/{\\d+}").hasRole("ADMIN")
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
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }
}
