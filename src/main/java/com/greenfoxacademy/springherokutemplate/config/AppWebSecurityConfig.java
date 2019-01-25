package com.greenfoxacademy.springherokutemplate.config;

import com.greenfoxacademy.springherokutemplate.model.security.KnownAuthorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class AppWebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from app_user " +
                        "where username=?")
                .authoritiesByUsernameQuery(
                        "select app_user.username, app_user_authority.authority " +
                        "from app_user_authority inner join " +
                        "app_user on app_user_authority.id = app_user.id " +
                        "where username=?")
                .passwordEncoder(encoder())
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/home/**", "/book/**", "/profile/**")
                    .hasAuthority(KnownAuthorities.ROLE_USER)
                .antMatchers("/manage/**")
                    .hasAuthority(KnownAuthorities.ROLE_MANAGER)
                .antMatchers("/", "/search", "/search/**", "/basicsearch/**")
                    .permitAll()
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/basicsearch/calendars")
            .and()
                .logout()
        ;
    }
}


