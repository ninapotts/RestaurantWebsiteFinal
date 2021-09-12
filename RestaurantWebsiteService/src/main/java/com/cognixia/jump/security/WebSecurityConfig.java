package com.example.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.*;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowried
    UserDetailsService userDetailsService;


    @Override
    //temp will be using database
    protected void configure(AuthenicationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user1")
                .passwrod("{noop}pasword1")
                .roles("USER");

        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFActories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.srf().disable()
                .authorizeResquests()
                .antMatchers()
                /* needs to change for our models
                .antMatchers("/api/admin").hasAnyRole("ADMIN")
                .antMatchers("/api/useraccess").hasRole("USER")
                .antMatchers("/api/all").permitAll()
                .antMatchers("/error"). permitallo)
                .antMatchers(HttpMethod.GET, "/hello/*").hasRole("USER")
                .antMatchers (HttpMethod.GET, "/date/year").hasAuthority("ADMIN");*/
                .antMatchers("/api/useraccess").hasRole("USER","ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .and().formLogin()
                .and().httpBasic()


    }
}