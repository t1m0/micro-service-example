package com.t1m0.example.microservice.security.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.t1m0.example.microservice.security.domain.User;

@Component
public interface UserRepo extends CrudRepository<User, Long>, UserDetailsService {
 @Override
 @Query("select u from User u where u.username = :name")
 public UserDetails loadUserByUsername(@Param("name") String username);
}
