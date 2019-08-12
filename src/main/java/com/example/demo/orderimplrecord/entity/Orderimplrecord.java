package com.example.demo.orderimplrecord.entity;

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
 * 下单调用接口记录
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-03-31
 */
@Data
@Accessors(chain = true)
public class Orderimplrecord {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String orderid;

    private Integer customerid;
    private String productname;
    private String url;
    private String map;
    
    /**
     * 类型  顶点 现代
     */
    private String type;

    /**
     * 1000 是成功 其他是失败
     */
    private String code;

    /**
     * 1成功 0 失败
     */
    private Integer issucess;

    /**
     * 接口返回数据
     */
    private String data;

    /**
     * 接口返回信息
     */
    private String msg;

    private Date createtime;


}
