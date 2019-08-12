package com.example.demo.hopeorder.modelvo;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.common.PageVo;
import com.example.demo.hopeorder.entity.Hopeorder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 160707
 *顶点微博转发传输类
 */
@Data
@Accessors(chain = true)
public class WbzhuanfanVo implements Cloneable  {
	private static final long serialVersionUID = 1L;
    /**
     * 内容地址（微博地址抖音地址） wburl
     */
    private String orderurl;

    /**
     *  1快 0慢速（区分0人工和1自动订单）
     */
    private Integer isfast;
    
    private Integer goodid;
    
    /**
     * 数量
     */
    private Integer allnum;
    /**
     *  业务类型（转发 评论）
     */
    private Integer plzftype;
    
    /**
     *  转发语类型（订单 自动内容,人工内容）( 现代 1 无内容 2 系统默认内容 3自定义内容)
     */
    private Integer iscomment;
    /**
     * 评论数量
     */
    private Integer countnumber;
    /**
     * 指定内容
     */
    private String comments;
    /**
     * 关键词
     */
    private String comment_keyword;
    /**
     * 最大评论数量
     */
    private Integer comment_keyword_nums;
    /**
     *  是否同时评论
     */
    private Integer iscomment_onoff;
    
    /**
     *  转发语评论语类型（同转发语,指定内容）
     */
    private Integer zfiscomment;
    /**
     * 同时发语评论数量
     */
    private Integer zftspycurrentnumber;
    /**
     * 转发评论的数量
     */
    private Integer zfcurrentnumber;
    /**
     * 转发指定内容
     */
    private String zfcomments;
    
    /**
     *  刷赞类型
     */
    private Integer sztype;
    
    /**
     * 刷赞数量
     */
    private Integer szallnum;
    /**
     * 总价
     */
    private BigDecimal money;
    /**
     * 备注
     */
    private String remark;
    
    
    
    
    //现代专用的
    /**
     * 微博内容
     */
    private String ordercontent;
    
    /**
     * 初始评论数量
     */
    private Integer orderzfnum;
    
    /**
     *初始转发数量
     */
    private Integer orderplnum;
    
    /**
     * 微博名称
     */
    private String ordername;
    
    public Object clone() throws CloneNotSupportedException {
    	      return super.clone();
    	    }
    
}
