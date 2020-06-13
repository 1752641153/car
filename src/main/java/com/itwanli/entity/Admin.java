package com.itwanli.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@ApiModel("管理员表")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("token登录凭证")
    private String token;               /*token 登陆凭证*/
    @ApiModelProperty("token过期时间")
    private LocalDateTime expireTime;   /*token过期时间*/
    @ApiModelProperty("管理员登录时间")
    private LocalDateTime loginTime;    /*登录时间*/
/*    private String permission;         *//*用户权限  1，增删改查 2，增改查 3，查*/

}
