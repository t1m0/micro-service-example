package com.t1m0.exmple.microservice.resource;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.t1m0.exmple.microservice.resource.domain.ToDo;
import com.t1m0.exmple.microservice.resource.persistence.ToDoRepository;

@RestController
@EnableOAuth2Resource
@SpringBootApplication
public class Application {

 public static void main(final String[] args) throws Exception {
  SpringApplication.run(Application.class, args);
 }

 @Autowired
 private ToDoRepository repo;

 @RequestMapping("/todo")
 public List<ToDo> findAll() {
  return repo.findAll();
 }

 @PostConstruct
 private void init() {
  ToDo todo = new ToDo("ToDo", "some text ...");
  repo.save(todo);
 }

}
