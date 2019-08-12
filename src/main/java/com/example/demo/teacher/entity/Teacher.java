package com.example.demo.teacher.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;




import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-14
 */
@Data
/*@EqualsAndHashCode(callSuper = true)*/
@Accessors(chain = true)
public class Teacher{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    private String Name;

    private Integer Age;
     
    private Student Student;

}
