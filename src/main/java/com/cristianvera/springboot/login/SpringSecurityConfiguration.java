package com.cristianvera.springboot.login;

import com.cristianvera.springboot.login.auth.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final LoginSuccessHandler loginSuccessHandler;

    public SpringSecurityConfiguration(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/home/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(loginSuccessHandler)
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/error-403");
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //authenticationManagerBuilder.userDetailsService()
    }
}
