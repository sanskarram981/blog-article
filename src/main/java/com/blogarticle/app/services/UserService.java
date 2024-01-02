package com.blogarticle.app.services;

import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto,Integer rid);
    UserDto updateUser(UserDto userDto,Integer userId);
    ApiResponseDto deleteUser(Integer userId);
    UserDto getUser(Integer userId);
    ApiResponseDto getAllUser();
}
