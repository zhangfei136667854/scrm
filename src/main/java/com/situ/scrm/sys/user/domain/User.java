package com.situ.scrm.sys.user.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("User")
public class User extends BaseClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parentCode ;//父类用户
	private String userName ; // 用户名称
	private String userCode ; // 登陆账号
	private String userPass ; // 登陆密码
	private String userPhone ;//用户手机
	private String userAvatar ; // 用户头像
	private Integer userKind ;// 用户类型 // 1：超级用户 0：普通用户
	private Integer isLock ; // 是否被锁定1:是 0：否
	private Integer LoginIp ; // 最后登陆地址
	private Integer loginCount ; //登陆次数
	private Date loginDate ; // 最后登陆时间 
	private List<String> roleList ;// 角色Code集合
	private String roleCode ; //角色code 
	private String roleName ; // 角色名称
	private String parentName ; // 父类用户名称
	private Integer userLevel ;//用户级别
	
	
	public String getParentCode() {
		return parentCode;
	}


	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getUserPass() {
		return userPass;
	}


	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserAvatar() {
		return userAvatar;
	}


	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}


	public Integer getUserKind() {
		return userKind;
	}


	public void setUserKind(Integer userKind) {
		this.userKind = userKind;
	}


	public Integer getIsLock() {
		return isLock;
	}


	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}


	public Integer getLoginIp() {
		return LoginIp;
	}


	public void setLoginIp(Integer loginIp) {
		LoginIp = loginIp;
	}


	public Integer getLoginCount() {
		return loginCount;
	}


	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}


	public Date getLoginDate() {
		return loginDate;
	}


	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}


	public List<String> getRoleList() {
		return roleList;
	}


	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}


	public String getRoleCode() {
		return roleCode;
	}


	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public Integer getUserLevel() {
		return userLevel;
	}


	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}


	@Override
	public String toString() {
		return "User [parentCode=" + parentCode + ", userName=" + userName + ", userCode=" + userCode + ", userPass="
				+ userPass + ", userPhone=" + userPhone + ", userAvatar=" + userAvatar + ", userKind=" + userKind
				+ ", isLock=" + isLock + ", LoginIp=" + LoginIp + ", loginCount=" + loginCount + ", loginDate="
				+ loginDate + ", roleList=" + roleList + ", roleCode=" + roleCode + ", roleName=" + roleName
				+ ", parentName=" + parentName + ", userLevel=" + userLevel + "]";
	}


	
	
	

}
