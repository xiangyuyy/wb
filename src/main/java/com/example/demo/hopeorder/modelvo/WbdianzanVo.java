package com.example.demo.hopeorder.modelvo;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.common.PageVo;
import com.example.demo.hopeorder.entity.Hopeorder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 160707
 *顶点微博点赞传输类
 */
@Data
@Accessors(chain = true)
public class WbdianzanVo {
	private static final long serialVersionUID = 1L;
    /**
     * 1微博赞 2评论赞 3表情赞
     */
	private Integer kind;
	private String facekind;
	private String mid;
    private String remark;
    /**
     * 内容地址（微博地址抖音地址） wburl
     */
    private String orderurl;

    /**
     *  1快 0慢速（区分0人工和1自动订单）
     */
    private Integer isfast;

    /**
     * 数量
     */
    private BigDecimal allnum;
    
    private Integer goodid;
    /**
     * 总价
     */
    private BigDecimal money;
    
    /**
     * 快速的价格
     */
    private BigDecimal fastprice;

    /**
     * 慢速的价格
     */
    private BigDecimal slowprice;
    
    /**
     * 用户名称（微信或者抖音名称）wbname
     */
    private String orderusername;
    
    private Integer positionstart;
	
}
