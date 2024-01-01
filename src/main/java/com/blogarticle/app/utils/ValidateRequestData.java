package com.blogarticle.app.utils;

import com.blogarticle.app.exceptions.RequestDataValidationException;
import com.blogarticle.app.payloads.CategoryDto;
import com.blogarticle.app.payloads.LoginRequestDto;
import com.blogarticle.app.payloads.UserDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateRequestData {
    private static final int PASSWORD_LENGTH = 6;
    private static final String REGEX_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static void validate(UserDto userDto)
    {
        if(userDto.getName() == null || isBlank(userDto.getName()))
            throw new RequestDataValidationException("USER","Invalid/Missing argument for name");

        if(userDto.getEmail() == null || isBlank(userDto.getEmail()) || !isValid(userDto.getEmail()))
            throw new RequestDataValidationException("USER","Invalid/Missing argument for email");

        if(userDto.getPassword() == null || isBlank(userDto.getPassword()) || (userDto.getPassword() != null && userDto.getPassword().length() < PASSWORD_LENGTH))
            throw new RequestDataValidationException("USER","Invalid/Missing argument for password");
    }

    public static void validate(CategoryDto categoryDto)
    {
        if(categoryDto.getTitle() == null || isBlank(categoryDto.getTitle()))
            throw new RequestDataValidationException("CATEGORY","Invalid/Missing argument for title");
        if(categoryDto.getDescription() == null || isBlank(categoryDto.getDescription()))
            throw new RequestDataValidationException("CATEGORY","Invalid/Missing argument for description");
    }
    private static boolean isValid(String email)
    {
        if(email == null)
            return false;
        Pattern pattern = Pattern.compile(REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isBlank(String str)
    {
        if(str == null)
            return true;
        else
            return str.isBlank();
    }

    public static void validate(LoginRequestDto loginRequestDto)
    {
        if(loginRequestDto.getUsername() == null || isBlank(loginRequestDto.getUsername()))
            throw new RequestDataValidationException("LOGIN","username is missing");
        if(loginRequestDto.getPassword() == null || isBlank(loginRequestDto.getPassword()))
            throw new RequestDataValidationException("LOGIN","password is missing");
    }

}
