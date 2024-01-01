package com.blogarticle.app.controllers;

import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.UserDto;
import com.blogarticle.app.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        return new ResponseEntity<>(this.userService.createUser(userDto,1), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId)
    {
        return new ResponseEntity<>(this.userService.updateUser(userDto,userId),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId)
    {
        return new ResponseEntity<>(this.userService.deleteUser(userId),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userId)
    {
        return new ResponseEntity<>(this.userService.getUser(userId),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllUser()
    {
        return new ResponseEntity<>(this.userService.getAllUser(),HttpStatus.OK);
    }


}
