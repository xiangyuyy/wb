package com.example.demo.customer.entity;

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
 * 会员信息表
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-12
 */
@Data
@Accessors(chain = true)
public class Customer{

    private static final long serialVersionUID = 1L;
    @TableId(value = "customerid", type = IdType.AUTO)
    private Integer customerid;

    private String account;

    private String password;

    private String nickname;

    private String name;

    private Integer sex;

    private String qq;

    /**
     * 最新上架 热销商品 精选商品
     */
    private String weixin;

    private String phone;

    private String zhifubao;

    /**
     * 注册ip
     */
    private String ip;

    /**
     * 会员等级 0普通 1黄金 2铂金 3钻石 4至尊
     */
    private Integer level;

    /**
     * 冻结资金 升级用
     */
    private BigDecimal levelmony;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 消费总金额
     */
    private BigDecimal monetary;
    
    /**
     * 总佣金
     */
    private BigDecimal commision;

    /**
     * 已经提现佣金
     */
    private BigDecimal cashcommision;

    /**
     * 待提现佣金
     */
    private BigDecimal nocashcommision;

    /**
     * 邀请码 自己的customerid
     */
    private Integer invitatecode;

    /**
     * 受邀请人码 customerid
     */
    private Integer byinvitatecod;

    /**
     * 0待核实 1 正常 2冻结
     */
    private Integer status;

    private Date createtime;

    private Date updatetime;

}
