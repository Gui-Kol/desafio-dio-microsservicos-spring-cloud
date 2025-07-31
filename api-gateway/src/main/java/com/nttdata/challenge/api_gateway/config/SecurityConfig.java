// src/main/java/com/nttdata/challenge/apigateway/config/SecurityConfig.java
package com.nttdata.challenge.api_gateway.config; // Mantenha o pacote correto do seu projeto

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final String FIXED_TOKEN = "secret-token"; // Token fixo. Confirme que está exatamente como você envia.

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(authenticationManager());
        authenticationWebFilter.setServerAuthenticationConverter(bearerTokenAuthenticationConverter());

        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/eureka/**").permitAll() // Permite acesso ao Eureka sem autenticação
                .anyExchange().authenticated() // Todas as outras rotas exigem autenticação
            )
            .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint((swe, e) ->
                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                )
                .accessDeniedHandler((swe, e) ->
                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                )
            );
        return http.build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return authentication -> {
            // **AQUI ESTÁ A CORREÇÃO**: 'rawToken' JÁ É o token puro, extraído pelo converter.
            // Não precisamos verificar 'startsWith("Bearer ")' novamente.
            String rawToken = authentication.getCredentials().toString();

            if (FIXED_TOKEN.equals(rawToken)) { // Compara o token puro recebido com o token fixo
                return Mono.just(new UsernamePasswordAuthenticationToken(
                        "user", // Principal
                        null,   // Credenciais (já validadas, não precisa mais)
                        List.of() // Autoridades/Roles
                ));
            }
            // Se o token não corresponder, retorne um erro.
            return Mono.error(new org.springframework.security.core.AuthenticationException("Token inválido ou ausente") {});
        };
    }

    @Bean
    public ServerAuthenticationConverter bearerTokenAuthenticationConverter() {
        return exchange -> {
            String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // Extrai APENAS o token, sem o prefixo "Bearer "
                String token = authorizationHeader.substring(7);
                // Passa o token puro (ex: "secret-token") como credencial para o authenticationManager
                return Mono.just(new UsernamePasswordAuthenticationToken(null, token));
            }
            return Mono.empty(); // Se não for um token Bearer, retorne vazio.
        };
    }
}