/**
 * @Company:中享思途   
 * @Title:StudentController.java 
 * @Author:wxinpeng   
 * @Date:2020年2月6日 下午1:40:26     
 */
package com.situ.scrm.student.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.situ.scrm.student.domain.Student;

/** 
 * @ClassName:StudentController 
 * @Description:(学生类的Controller)  
 */
@RestController
@RequestMapping("/student")
public class StudentController implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * @Title: saveStudent 
	 * @Description:(新增动作)
	 * @param student
	 * @return
	 */
	@PostMapping
	public Long saveStudent(Student student) {
		return 1L;
	}

	/**
	 * @Title: checkStuName 
	 * @Description:(学生名称的唯一性校验)
	 * @param stuName
	 * @return 1:可以使用;0:不可以使用;
	 */
	@GetMapping("/checkname")
	public Integer checkStuName(String stuName) {
		Integer result = 1;
		//默认一个逻辑 ，假定  ‘wangwu’已经存在
		if ("wangwu".equals(stuName)) {
			result = 0;
		}
		return result;
	}
}
