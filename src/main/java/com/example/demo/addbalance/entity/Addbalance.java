package com.example.demo.addbalance.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 充值订单
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-04-07
 */
@Data
@Accessors(chain = true)
public class Addbalance{

    private static final long serialVersionUID = 1L;

    private String balanceorderid;

    private String customerid;

    /**
     * 充值方式
     */
    private String payway;

    /**
     * 第三方支付账号
     */
    private String thirdaccount;

    /**
     * 第三方支付订单号
     */
    private String thirdnumber;

    /**
     * 充值前余额
     */
    private BigDecimal beforbalance;

    /**
     * 充值后余额
     */
    private BigDecimal afterbalance;

    /**
     * 充值金额
     */
    private BigDecimal balance;

    /**
     * 0失败 1 成功 
     */
    private Integer status;

    private String errormsg;

    private Date createtime;

    private Date updatetime;
}
