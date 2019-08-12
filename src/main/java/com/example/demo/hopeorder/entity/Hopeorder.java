package com.example.demo.hopeorder.entity;

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
 * @since 2019-02-17
 */
@Data
@Accessors(chain = true)
public class Hopeorder  {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String ordernum;
    /**
     * 交易状态（-1创建失败，0 审核中,1 队列中,2 执行中,3 有异常,5 已暂停,7 今天已完成,8 退款中,9 已完毕,10 已退款  11已取消（现代特殊的））
     */
    private Integer status;

    private Integer customerid;

    private Integer goodid;

    private String goodname;

    private String goodtypename;

    private String goodclassname;

    /**
     * 用户名称（微信或者抖音名称）wbname
     */
    private String orderusername;

    /**
     * 内容地址（微博地址抖音地址） wburl
     */
    private String orderurl;

    /**
     *  1快 0慢速（区分0人工和1自动订单）
     */
    private Integer isfast;
    
    /**
     *  订单类型 （刷赞 有表情赞3  微博赞1 评论赞2）
     */
    private Integer orderkind;
    /**
     * 数量
     */
    private Integer allnum;

    private BigDecimal price;

    private BigDecimal thirdprice;

    /**
     * 第三方总价
     */
    private BigDecimal thirdmoney;

    /**
     * 总价
     */
    private BigDecimal money;

    /**
     * 接口返回的总价
     */
    private BigDecimal returnmoney;
    /**
     * 利润
     */
    private BigDecimal hopemoney;

    /**
     * 开始时的数量
     */
    private Integer positionstart;

    /**
     * 结束时的数量
     */
    private Integer positionend;

    private Integer positionnow;

    /**
     * 已经完成的数量
     */
    private Integer allok;

    private String remark;

    /**
     * 接口传输模型
     */
    private String Other;

    /**
     * 内容 微博文章内容 订单成功返回
     */
    private String wbcontent;

    /**
     * 内容 微博文章订单成功返回的数据信息
     */
    private String sreturn;

    /**
     * 第三方生成的订单
     */
    private String thridtaskid;

    /**
     * 任务开始时间
     */
    private Date startime;

    /**
     * 任务结束时间
     */
    private Date endtime;

    private Date createtime;

    private Date updatetime;
    
    /**
     *第三方平台名称(慢速 顶点 现代 牛牛)
     */
    private String thirdname;
    
    /**
     * 第三方类型下的分类（顶点的 点赞 空闲赞 初级赞）
     */
    private String thirdtypename;

}
