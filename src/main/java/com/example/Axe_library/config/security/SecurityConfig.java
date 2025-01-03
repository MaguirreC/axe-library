package com.example.Axe_library.config.security;

import com.example.Axe_library.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.
                csrf(csfr->csfr.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sessionManConfig-> sessionManConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeRequests-> {
                    authorizeRequests.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.GET,"/auth/public-access").permitAll();
                    authorizeRequests.requestMatchers("/error").permitAll();

                    authorizeRequests.requestMatchers(HttpMethod.GET,"/books/**").hasAuthority(Permission.CREATE_ONE_CLIENT.name());
                    authorizeRequests.requestMatchers(HttpMethod.POST,"/books").hasAuthority(Permission.SAVE_ONE_BOOK.name());
                    authorizeRequests.requestMatchers(HttpMethod.DELETE,"/books/**").hasAuthority(Permission.CREATE_ONE_CLIENT.name());
                    authorizeRequests.requestMatchers(HttpMethod.POST,"/clients").hasAuthority(Permission.CREATE_ONE_CLIENT.name());
                    authorizeRequests.requestMatchers(HttpMethod.GET,"/clients").hasAuthority(Permission.CREATE_ONE_CLIENT.name());
                    authorizeRequests.requestMatchers(HttpMethod.DELETE,"/clients/**").hasAuthority(Permission.CREATE_ONE_CLIENT.name());
                    authorizeRequests.requestMatchers(HttpMethod.GET,"/loans/**").hasAuthority(Permission.CREATE_ONE_CLIENT.name());
                    authorizeRequests.requestMatchers(HttpMethod.POST,"/loans/**").hasAuthority(Permission.CREATE_ONE_CLIENT.name());
                    authorizeRequests.requestMatchers(HttpMethod.DELETE,"/loans/**").hasAuthority(Permission.CREATE_ONE_CLIENT.name());
                    authorizeRequests.requestMatchers(HttpMethod.PUT,"/loans/**").hasAuthority(Permission.CREATE_ONE_CLIENT.name());

                    authorizeRequests.anyRequest().denyAll();


                });



        return http.build();

    }
    @Bean public CorsConfigurationSource corsConfigurationSource() { CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); return source; }

    }


