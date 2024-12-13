package br.com.catolica.ms_gateway.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig implements Converter<Jwt, AbstractAuthenticationToken> {
  
  private JwtGrantedAuthoritiesConverter jwtConverter = new JwtGrantedAuthoritiesConverter();

  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    var authorities = Stream.concat(jwtConverter.convert(jwt).stream(), extractRoles(jwt).stream()).collect(Collectors.toSet());

    return new JwtAuthenticationToken(jwt, authorities);
  }

  private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
    var roles = new HashSet<String>(); 

    var realmRoles = jwt.getClaimAsMap("realm_access");
    if (realmRoles != null && realmRoles.containsKey("roles")) {
        var realmRoleList = (Collection<?>) realmRoles.get("roles");
        if (realmRoleList != null) {
            for (Object role : realmRoleList) {
                if (role instanceof String) {
                    roles.add((String) role);
                }
            }
        }
    }

    var resourceAccess = jwt.getClaimAsMap("resource_access");
    if (resourceAccess != null && resourceAccess.containsKey("gateway")) {
        var resourceRoleList = (Collection<?>) resourceAccess.get("gateway");
        if (resourceRoleList != null) {
            for (Object role : resourceRoleList) {
                if (role instanceof String) {
                    roles.add((String) role);
                }
            }
        }
    }

    return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toSet());
  }

}
