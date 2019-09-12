package com.homics.gateway.config.security;

import com.homics.gateway.service.UserActivityService;
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
 * Handle security configuration in the gateway
 * <p>
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
        http
                .authorizeRequests().antMatchers(HttpMethod.GET, "/login*", "/internal/version*", "/versions*", "/crash*", "/static/**", "/*.js", "/*.json", "/*.ico").permitAll().and()
                .authorizeRequests().antMatchers("/**").hasAnyRole("ADMIN", "USER").and()
                .authorizeRequests().antMatchers("/console/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/perform_login")
                .successHandler(new CustomAuthenticationSuccessHandler(userActivityService))
                .failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestApiAuthenticationEntryPoint())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler(userActivityService))
                .permitAll();

        http.headers().frameOptions().disable();
        http.cors();
        http.csrf().disable();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
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
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.contentEquals(charSequence);
            }
        };
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder noOpPasswordEncoder) throws Exception {
        auth.inMemoryAuthentication().withUser("nico").password(noOpPasswordEncoder.encode("nico")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("eric").password(noOpPasswordEncoder.encode("eric")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("beni").password(noOpPasswordEncoder.encode("beni")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("admin").password(noOpPasswordEncoder.encode("admin")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password(noOpPasswordEncoder.encode("user")).roles("USER");
    }
}
