package com.itwanli.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String token;               /*token 登陆凭证*/
    private LocalDateTime expireTime;   /*token过期时间*/
    private LocalDateTime loginTime;    /*登录时间*/
/*    private String permission;         *//*用户权限  1，增删改查 2，增改查 3，查*/

}
