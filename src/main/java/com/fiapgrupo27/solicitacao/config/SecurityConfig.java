package com.fiapgrupo27.solicitacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    private final Environment environment;

    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (environment.acceptsProfiles("test")) { // 🔥 Verifica se está rodando no perfil de testes
            http
                    .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // 🔥 Libera todos os endpoints
                    .csrf(csrf -> csrf.disable()); // 🔥 Desativa CSRF para evitar bloqueios nos testes
        } else {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/public/**").permitAll() // Permite acesso a endpoints públicos
                            .requestMatchers("/api/private").authenticated() // Protege GET
                            .requestMatchers("/api/admin").hasRole("ADMIN") // Exemplo de endpoint protegido por role
                            .requestMatchers(HttpMethod.PUT, "/**").permitAll() // Permite todos os PUTs
                            .anyRequest().authenticated() // Protege os outros endpoints
                    )
                    .csrf(csrf -> csrf.disable()) // 🔥 Desativa CSRF para permitir POST, PUT, DELETE
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                    )
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        }

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("cognito:groups"); // Usa os grupos do Cognito como roles

        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }
}