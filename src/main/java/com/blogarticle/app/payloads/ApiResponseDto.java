package com.blogarticle.app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponseDto
{
    private String message;
    private boolean success;
    private Object data;
}
