package com.springbootquiz.security;

import com.springbootquiz.model.Role;
import com.springbootquiz.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${authentication.internal-api-key}")
    private String internalApiKey;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll()
                .antMatchers("/api/user/resetpassword").permitAll()
                .antMatchers(HttpMethod.GET, "/api/category/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/quiz/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/question/**").permitAll()
                .antMatchers( "/api/user/**").hasAnyRole(Role.ADMIN.name(),Role.SUPER_ADMIN.name())
                .antMatchers("/api/category/**").hasAnyRole(Role.ADMIN.name(),Role.SUPER_ADMIN.name())
                .antMatchers("/api/quiz/**").hasRole(Role.ADMIN.name())
                .antMatchers("/api/question/**").hasRole(Role.ADMIN.name())//crud quiz admin
                .antMatchers("/api/makeadmin/**").hasRole(Role.SUPER_ADMIN.name()) //update role for user to admin
                .antMatchers("/api/internal/**").hasRole(Role.SYSTEM_MANAGER.name()) //update role for admin to superadmin by secret api
                .anyRequest().authenticated();

        //jwt filter
        //internal > jwt > authentication
        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(internalApiAuthenticationFilter(), JwtAuthorizationFilter.class);
    }

    @Bean
    public InternalApiAuthenticationFilter internalApiAuthenticationFilter() {
        return new InternalApiAuthenticationFilter(internalApiKey);
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
}
