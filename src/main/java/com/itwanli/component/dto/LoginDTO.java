package com.itwanli.component.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 新增验证码传输类
 */
@Data
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
