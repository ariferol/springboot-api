package tr.com.sample.api.v1.common.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tr.com.sample.api.v1.common.security.exception.CustomAuthEntryPointForJwtException;
import tr.com.sample.api.v1.common.security.filter.AuthRequestFilter;
import tr.com.sample.api.v1.common.security.provider.CustomAuthenticationProvider;

/**
 * @author arif.erol
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfigurer {

    @Value("${api.base-path}")
    private String apiBasePath;

    public SecurityConfigurer() {
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationProvider authenticationProvider() {
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        return authenticationProvider;
    }

    @Bean
    public AuthRequestFilter authRequestFilter() {
        return new AuthRequestFilter();
    }

    @Bean
    public AuthenticationEntryPoint authEntryPointForJwtException() {
        return new CustomAuthEntryPointForJwtException();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
                .ignoringRequestMatchers(apiBasePath + "/v1/auth/**")
                .ignoringRequestMatchers(apiBasePath + "/v1/route/**")
                .ignoringRequestMatchers("/webjars/**")
                .ignoringRequestMatchers(
                    "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/swagger-resources",
                    apiBasePath + "/swagger-ui.html", apiBasePath + "/swagger-ui/**", apiBasePath + "/v3/api-docs", apiBasePath + "/v3/api-docs/**",
                    apiBasePath + "/v3/api-docs/swagger-config",
                    "/favicon.ico"
                )
                .disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointForJwtException()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(apiBasePath + "/v1/auth/**", apiBasePath + "/v1/route/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/webjars/**").permitAll()
                    .requestMatchers(
                        "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/swagger-resources",
                        apiBasePath + "/swagger-ui.html", apiBasePath + "/swagger-ui/**", apiBasePath + "/v3/api-docs", apiBasePath + "/v3/api-docs/**",
                        apiBasePath + "/v3/api-docs/swagger-config",
                        "/favicon.ico"
                    ).permitAll()
                    .anyRequest().authenticated());
        httpSecurity.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.addFilterBefore(authRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
