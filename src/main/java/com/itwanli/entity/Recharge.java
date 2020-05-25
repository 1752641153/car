package com.itwanli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Recharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int money;          /*充值金额*/
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTime;    /*充值时间*/
    private String numbers;     /*交易单号*/
    private String state;       /*交易状态*/
    private Boolean valid;       /*是否有效（无效则不进行前端数据展示）*/

    @JsonIgnoreProperties("recharges")
    @ManyToOne
    private User user;

}
