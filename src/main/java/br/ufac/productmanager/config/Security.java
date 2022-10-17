package br.ufac.productmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.ufac.productmanager.model.User;

@Configuration
@EnableWebSecurity
public class Security {

    // @Override
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    // protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.cors();
        
        // All requests from /user_info/ are allowed, and other endpoints can only accessed
        // by authenticated users
        http.
            authorizeRequests().
                antMatchers("/user_info/**").permitAll().
                and().
            authorizeRequests().
                antMatchers("/").authenticated();

        // http.authorizeRequests().anyRequest().permitAll();

        http.authenticationProvider(authenticationProvider());
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        
        authProvider.setUserDetailsService(udService());
        authProvider.setPasswordEncoder(passwordEncoder());
    
        return authProvider;
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.authenticationProvider(authProvider());
    // }

    // @Bean
    // public AuthenticationProvider authProvider() {
    //     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //     authProvider.setUserDetailsService(udService());
    //     authProvider.setPasswordEncoder(passwordEncoder());
    //     return authProvider;
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService udService() {
        return new UserProfileService();
    }
    
}
