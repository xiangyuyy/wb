package com.example.demo.teacher.mapper;

import com.example.demo.teacher.entity.Student;
import com.example.demo.teacher.entity.Teacher;

import org.apache.ibatis.annotations.One;
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
public interface StudentMapper extends BaseMapper<Student> {
	@Select("SELECT * FROM student WHERE id = #{id}")
	@Results({
	       @Result(property = "Teacher",
	               column = "TeacherId",
	               one=@One(select="com.example.demo.teacher.mapper.TeacherMapper.selectById"))
	})
	Student selectsById(@Param("id") int id);
}
