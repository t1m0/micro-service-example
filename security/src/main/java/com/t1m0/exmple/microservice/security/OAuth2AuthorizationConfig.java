package com.t1m0.exmple.microservice.security;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

 @Autowired
 private AuthenticationManager authenticationManager;

 @Bean
 public JwtAccessTokenConverter jwtAccessTokenConverter() {
  JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
  KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray())
  .getKeyPair("test");
  converter.setKeyPair(keyPair);
  return converter;
 }

 @Override
 public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
  clients.inMemory().withClient("acme").secret("acmesecret")
  .authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("openid");
 }

 @Override
 public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
  endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
 }

 @Override
 public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
  oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
 }

}