package com.example.demo.commisionorder.entity;

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
 * 佣金变更订单
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-23
 */
@Data
@Accessors(chain = true)
public class Commisionorder {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 返现下单人id
     */
    private String bycustomerid;
    /**
     * 返现受益人
     */
    private String forgoodcustomerid;
    /**
     * 二级返现上一级受益人
     */
    private String onecustomerid;
    /**
     * 返现等级
     */
    private Integer level;
    
    /**
     * 返现比例
     */
    private BigDecimal proportion;
    /**
     * 返现前的佣金
     */
    private BigDecimal beforcommision;

    /**
     * 返现后的佣金
     */
    private BigDecimal aftercommision;

    /**
     * 下线此单消费金额
     */
    private BigDecimal bycustomermoney;
    
    /**
     * 佣金
     */
    private BigDecimal hopemoney;

    /**
     * 下线购买的商品ID
     */
    private Integer bygoodid;

    private String bygoodclassname;

    private String bygoodtypename;

    /**
     * 下线购买的商品名称
     */
    private String bygoodname;

    /**
     * 下线生产的订单号
     */
    private String byordernum;

    private Date createtime;

}
