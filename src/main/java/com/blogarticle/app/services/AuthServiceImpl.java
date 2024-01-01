package com.blogarticle.app.services;

import com.blogarticle.app.exceptions.InvalidCredentialsException;
import com.blogarticle.app.payloads.LoginRequestDto;
import com.blogarticle.app.payloads.LoginResponseDto;
import com.blogarticle.app.payloads.UserDto;
import com.blogarticle.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto)
    {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        try
        {
            String username = loginRequestDto.getUsername();
            String password = loginRequestDto.getPassword();
            UsernamePasswordAuthenticationToken pat = new UsernamePasswordAuthenticationToken(username,password);
            this.authenticationManager.authenticate(pat);
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
            String token = this.jwtUtils.generateToken(userDetails);
            loginResponseDto.setToken(token);
            loginResponseDto.setMessage("login successfully");
            loginResponseDto.setSuccess(true);
        }
        catch(BadCredentialsException e)
        {
            System.out.println(e.getMessage());
            throw new InvalidCredentialsException("username/password is wrong");
        }
        return loginResponseDto;
    }

    @Override
    public UserDto register(UserDto userDto) {
        return this.userService.createUser(userDto,1);
    }
}
