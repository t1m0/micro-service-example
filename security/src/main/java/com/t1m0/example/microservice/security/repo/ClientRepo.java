//package com.t1m0.example.microservice.security.repo;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.stereotype.Repository;
//
//import com.t1m0.example.microservice.security.domain.Client;
//
//@Repository
//public interface ClientRepo extends PagingAndSortingRepository<Client, Long>, ClientDetailsService {
// @Override
// @Query("select c from Client c where c.clientId = :id")
// public ClientDetails loadClientByClientId(@Param("id") String clientId);
// }
