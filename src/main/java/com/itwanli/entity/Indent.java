package com.itwanli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Indent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numbers;     /*订单编号*/
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;          /*订单日期*/
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm:ss")
    @JsonFormat(pattern = "hh:mm:ss",timezone = "GMT+8")
    private Date createTime;    /*预定开始时间*/
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm:ss")
    @JsonFormat(pattern = "hh:mm:ss",timezone = "GMT+8")
    private Date overTime;      /*预定结束时间*/
    private int money;          /*花费*/
    private String grade;       /*订单评分*/
    private String evaluate;    /*订单评价*/
    private String state;       /*订单状态*/
    private Boolean valid;       /*是否有效（无效则不进行前端数据展示）*/

    @JsonIgnoreProperties("indents")
    @ManyToOne
    private User user;

    @JsonIgnoreProperties("indents")
    @ManyToOne
    private CarOwner carOwner;
}
