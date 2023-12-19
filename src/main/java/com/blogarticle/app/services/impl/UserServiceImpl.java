package com.blogarticle.app.services.impl;

import com.blogarticle.app.entities.User;
import com.blogarticle.app.exceptions.ResourceAlreadyFoundException;
import com.blogarticle.app.exceptions.ResourceNotFoundException;
import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.UserDto;
import com.blogarticle.app.repositories.UserRepository;
import com.blogarticle.app.services.UserService;
import com.blogarticle.app.utils.ValidateRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        ValidateRequestData.validate(userDto);
        Optional<User> userOptional = this.userRepo.findByEmail(userDto.getEmail());
        if(userOptional.isPresent())
            throw new ResourceAlreadyFoundException("User","email",userDto.getEmail());
        User user = this.conversion(userDto);
        user = this.userRepo.save(user);
        return this.conversion(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer userId) {
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","id",Integer.toString(userId));
        User user = userOptional.get();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user = this.userRepo.save(user);
        return this.conversion(user);
    }

    @Override
    public ApiResponse deleteUser(Integer userId) {
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","id",Integer.toString(userId));
        this.userRepo.deleteById(userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("User deleted successfully");
        apiResponse.setSuccess(true);
        apiResponse.setData(null);
        return apiResponse;
    }

    @Override
    public UserDto getUser(Integer userId) {
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","id",Integer.toString(userId));
        User user = userOptional.get();
        return this.conversion(user);
    }

    @Override
    public ApiResponse getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(this::conversion).collect(Collectors.toList());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("success");
        apiResponse.setSuccess(true);
        apiResponse.setData(userDtos);
        return apiResponse;
    }

    private UserDto conversion(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }

    private User conversion(UserDto userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

}
