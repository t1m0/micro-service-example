package com.t1m0.exmple.microservice.security;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
}
