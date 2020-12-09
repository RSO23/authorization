package rso.authorization.service;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rso.authorization.dto.AuthDto;
import rso.authorization.dto.TokenDto;
import rso.authorization.exceptions.ApiRequestException;
import rso.authorization.feign.UserServiceFeign;
import rso.authorization.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(AuthorizationService.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserServiceFeign userServiceFeign;
    private AuthDto authDto;


    @Override
    public UserDetails loadUserByUsername(String username) {
        authDto = userServiceFeign.getAuthDto(username);
        if (authDto != null) {
            return new org.springframework.security.core.userdetails.User(
                    authDto.getEmail(),
                    authDto.getPassword(),
                    AuthorityUtils.createAuthorityList("BASIC")
            );
        } else {
            throw new UsernameNotFoundException("Email not found.");
        }
    }

    public TokenDto generateToken(AuthDto authDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new ApiRequestException("Wrong email or password.");
        }

        log.info(MessageFormat.format("User \"{0}\" successfully logged in.", authDto.getEmail()));
        return new TokenDto(jwtUtil.generateToken(authDto.getEmail(), this.authDto.getId()));
    }
}
