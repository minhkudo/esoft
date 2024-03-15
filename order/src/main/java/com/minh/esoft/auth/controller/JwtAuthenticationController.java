package com.minh.esoft.auth.controller;

import com.minh.esoft.auth.utils.JwtTokenUtil;
import com.minh.esoft.common.BaseResponse;
import com.minh.esoft.repository.request.UserLoginRequest;
import com.minh.esoft.repository.request.UserRegisterRequest;
import com.minh.esoft.repository.response.UserLoginResponse;
import com.minh.esoft.repository.response.UserRegisterResponse;
import com.minh.esoft.service.AccountUserService;
import com.minh.esoft.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class JwtAuthenticationController {

    private AuthenticationManager authenticationManager;
    private AccountUserService accountUserService;
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginRequest userLoginRequest) throws Exception {

        Authentication authentication = authenticate(userLoginRequest.getUsername(), userLoginRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

        return BaseResponse.success(new UserLoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAuthenticationToken(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        UserRegisterResponse userRegisterResponse = accountUserService.createAccountUser(userRegisterRequest);
        return BaseResponse.success(userRegisterResponse);
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
