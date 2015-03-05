package com.t1m0.example.microservice.security.domain;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Timo Schoepflin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * Implementation of {@link ClientDetails}.
 */
@Entity
@Table(name = "tbl_clients")
@SuppressWarnings("serial")
public class Client extends AEntity implements ClientDetails {

 /** The client id identifies the client. */
 @Column(name = "clientid", unique = true, nullable = false)
 private String                       clientId                    = null;

 /**
  * The client secret is used to authenticate the given client. Ignored, if
  * secretrequired is false.
  */
 @Column(name = "clientsecret")
 private String                       clientSecret                = null;

 /**
  * Defines how long the refresh token will be valid (If null the default value
  * used.)
  */
 @Column(name = "accesstokenvalidityseconds")
 private Integer                      accessTokenValiditySeconds  = null;

 /**
  * Defines how long the refresh token will be valid. (If zero or negative the
  * default value used.)
  */
 @Column(name = "refreshtokenvalidityseconds")
 private Integer                      refreshTokenValiditySeconds = 0;

 /** Declares if the client is limited to specific scope. */
 @Column(name = "scoped")
 private boolean                      scoped                      = false;

 /** Declares if the client needs an secret to authenticate. */
 @Column(name = "secretrequired")
 private boolean                      secretRequired              = false;

