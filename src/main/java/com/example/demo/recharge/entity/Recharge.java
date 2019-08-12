package com.example.demo.recharge.entity;

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
 * 
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-23
 */
@Data
@Accessors(chain = true)
public class Recharge{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String ordernum;

    /**
     * 支付宝账号
     */
    private String account;

    /**
     * 支付宝订单号
     */
    private String relationnum;

    /**
     * 1支付宝 2微信
     */
    private Integer payway;

    private BigDecimal money;

    private Integer customerid;

    private Date updatetime;

    private Date creattime;

    /**
     * 0失败 1 成功
     */
    private Integer status;


}
