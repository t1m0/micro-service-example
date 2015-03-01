package com.t1m0.exmple.microservice.frontend;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableZuulProxy
@EnableOAuth2Sso
@SpringBootApplication
public class Application {

 public static void main(final String[] args) {
  SpringApplication.run(Application.class, args);
 }

 @RequestMapping("/user")
 public Principal user(final Principal user) {
  return user;
 }

 @RequestMapping("/token")
 @ResponseBody
 public Map<String, String> token(final HttpSession session) {
  return Collections.singletonMap("token", session.getId());
 }

}
