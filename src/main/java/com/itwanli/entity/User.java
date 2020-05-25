package com.itwanli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*用户基本信息表*/
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;     /*用户名*/
    private String password;     /*密码*/
    private String iphone;          /*手机号*/
    private int balance;         /*账户余额*/
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTime;     /*注册时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date loginTime;      /*上次登录时间*/
    private Boolean valid;       /*是否有效（无效则不进行前端数据展示）*/

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Indent> indents = new ArrayList<>();

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Recharge> recharges = new ArrayList<>();
}
