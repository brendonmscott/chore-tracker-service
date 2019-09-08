package com.bscott.chore.tracker.controller;

import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.ApiResponse;
import com.bscott.chore.tracker.dto.JwtAuthenticationResponseDto;
import com.bscott.chore.tracker.dto.CredentialsDto;
import com.bscott.chore.tracker.dto.SignUpRequestDto;
import com.bscott.chore.tracker.repository.LoginRepository;
import com.bscott.chore.tracker.repository.UserRepository;
import com.bscott.chore.tracker.security.JwtTokenProvider;
import com.bscott.chore.tracker.security.UserPrincipal;
import com.bscott.chore.tracker.translator.RegistrationTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private RegistrationTranslator registrationTranslator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody CredentialsDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
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

        if (loginRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (loginRepository.existsByEmail(signUpRequest.getCredentials().getEmail())) {
            return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User result = userRepository.save(registrationTranslator.toEntity(signUpRequest));

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users" + "?" + "username={username}")
                .buildAndExpand(result.getLoginCredentials().getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "User registered successfully"));
    }
}
