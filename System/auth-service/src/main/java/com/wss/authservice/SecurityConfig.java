package com.wss.authservice;

import com.wss.authservice.filters.JwtAuthenticationFilter;
import com.wss.authservice.filters.JwtAuthorizationFilter;
import com.wss.authservice.repositories.IAppUserRepository;
import com.wss.authservice.services.UserPrincipalDetailsService;
import com.wss.common.model.EUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
@Configuration
@CrossOrigin(origins="*")
@ComponentScans({
        @ComponentScan(basePackages = "com.wss.authservice.*"),
})
@EnableJpaRepositories(basePackages = {"com.wss.authservice.repositories"})
@EntityScan(basePackages = {"com.wss.authservice.entity"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private UserPrincipalDetailsService userPrincipalDetailsService;
    private IAppUserRepository IAppUserRepository;

    public SecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService, IAppUserRepository IAppUserRepository){
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.IAppUserRepository = IAppUserRepository;

    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        // we don't need CSRF because our token is invulnerable
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.IAppUserRepository))

                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                //allow anonymous auth requests
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/appUser/**").permitAll()
//                .antMatchers("/appUser/**").hasAnyRole(EUserRole.ADMIN.getCode())

                //allow requests
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/buyers/**").permitAll()
                .antMatchers(HttpMethod.POST, "/sellers/**").permitAll()
                .antMatchers("/test/images/**").permitAll()
                .antMatchers("/roles-permissions-setup/init/**").permitAll()


                //authenticated requests
                .anyRequest().authenticated();;

//                cors()
//                .and()
//                // remove csrf and state in session because in jwt we do not need them
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                // add jwt filters (1. authentication, 2. authorization)
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
//                .authorizeRequests()
//                // configure access rules
//                .antMatchers("*").permitAll()
//                .anyRequest().permitAll();
////                //allow anonymous auth requests
////                .antMatchers("/auth/**").permitAll()
////                .antMatchers("/ws/**").permitAll()
////                .antMatchers("/login").permitAll()
////                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
////                .antMatchers(HttpMethod.POST, "/api/register").permitAll()
////                .anyRequest().authenticated();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);



        return source;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userPrincipalDetailsService)
                .passwordEncoder(passwordEncoder());
    }




}
