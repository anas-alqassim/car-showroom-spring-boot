package com.elm.challenge.carshowroom.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${keycloak.client}")
    private String client;

    private static final String[] openEndpoints = {
            "/actuator/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    @Profile("!prod")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(corsSpec -> corsSpec.configurationSource(exchange -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
                    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                    return corsConfiguration;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(openEndpoints).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(new KeycloakRealmRoleConverter(client))
                        )
                )
                .build();
    }

    public static class KeycloakRealmRoleConverter extends JwtAuthenticationConverter {

        public KeycloakRealmRoleConverter(String client) {
            setJwtGrantedAuthoritiesConverter(jwt -> {
                Map<String, Object> claims = jwt.getClaims();
                Object resourceAccessObj = claims.get("resource_access");
                if (resourceAccessObj instanceof Map<?, ?> resourceAccess) {
                    return resourceAccess.entrySet().stream()
                            .filter(entry -> client.equals(entry.getKey()))
                            .map(Map.Entry::getValue)
                            .filter(value -> value instanceof Map)
                            .map(value -> (Map<?, ?>) value)
                            .flatMap(map -> {
                                Object rolesObj = map.get("roles");
                                if (rolesObj instanceof List<?> roles) {
                                    return roles.stream();
                                } else {
                                    return Stream.empty();
                                }
                            })
                            .map(role -> new SimpleGrantedAuthority("ROLE_"+role.toString())
                            )
                            .collect(Collectors.toList());
                } else {
                    return Collections.emptyList();
                }
            });
        }
    }
}

