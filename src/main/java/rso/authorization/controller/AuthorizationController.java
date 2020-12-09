package rso.authorization.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rso.authorization.dto.AuthDto;
import rso.authorization.dto.TokenDto;
import rso.authorization.service.AuthorizationService;

@RestController
@RequiredArgsConstructor
public class AuthorizationController
{
    private final AuthorizationService authService;

    @PostMapping("/login")
    public TokenDto login(@RequestBody AuthDto authDto) {
        return authService.generateToken(authDto);
    }
}
