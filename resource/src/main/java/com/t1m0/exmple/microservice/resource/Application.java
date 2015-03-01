package com.t1m0.exmple.microservice.resource;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.t1m0.exmple.microservice.resource.domain.ToDo;
import com.t1m0.exmple.microservice.resource.persistence.ToDoRepository;

@EnableOAuth2Resource
@SpringBootApplication
public class Application extends RepositoryRestMvcConfiguration {

 public static void main(final String[] args) throws Exception {
  SpringApplication.run(Application.class, args);
 }

 @Autowired
 private ToDoRepository repo;

 @PostConstruct
 private void init() {
  ToDo todo = new ToDo("ToDo", "some text ...");
  repo.save(todo);
 }
}
