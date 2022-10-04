package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.configuration;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.service.LibraryUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */

@Configuration
public class LibraryDemoSecurityConfiguration {

    private final LibraryUserDetailService libraryUserDetailService;

    public LibraryDemoSecurityConfiguration(LibraryUserDetailService libraryUserDetailService) {
        this.libraryUserDetailService = libraryUserDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/css/**", "/webjars/**").permitAll()
                        .antMatchers("/", "/book/overview").permitAll()
                        .anyRequest().authenticated()
        )
                .formLogin().and()
                .logout().logoutSuccessUrl("/book/overview");
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(libraryUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
