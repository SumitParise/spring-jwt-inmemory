package com.example.security.config;
import com.example.security.security.JWTAthenticationEntryPoint;
import com.example.security.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JWTAthenticationEntryPoint point;  // clearer naming

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // CSRF is disabled as JWT is used, which is stateless
        http.csrf(csrf -> csrf.disable())

                // CORS configuration can be customized instead of being disabled
                .cors(cors -> cors.disable()) // customize this if needed

                // Define authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login/**").permitAll()  // Publicly accessible routes
                        .anyRequest().authenticated()  // All other requests require authentication
                )

                // Exception handling for unauthorized access
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))

                // Stateless session management because we are using JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Adding the JWT authentication filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
return http.build();

    }
}
