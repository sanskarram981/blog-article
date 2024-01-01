package com.blogarticle.app.services;

import com.blogarticle.app.payloads.LoginRequestDto;
import com.blogarticle.app.payloads.LoginResponseDto;
import com.blogarticle.app.payloads.UserDto;

public interface AuthService {
    public LoginResponseDto login(LoginRequestDto loginRequestDto);
    public UserDto register(UserDto userDto);
}
