package com.example.demo.notice.entity;

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
 * 最新公告
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-30
 */
@Data
@Accessors(chain = true)
public class Notice{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String content;

    private Integer status;

    private Date createtime;

    private Date updatetime;

}
