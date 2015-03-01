package com.t1m0.exmple.microservice.frontend;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.security.Http401AuthenticationEntryPoint;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends OAuth2SsoConfigurerAdapter {

 @Override
 public void configure(final HttpSecurity http) throws Exception {
  http.logout().and().exceptionHandling()
  .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Session realm=\"JSESSIONID\"")).and()
  .antMatcher("/**").authorizeRequests().antMatchers("/index.html", "/home.html", "/", "/login").permitAll()
  .anyRequest().authenticated().and().csrf().csrfTokenRepository(csrfTokenRepository()).and()
  .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
 }

 private CsrfTokenRepository csrfTokenRepository() {
  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
  repository.setHeaderName("X-XSRF-TOKEN");
  return repository;
 }
}