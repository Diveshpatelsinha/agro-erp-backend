package com.agroerp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * CORS Configuration.
 *
 * CORS (Cross Origin Resource Sharing) is a browser
 * security feature that blocks requests from one
 * domain to another by default.
 *
 * Without this config:
 *  Angular (localhost:4200) → Spring Boot (localhost:8080)
 *  Browser blocks the request with CORS error.
 *
 * With this config:
 *  We tell the browser it is safe to allow Angular
 *  to call our Spring Boot API.
 *
 * In production replace localhost:4200 with
 * your actual deployed Angular domain.
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        // allowed origins
        // Angular dev server and production build
        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://localhost:80"
        ));

        // allowed HTTP methods
        config.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "PATCH",
                "OPTIONS"
        ));

        // allow all headers including Authorization
        // Authorization header carries the JWT token
        config.setAllowedHeaders(List.of("*"));

        // allow Authorization header in response
        // needed so Angular can read the JWT token
        config.setExposedHeaders(List.of(
                "Authorization"
        ));

        // allow cookies and Authorization headers
        // required for JWT to work correctly
        config.setAllowCredentials(true);

        // cache preflight response for 1 hour
        // reduces OPTIONS requests from browser
        config.setMaxAge(3600L);

        // apply this CORS config to all endpoints
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}