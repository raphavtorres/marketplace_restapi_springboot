package com.api.delivery.controller;

import com.api.delivery.domain.user.RegisterDto;
import com.api.delivery.domain.user.User;
import com.api.delivery.domain.user.AuthenticationDto;
import com.api.delivery.infra.security.token.TokenDto;
import com.api.delivery.infra.security.token.TokenService;
import com.api.delivery.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

   @Autowired
   private AuthenticationManager authManager;

   @Autowired
   private TokenService tokenService;

   @Autowired
   private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        var authentication = authManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDto(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) {
        if (this.userRepository.findByUsername(registerDto.username()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User user = new User(registerDto.username(), encryptedPassword, registerDto.role());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
