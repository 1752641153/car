package com.itwanli.component.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenVO {
    private String token;
    private LocalDateTime expireTime;
}
