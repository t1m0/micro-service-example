package com.t1m0.exmple.microservice.resource.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.t1m0.exmple.microservice.resource.domain.ToDo;

@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface ToDoRepository extends PagingAndSortingRepository<ToDo, String> {
}
