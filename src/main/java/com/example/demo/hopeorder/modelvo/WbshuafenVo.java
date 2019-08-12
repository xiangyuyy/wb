package com.example.demo.hopeorder.modelvo;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.common.PageVo;
import com.example.demo.hopeorder.entity.Hopeorder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 160707
 *顶点微博刷粉传输类
 */
@Data
@Accessors(chain = true)
public class WbshuafenVo {
	private static final long serialVersionUID = 1L;
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
    
    
    
    private Integer speed;
    /**
     * 总价
     */
    private BigDecimal money;
    
    private Integer iftiming;
	private String startdate ;
	private Integer perhour;
	private Integer pernum;	
    /**
     * 用户名称（微信或者抖音名称）wbname
     */
    private String orderusername;
    
    private Integer positionstart;
    	
}
