package br.com.catolica.gateway.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Map;

public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public @NotNull AbstractAuthenticationToken convert(@NotNull Jwt jwt) {
        Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
        Collection<String> roles = realmAccess.get("roles");
        var grants = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();

        return new JwtAuthenticationToken(jwt, grants);
    }
}
