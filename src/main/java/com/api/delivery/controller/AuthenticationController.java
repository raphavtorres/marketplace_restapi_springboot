package com.api.delivery.infra.security;

import com.api.delivery.model.User;
import com.api.delivery.infra.security.token.TokenDto;
import com.api.delivery.infra.security.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

   @Autowired
   private AuthenticationManager manager;

   @Autowired
   private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDto(tokenJWT));
    }


}
