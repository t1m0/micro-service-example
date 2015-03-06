package com.t1m0.exmple.microservice.resource.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.t1m0.exmple.microservice.resource.domain.ToDo;

@Component
public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
