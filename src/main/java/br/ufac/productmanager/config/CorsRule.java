package br.ufac.productmanager.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsRule {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration configCors = new CorsConfiguration();
        configCors.setAllowCredentials(true);
        // Front End URL
        configCors.setAllowedOrigins(Arrays.asList("*"));
        configCors.setAllowedMethods(Arrays.asList("*"));
        configCors.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", configCors);

        return new CorsFilter(configSource);

    }
    
}
