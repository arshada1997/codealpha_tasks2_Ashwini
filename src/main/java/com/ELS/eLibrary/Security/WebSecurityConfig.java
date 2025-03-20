package com.ELS.eLibrary.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = false)
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .sessionManagement((sessions) -> sessions
                        .requireExplicitAuthenticationStrategy(false))

                .authorizeHttpRequests((requests) -> requests
                        // .requestMatchers("/db-console/**").permitAll()

                        .requestMatchers("/**", "/login", "/register",
                                "/css/**",
                                "/fonts/**",
                                "/images/**",
                                "/js/**")
                        .permitAll()
                        // .anyRequest().authenticated()
                    //    .requestMatchers("/profile/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/librarian/**").hasRole("LIBRARIAN"))
                         
                .formLogin(formLogin -> formLogin
                        .loginPage("/")
                        .loginProcessingUrl("/")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(authenticationSuccessHandler()).permitAll()

                        .failureUrl("/?error")
                        
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/?logout") // Redirect URL after successful logout
                        .invalidateHttpSession(true) // Invalidate session
                        .deleteCookies("JSESSIONID") // Delete session cookies
                        .addLogoutHandler(new CustomLogoutHandler()) // Add custom handler

                        .permitAll())
                .rememberMe((rememberMe) -> rememberMe
                        .key("remember-me") // Replace with a strong secret key
                        .tokenValiditySeconds(86400 * 30) // 30 days
                )

                /*
                 * .csrf(csrf -> csrf
                 * .ignoringRequestMatchers("/db-console/**")
                 * // .disable() // Disable CSRF for H2 console
                 * )
                 */ .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable() // Disable X-Frame-Options for H2 console
                        )
                // .rememberMe().rememberMeParameter("remember-me")

                );

        return http.build();
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
