package com.bscott.chore.tracker.controller;

import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.ApiResponse;
import com.bscott.chore.tracker.dto.JwtAuthenticationResponseDto;
import com.bscott.chore.tracker.dto.CredentialsDto;
import com.bscott.chore.tracker.dto.SignUpRequestDto;
import com.bscott.chore.tracker.security.JwtTokenProvider;
import com.bscott.chore.tracker.security.UserPrincipal;
import com.bscott.chore.tracker.service.UserService;
import com.bscott.chore.tracker.translator.RegistrationTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(allowCredentials = "true")
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationTranslator registrationTranslator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody CredentialsDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtAuthenticationResponseDto(userPrincipal.getId(), jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequest) {

        User registeredUser = userService.registerNewUser(registrationTranslator.toEntity(signUpRequest));

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users" + "?" + "username={username}")
                .buildAndExpand(registeredUser.getLoginCredentials().getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "User registered successfully"));
    }
}
