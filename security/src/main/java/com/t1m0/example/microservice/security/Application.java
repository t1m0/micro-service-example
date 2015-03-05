package com.t1m0.example.microservice.security;

import java.security.Principal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.t1m0.example.microservice.security.domain.User;
import com.t1m0.example.microservice.security.repo.UserRepo;

@Controller
@SpringBootApplication
@SessionAttributes("authorizationRequest")
public class Application extends WebMvcConfigurerAdapter {

 @RequestMapping("/user")
 @ResponseBody
 public Principal user(final Principal user) {
  return user;
 }

 @Override
 public void addViewControllers(final ViewControllerRegistry registry) {
  registry.addViewController("/login").setViewName("login");
  registry.addViewController("/oauth/confirm_access").setViewName("authorize");
 }

 public static void main(final String[] args) {
  SpringApplication.run(Application.class, args);
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder(16);
 }

 @Autowired
 private UserRepo repo;

 @PostConstruct
 private void initalLoad() {
  String password = passwordEncoder().encode("user");
  repo.save(new User("user", password, User.Role.ROLE_USER));
 }
}
