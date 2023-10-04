package com.example.controller;

import com.example.data.request.UserLoginRequest;
import com.example.data.request.UserSignInRequest;
import com.example.jwt.JwtUltis;
import com.example.security.UserLoginInfo;
import com.example.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authenticate")
public class LoginAndSignInPageController {
    private IAuthenticationService authenticationService;
    private AuthenticationManager authenticationManager;
    private JwtUltis jwtUltis;
    private PasswordEncoder passwordEncoder;

    public LoginAndSignInPageController(IAuthenticationService authenticationService, AuthenticationManager authenticationManager, JwtUltis jwtUltis, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.jwtUltis = jwtUltis;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/login")
    public ResponseEntity<?>authenticateUser(@RequestBody UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUserAccount(),
                        userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUltis.generateToken(userLoginRequest.getUserAccount());

        UserLoginInfo userDetails = (UserLoginInfo) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(jwt);
    }
    @PostMapping("/signIn")
    public ResponseEntity<String>SignIn(@RequestBody @Valid UserSignInRequest userSignInRequest){
        String password=passwordEncoder.encode(userSignInRequest.getUserPassword());
        userSignInRequest.setUserPassword(password);
        boolean insertUser = authenticationService.insertUSer(userSignInRequest);
        if(insertUser){
            return ResponseEntity.ok("account has been registered");
        }
        return ResponseEntity.ok("failed");
    }
}
