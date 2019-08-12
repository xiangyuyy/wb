package com.example.demo.teacher.mapper;

import com.example.demo.teacher.entity.Student;
import com.example.demo.teacher.entity.Teacher;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-14
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

}
