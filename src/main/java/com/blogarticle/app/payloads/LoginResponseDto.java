package com.blogarticle.app.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private String message;
    private boolean success;
}