 /** The scopes assigned to the client. */
 @ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "tbl_client_scopes", joinColumns = @JoinColumn(name = "client_id"))
 @Column(name = "scope")
 private Set<String>                  scope                       = new HashSet<String>();

 /** The authorized grant types assigned to the client. */
 @ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "tbl_client_authorizedgranttypes", joinColumns = @JoinColumn(name = "client_id"))
 @Column(name = "authorizedgranttypes")
 private Set<String>                  authorizedGrantTypes        = new HashSet<String>();

 /**
  * The resources which can be accessed by the client. (May ignored if null)
  */
 @ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "tbl_client_resourceids", joinColumns = @JoinColumn(name = "client_id"))
 @Column(name = "resourceids")
 private Set<String>                  resourceIds                 = new HashSet<String>();

 /**
  * The uris, which the client is redirected to after an successful
  * authentication.
  */
 @ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "tbl_client_registeredredirecturi", joinColumns = @JoinColumn(name = "client_id"))
 @Column(name = "registeredredirecturi")
 private Set<String>                  registeredRedirectUri       = new HashSet<String>();

 /** The authorities assigned to the client. */
 @ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "tbl_client_authorities", joinColumns = @JoinColumn(name = "client_id"))
 @Column(name = "authorities")
 private Collection<GrantedAuthority> authorities                 = new ArrayList<GrantedAuthority>();

 /** Additional information for the client. */
 // @ElementCollection(fetch=FetchType.EAGER)
 // @CollectionTable(name="tbl_client_additionalinformation",
 // joinColumns=@JoinColumn(name="client_id"))
 // @Column(name="additionalinformation")
 @Transient
 private Map<String, Object>          additionalInformation       = new Hashtable<String, Object>();

 /**
  * The Constructor.
  */
 public Client() {
  super();
 }

 /**
  * The Constructor.
  *
  * @param clientId
  *         the client id
  */
 public Client(final String clientId) {
  super();
  this.clientId = clientId;
 }

 /**
  * The Constructor.
  *
  * @param clientId
  *         the client id
  * @param clientSecret
  *         the client secret
  */
 public Client(final String clientId, final String clientSecret) {
  super();
  this.clientId = clientId;
  this.clientSecret = clientSecret;
  secretRequired = true;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public String getClientId() {
  return clientId;
 }

 /**
  * Sets the client id.
  *
  * @param clientId
  *         the client id
  */
 public void setClientId(final String clientId) {
  this.clientId = clientId;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public String getClientSecret() {
  return clientSecret;
 }

 /**
  * Sets the client secret.
  *
  * @param clientSecret
  *         the client secret
  */
 public void setClientSecret(final String clientSecret) {
  this.clientSecret = clientSecret;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public Integer getAccessTokenValiditySeconds() {
  return accessTokenValiditySeconds;
 }

 /**
  * Sets the access token validity seconds.
  *
  * @param accessTokenValiditySeconds
  *         the access token validity seconds
  */
 public void setAccessTokenValiditySeconds(final Integer accessTokenValiditySeconds) {
  this.accessTokenValiditySeconds = accessTokenValiditySeconds;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public Integer getRefreshTokenValiditySeconds() {
  return refreshTokenValiditySeconds;
 }

 /**
  * Sets the refresh token validity seconds.
  *
  * @param refreshTokenValiditySeconds
  *         the refresh token validity seconds
  */
 public void setRefreshTokenValiditySeconds(final Integer refreshTokenValiditySeconds) {
  this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public boolean isScoped() {
  return scoped;
 }

 /**
  * Sets the scoped.
  *
  * @param scoped
  *         the scoped
  */
 public void setScoped(final boolean scoped) {
  this.scoped = scoped;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public boolean isSecretRequired() {
  return secretRequired;
 }

 /**
  * Sets the secret required.
  *
  * @param secretRequired
  *         the secret required
  */
 public void setSecretRequired(final boolean secretRequired) {
  this.secretRequired = secretRequired;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public Set<String> getScope() {
  return scope;
 }

 /**
  * Sets the scope.
  *
  * @param scope
  *         the scope
  */
 public void setScope(final Set<String> scope) {
  this.scope = scope;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public Set<String> getAuthorizedGrantTypes() {
  return authorizedGrantTypes;
 }

 /**
  * Sets the authorized grant types.
  *
  * @param authorizedGrantTypes
  *         the authorized grant types
  */
 public void setAuthorizedGrantTypes(final Set<String> authorizedGrantTypes) {
  this.authorizedGrantTypes = authorizedGrantTypes;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public Set<String> getResourceIds() {
  return resourceIds;
 }

 /**
  * Sets the resource ids.
  *
  * @param resourceIds
  *         the resource ids
  */
 public void setResourceIds(final Set<String> resourceIds) {
  this.resourceIds = resourceIds;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public Set<String> getRegisteredRedirectUri() {
  return registeredRedirectUri;
 }

 /**
  * Sets the registered redirect uri.
  *
  * @param registeredRedirectUri
  *         the registered redirect uri
  */
 public void setRegisteredRedirectUri(final Set<String> registeredRedirectUri) {
  this.registeredRedirectUri = registeredRedirectUri;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public Collection<GrantedAuthority> getAuthorities() {
  return authorities;
 }

 /**
  * Sets the authorities.
  *
  * @param authorities
  *         the authorities
  */
 public void setAuthorities(final Collection<GrantedAuthority> authorities) {
  this.authorities = authorities;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public Map<String, Object> getAdditionalInformation() {
  return additionalInformation;
 }

 /**
  * Sets the additional information.
  *
  * @param additionalInformation
  *         the additional information
  */
 public void setAdditionalInformation(final Map<String, Object> additionalInformation) {
  this.additionalInformation = additionalInformation;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public boolean isAutoApprove(final String scope) {
  return false;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = (prime * result) + ((accessTokenValiditySeconds == null) ? 0 : accessTokenValiditySeconds.hashCode());
  result = (prime * result) + ((additionalInformation == null) ? 0 : additionalInformation.hashCode());
  result = (prime * result) + ((authorities == null) ? 0 : authorities.hashCode());
  result = (prime * result) + ((authorizedGrantTypes == null) ? 0 : authorizedGrantTypes.hashCode());
  result = (prime * result) + ((clientId == null) ? 0 : clientId.hashCode());
  result = (prime * result) + ((clientSecret == null) ? 0 : clientSecret.hashCode());
  result = (prime * result) + ((refreshTokenValiditySeconds == null) ? 0 : refreshTokenValiditySeconds.hashCode());
  result = (prime * result) + ((registeredRedirectUri == null) ? 0 : registeredRedirectUri.hashCode());
  result = (prime * result) + ((resourceIds == null) ? 0 : resourceIds.hashCode());
  result = (prime * result) + ((scope == null) ? 0 : scope.hashCode());
  result = (prime * result) + (scoped ? 1231 : 1237);
  result = (prime * result) + (secretRequired ? 1231 : 1237);
  return result;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public boolean equals(final Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  Client other = (Client) obj;
  if (accessTokenValiditySeconds == null) {
   if (other.accessTokenValiditySeconds != null) {
    return false;
   }
  } else if (!accessTokenValiditySeconds.equals(other.accessTokenValiditySeconds)) {
   return false;
  }
  if (additionalInformation == null) {
   if (other.additionalInformation != null) {
    return false;
   }
  } else if (!additionalInformation.equals(other.additionalInformation)) {
   return false;
  }
  if (authorities == null) {
   if (other.authorities != null) {
    return false;
   }
  } else if (!authorities.equals(other.authorities)) {
   return false;
  }
  if (authorizedGrantTypes == null) {
   if (other.authorizedGrantTypes != null) {
    return false;
   }
  } else if (!authorizedGrantTypes.equals(other.authorizedGrantTypes)) {
   return false;
  }
  if (clientId == null) {
   if (other.clientId != null) {
    return false;
   }
  } else if (!clientId.equals(other.clientId)) {
   return false;
  }
  if (clientSecret == null) {
   if (other.clientSecret != null) {
    return false;
   }
  } else if (!clientSecret.equals(other.clientSecret)) {
   return false;
  }
  if (refreshTokenValiditySeconds == null) {
   if (other.refreshTokenValiditySeconds != null) {
    return false;
   }
  } else if (!refreshTokenValiditySeconds.equals(other.refreshTokenValiditySeconds)) {
   return false;
  }
  if (registeredRedirectUri == null) {
   if (other.registeredRedirectUri != null) {
    return false;
   }
  } else if (!registeredRedirectUri.equals(other.registeredRedirectUri)) {
   return false;
  }
  if (resourceIds == null) {
   if (other.resourceIds != null) {
    return false;
   }
  } else if (!resourceIds.equals(other.resourceIds)) {
   return false;
  }
  if (scope == null) {
   if (other.scope != null) {
    return false;
   }
  } else if (!scope.equals(other.scope)) {
   return false;
  }
  if (scoped != other.scoped) {
   return false;
  }
  if (secretRequired != other.secretRequired) {
   return false;
  }
  return true;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public String toString() {
  return "Client [clientId=" + clientId + ", clientSecret=" + clientSecret + ", accessTokenValiditySeconds="
    + accessTokenValiditySeconds + ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds + ", scoped="
    + scoped + ", secretRequired=" + secretRequired + ", scope=" + scope + ", authorizedGrantTypes="
    + authorizedGrantTypes + ", resourceIds=" + resourceIds + ", registeredRedirectUri=" + registeredRedirectUri
    + ", authorities=" + authorities + ", additionalInformation=" + additionalInformation + "]";
 }

}
