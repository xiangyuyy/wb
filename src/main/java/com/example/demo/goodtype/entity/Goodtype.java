package com.example.demo.goodtype.entity;

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
 * 商品类型 微博 抖音
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Data
@Accessors(chain = true)
public class Goodtype {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String describe;

    private Integer status;

    private Date createtime;

    private Date updatetime;
    
    private Integer rank;

}
