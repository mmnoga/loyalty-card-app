package pl.careaboutit.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.reactive.function.client.WebClient;
import pl.careaboutit.backend.config.oauth.GoogleOpaqueTokenIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
@RequiredArgsConstructor
public class SecurityConfig {

    private final WebClient userInfoClient;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .exceptionHandling(customizer -> customizer
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/auth/**", "/public/**").permitAll()
                        .requestMatchers("/ws/notifications").permitAll()
                        .requestMatchers("/notifications/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/payu/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/payu/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(customizer -> customizer
                        .opaqueToken(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public OpaqueTokenIntrospector introspector() {
        return new GoogleOpaqueTokenIntrospector(userInfoClient);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
