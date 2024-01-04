package com.blogarticle.app.controllers;

import com.blogarticle.app.constants.SihaiConstants;
import com.blogarticle.app.kafka.KafkaProducer;
import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.UserDto;
import com.blogarticle.app.services.UserService;
import com.blogarticle.app.utils.AuditUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping(SihaiConstants.USER_URI)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuditUtils auditUtils;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        auditUtils.audit("USER","POST",SihaiConstants.USER_URI+"/");
        return new ResponseEntity<>(this.userService.createUser(userDto,1), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId)
    {
        auditUtils.audit("USER","PUT",SihaiConstants.USER_URI+"/"+Integer.toString(userId));
        return new ResponseEntity<>(this.userService.updateUser(userDto,userId),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDto> deleteUser(@PathVariable("userId") Integer userId)
    {
        auditUtils.audit("USER","DELETE",SihaiConstants.USER_URI+"/"+Integer.toString(userId));
        return new ResponseEntity<>(this.userService.deleteUser(userId),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
//    @Operation(summary = "getting a user by its id")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "user found with id",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
//                           @ApiResponse(responseCode = "404",description = "user not found with id"),
//                           @ApiResponse(responseCode = "400",description = "user id not provided")})
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userId)
    {
        auditUtils.audit("USER","GET",SihaiConstants.USER_URI+"/"+Integer.toString(userId));
        return new ResponseEntity<>(this.userService.getUser(userId),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseDto> getAllUser()
    {
        auditUtils.audit("USER","GET",SihaiConstants.USER_URI+"/");
        return new ResponseEntity<>(this.userService.getAllUser(),HttpStatus.OK);
    }


}
