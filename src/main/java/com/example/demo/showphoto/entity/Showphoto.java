package com.example.demo.showphoto.entity;

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
 * 展示页面图片
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-30
 */
@Data
@Accessors(chain = true)
public class Showphoto {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String url;

    private String describe;

    private String type;

    private Integer status;

    private Date createtime;

    private Date updatetime;

}
