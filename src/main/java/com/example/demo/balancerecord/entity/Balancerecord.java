package com.example.demo.balancerecord.entity;

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
 * 余额变更记录
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-23
 */
@Data
@Accessors(chain = true)
public class Balancerecord {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer customerid;

    /**
     * 0充值 1佣金提现 2下单消费 升级冻结3 冻结解除4  5 提现申请   6退单返回   7管理员操作
     */
    private Integer type;

    /**
     * 下单消费，冻结为负数
     */
    private BigDecimal balance;

    /**
     * 变更前余额
     */
    private BigDecimal beforbalance;

    /**
     * 变更后的余额
     */
    private BigDecimal afterbalance;

    /**
     * 0充值 1佣金提现 2下单消费对应的订单号
     */
    private String detailordernum;

    private Date createtime;
    /**
     * 交易状态（0失败 1 审核中 2审核通过转账中 3成功）
     */
    private Integer status;

}
