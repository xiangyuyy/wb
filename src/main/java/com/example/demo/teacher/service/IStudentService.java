package com.example.demo.teacher.service;

import com.example.demo.teacher.entity.Student;
import com.example.demo.teacher.entity.Teacher;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-14
 */
public interface IStudentService extends IService<Student> {
	Student selectsById(int id);
}
