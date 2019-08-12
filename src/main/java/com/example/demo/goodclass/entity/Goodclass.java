package com.example.demo.goodclass.entity;

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
 * 商品分类 (刷粉 点赞 博文转评。。)
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Data
@Accessors(chain = true)
public class Goodclass {

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
