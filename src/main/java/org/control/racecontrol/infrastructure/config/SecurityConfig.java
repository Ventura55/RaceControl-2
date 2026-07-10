package org.control.racecontrol.infrastructure.config;

import org.control.racecontrol.infrastructure.security.RateLimitFilter;
import org.control.racecontrol.infrastructure.security.SecurityAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityAuthenticationFilter securityAuthenticationFilter;
    private final RateLimitFilter rateLimitFilter;

    public SecurityConfig(SecurityAuthenticationFilter securityAuthenticationFilter, RateLimitFilter rateLimitFilter) {
        this.securityAuthenticationFilter = securityAuthenticationFilter;
        this.rateLimitFilter = rateLimitFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/races/*/insights").hasAnyRole("MEDIA", "STEWARD")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/penalty/**").hasRole("STEWARD")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(rateLimitFilter, SecurityAuthenticationFilter.class);

        return http.build();
    }
}
