package com.cgm.config;

import com.cgm.config.authentication.CgmAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    CgmAuthenticationService cgmAuthenticationService;

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        auth.userDetailsService(cgmAuthenticationService).passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/public").permitAll()

                .antMatchers("/drugs").permitAll()
                .antMatchers("/licence/**").permitAll()
                .anyRequest().authenticated().and().formLogin()
				.permitAll();
    }
}
