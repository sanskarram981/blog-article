package com.blogarticle.app.services.impl;

import com.blogarticle.app.entities.Role;
import com.blogarticle.app.entities.User;
import com.blogarticle.app.exceptions.ResourceAlreadyFoundException;
import com.blogarticle.app.exceptions.ResourceNotFoundException;
import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.UserDto;
import com.blogarticle.app.repositories.RoleRepository;
import com.blogarticle.app.repositories.UserRepository;
import com.blogarticle.app.services.UserService;
import com.blogarticle.app.utils.EmailUtils;
import com.blogarticle.app.utils.ValidateRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto userDto,Integer rid) {
        ValidateRequestData.validate(userDto);
        Optional<User> userOptional = this.userRepo.findByEmail(userDto.getEmail());
        if(userOptional.isPresent())
            throw new ResourceAlreadyFoundException("User","email",userDto.getEmail());
        User user = this.conversion(userDto);
        Optional<Role> roleOptional = this.roleRepo.findById(rid);
        if(!roleOptional.isPresent())
            throw new ResourceNotFoundException("Role","id",Integer.toString(rid));
        Role role = roleOptional.get();
        user.setRoles(Collections.singletonList(role));
        user = this.userRepo.save(user);
        emailUtils.sendMessage(userDto.getEmail(),"You have been registered successfully",
                "Thanks for registering.\n\nThanks & Regards,\nSihai");
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
        user.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());
        user = this.userRepo.save(user);
        return this.conversion(user);
    }

    @Override
    public ApiResponseDto deleteUser(Integer userId) {
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","id",Integer.toString(userId));
        this.userRepo.deleteById(userId);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("User deleted successfully");
        apiResponseDto.setSuccess(true);
        apiResponseDto.setData(null);
        return apiResponseDto;
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
    public ApiResponseDto getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(this::conversion).collect(Collectors.toList());
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("success");
        apiResponseDto.setSuccess(true);
        apiResponseDto.setData(userDtos);
        return apiResponseDto;
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
        user.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());
        return user;
    }

}
