package com.example.Blindajes.config;

import com.example.Blindajes.security.CorsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.Blindajes.model.Role.*;

//@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
//@EnableMethodSecurity // -> Habilita utilizar PreAuthorize en los controllers.
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CorsConfig corsConfig;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsConfig.corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeHttpRequestCustomizer -> authorizeHttpRequestCustomizer
                .requestMatchers(HttpMethod.POST, "/auth/register/").hasAnyAuthority(ADMIN.name(), ENGINEER.name())

                .requestMatchers(HttpMethod.POST, "/vehicle/**").hasAnyAuthority(CICO_LOGISTIC.name(), ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.PUT, "/vehicle/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.DELETE, "/vehicle/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers("/vehicle/**").authenticated()

                .requestMatchers(HttpMethod.POST, "/client/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.PUT, "/client/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.DELETE, "/client/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers("/client/**").authenticated()

                .requestMatchers(HttpMethod.POST, "/brand/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.PUT, "/brand/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.DELETE, "/brand/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers("/brand/**").authenticated()

                .requestMatchers(HttpMethod.POST, "/model/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.PUT, "/model/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.DELETE, "/model/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers("/model/**").authenticated()

                .requestMatchers(HttpMethod.POST, "/workGroups/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.PUT, "/workGroups/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.DELETE, "/workGroups/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers("/workGroups/**").authenticated()

                .requestMatchers(HttpMethod.POST, "/vehicleQualityControl/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name(), QUALITY_CONTROL.name(), CICO_LOGISTIC.name())
                .requestMatchers(HttpMethod.PUT, "/vehicleQualityControl/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers(HttpMethod.DELETE, "/vehicleQualityControl/**").hasAnyAuthority(ADMIN.name(), ENGINEER.name())
                .requestMatchers("/vehicleQualityControl/**").authenticated()

                .anyRequest()
                .permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowedMethods(List.of("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
