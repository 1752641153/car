package com.itwanli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*车主表*/
@Data
@Entity
public class CarOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;     /*用户名*/
    private String password;     /*密码*/
    private String iphone;          /*手机号*/
    private int earnings;         /*收益*/
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTime;     /*注册时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date loginTime;      /*上次登录时间*/
    private String mac;          /*蓝牙Mac*/
    private String bluetooth;    /*蓝牙名称*/
    private String longitude;    /*经度*/
    private String latitude;     /*纬度*/
    private String place;        /*地址*/
    private String electric;     /*电量状态*/
    private Boolean valid;       /*是否有效（无效则不进行前端数据展示）*/

    @JsonIgnoreProperties("carOwner")
    @OneToMany(mappedBy = "carOwner")
    private List<Indent> indents = new ArrayList<>();

    @JsonIgnoreProperties("carOwner")
    @OneToMany(mappedBy = "carOwner")
    private List<Rent> rents = new ArrayList<>();

}
