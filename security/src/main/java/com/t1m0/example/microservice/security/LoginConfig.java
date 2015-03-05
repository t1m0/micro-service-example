package com.t1m0.example.microservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.t1m0.example.microservice.security.repo.UserRepo;

@Configuration
@Order(-10)
public class LoginConfig extends WebSecurityConfigurerAdapter {

 @Autowired
 private AuthenticationManager authenticationManager;

 @Override
 protected void configure(final HttpSecurity http) throws Exception {
  // @formatter:off
  http
  .formLogin().loginPage("/login").permitAll()
  .and()
  .requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
  .and()
  .authorizeRequests().anyRequest().authenticated();
  // @formatter:on
 }

 //
 //
 // @Bean
 // public AuthenticationProvider authenticationProvider() {
 // DaoAuthenticationProvider authenticationProvider = new
 // DaoAuthenticationProvider();
 // authenticationProvider.setPasswordEncoder(passwordEncoder());
 // authenticationProvider.setUserDetailsService(repo);
 // return authenticationProvider;
 // }

 @Override
 protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
  auth.parentAuthenticationManager(authenticationManager);
  // auth.authenticationProvider(authenticationProvider());
 }

 @Configuration
 protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  private UserRepo        repo;

  @Autowired
  private PasswordEncoder encoder;

  @Override
  public void init(final AuthenticationManagerBuilder auth) throws Exception {
   auth.userDetailsService(repo).passwordEncoder(encoder);
  }
 }
}