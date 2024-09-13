package br.edu.ifpb.padroes.biblioteca.gerenciador.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf((AbstractHttpConfigurer::disable))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuario/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/livro/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/livro/update/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/livro/delete/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/emprestimo/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/emprestimo/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/emprestimo/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/emprestimo/update/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/emprestimo/refund/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/emprestimo/payment/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/emprestimo/delete/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
