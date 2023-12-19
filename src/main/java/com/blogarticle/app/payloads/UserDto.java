package com.blogarticle.app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(allowSetters = true,value = {"password"})
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
