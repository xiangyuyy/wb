package com.example.demo.good.entity;

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
 * 商品信息
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Data
@Accessors(chain = true)
public class Good{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 微博。。
     */
    private String goodtypename;

    /**
     * 刷粉 点赞
     */
    private String goodclassname;

    private String goodname;

    private String describe;

    /**
     * 下单说明 
     */
    private String orderdescribe;

    private String photourl;
    
    private String url;
    
    private Integer rank;

    /**
     * 最新上架 热销商品 精选商品
     */
    private String sign;

    /**
     * 快速的价格
     */
    private BigDecimal fastprice;

    /**
     * 慢速的价格
     */
    private BigDecimal slowprice;

    /**
     * 拿货快速的价格
     */
    private BigDecimal thirdfastprice;

    /**
     * 拿货慢速的价格
     */
    private BigDecimal thirdslowprice;

    /**
     * 0 下架 1 上架
     */
    private Integer status;
    
    /**
     *第三方平台名称(慢速 顶点 现代 牛牛)
     */
    private String thirdname;
    
    /**
     * 第三方类型下的分类（顶点的 点赞 空闲赞 初级赞）
     */
    private String thirdtypename;
    /**
     * 页面地址
     */
    private String hopeurl;
    
    /**
     * 最小下单数量
     */
    private Integer minnumber;
 

    private Date createtime;

    private Date updatetime;

}
