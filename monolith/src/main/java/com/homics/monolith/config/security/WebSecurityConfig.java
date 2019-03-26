package com.homics.monolith.config.security;

import com.homics.monolith.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * Handle Security configuration for the monolith
 *
 * Configure:
 * - HTTP security rules
 * - CORS
 * - Users
 * - password encoder
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserActivityService userActivityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configure Http security rules
        http
                .authorizeRequests().antMatchers(HttpMethod.GET, "/mono/**", "/mono/static/**", "/*.js", "/*.json", "/*.ico").permitAll().and()
                .authorizeRequests().antMatchers("/").hasAnyRole("ADMIN", "USER").and()
                .authorizeRequests().antMatchers("/console/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/mono/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/mono/perform_login")
                .successHandler(new CustomAuthenticationSuccessHandler(userActivityService))
                .failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestApiAuthenticationEntryPoint())
                .and()
                .logout()
                .logoutUrl("/mono/perform_logout")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler(userActivityService))
                .permitAll();

        http.headers().frameOptions().disable();
        http.cors();
        http.csrf().disable();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Configure CORS for our micro-services
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token", "x-xsrf-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token", "x-xsrf-token"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public static PasswordEncoder noOpPasswordEncoder() {
        // Setup the passwordEncoder to stay really simple: return the string directly
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence);
            }
        };
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder noOpPasswordEncoder) throws Exception {
        // Configure our users for the app
        auth.inMemoryAuthentication().withUser("nico").password(noOpPasswordEncoder.encode("nico")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("eric").password(noOpPasswordEncoder.encode("eric")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("beni").password(noOpPasswordEncoder.encode("beni")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password(noOpPasswordEncoder.encode("user")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(noOpPasswordEncoder.encode("admin")).roles("USER");
    }

}
