/**
 * @Company:中享思途   
 * @Title:Student.java 
 * @Author:wxinpeng   
 * @Date:2020年2月6日 下午1:37:14     
 */
package com.situ.scrm.student.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * @ClassName:Student 
 * @Description:(学生类)  
 */
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long rowId;
	private String stuName;//姓名
	private Integer stuAge;//年龄
	private Integer stuSex;//性别
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stuBirthday;//生日
	private List<String> stuLikes;//爱好
	private String stuImg;//学生的头像

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Integer getStuAge() {
		return stuAge;
	}

	public void setStuAge(Integer stuAge) {
		this.stuAge = stuAge;
	}

	public Integer getStuSex() {
		return stuSex;
	}

	public void setStuSex(Integer stuSex) {
		this.stuSex = stuSex;
	}

	public Date getStuBirthday() {
		return stuBirthday;
	}

	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	public List<String> getStuLikes() {
		return stuLikes;
	}

	public void setStuLikes(List<String> stuLikes) {
		this.stuLikes = stuLikes;
	}

	public String getStuImg() {
		return stuImg;
	}

	public void setStuImg(String stuImg) {
		this.stuImg = stuImg;
	}

	@Override
	public String toString() {
		return "Student [rowId=" + rowId + ", stuName=" + stuName + ", stuAge=" + stuAge + ", stuSex=" + stuSex + ", stuBirthday=" + stuBirthday + ", stuLikes=" + stuLikes + "]";
	}
}
