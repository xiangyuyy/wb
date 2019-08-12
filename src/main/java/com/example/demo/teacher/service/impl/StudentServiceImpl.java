package com.example.demo.teacher.service.impl;

import com.example.demo.teacher.entity.Student;
import com.example.demo.teacher.entity.Teacher;
import com.example.demo.teacher.mapper.StudentMapper;
import com.example.demo.teacher.mapper.TeacherMapper;
import com.example.demo.teacher.service.IStudentService;
import com.example.demo.teacher.service.ITeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

	@Override
	public Student selectsById(int id) {
		return this.baseMapper.selectsById(id);
	}

}
