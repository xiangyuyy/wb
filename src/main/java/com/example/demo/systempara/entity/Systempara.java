package com.example.demo.systempara.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统参数表
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-23
 */
@Data
@Accessors(chain = true)
public class Systempara {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String adminaccount;

    private String adminpassword;

    /**
     * 等级1需要冻结的钱
     */
    private BigDecimal levelone;

    /**
     * 等级2需要冻结的钱
     */
    private BigDecimal leveltwo;

    private BigDecimal levelthree;

    private BigDecimal levelfour;

    /**
     * 等级1消费的钱
     */
    private BigDecimal leveloneordermy;

    /**
     * 等级2消费的钱
     */
    private BigDecimal leveltwoordermy;

    private BigDecimal levelthreeordermy;

    private BigDecimal levelfourordermy;

    /**
     * 等级1折扣 0.8等
     */
    private BigDecimal levelonediscount;

    /**
     * 等级2折扣 0.9等
     */
    private BigDecimal leveltwodiscount;

    private BigDecimal levelthreediscount;

    private BigDecimal levelfourdiscount;

    /**
     * 一级返佣比例
     */
    private BigDecimal commisionone;

    /**
     * 二级返佣比例
     */
    private BigDecimal commisiontwo;
    
    /**
     * 注册送的钱
     */
    private BigDecimal registersend;

    /**
     * 接口调用token
     */
    private String token;

    private Date createtime;

    private Date updatetime;



}
