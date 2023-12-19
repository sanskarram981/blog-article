package com.blogarticle.app.services;

import com.blogarticle.app.entities.User;
import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    ApiResponse deleteUser(Integer userId);
    UserDto getUser(Integer userId);
    ApiResponse getAllUser();
}
