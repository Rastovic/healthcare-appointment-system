package com.main.app.Config;

import com.main.app.Model.Person;
import com.main.app.Services.PersonService;
import com.main.app.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Autowired
 private RoleService roleService;
 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/register", "/doLogin", "/persons/update"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/doLogin", "/css/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/patient/**").hasRole("PATIENT")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
 .loginPage("/login").loginProcessingUrl("/doLogin").defaultSuccessUrl("/default-dashboard-after-login", true).failureUrl("/login?error=true").permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 @Bean
 public UserDetailsService userDetailsService(PersonService personService) {
 return username -> {
 Person person = personService.findByUsername(username);
 if (person == null) {
 throw new UsernameNotFoundException("User not found with username: " + username);
 }

                com.main.app.Model.Role role = roleService.findByRoleId(person.getRoleId());
 if (role == null) {
 throw new UsernameNotFoundException("Role not found for user: " + username);
 }

 return User.builder().username(person.getUserName()).password(person.getPasswordHash()).roles(role.getRoleName()).build();
            };
 }
 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
 return authenticationConfiguration.getAuthenticationManager();
 }
}

