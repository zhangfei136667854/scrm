package com.situ.scrm.ods.contract.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("Contract")
public class Contract extends BaseClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String contCode ; //跟单编号
private String userCode ;//用户编号
private String cusCode ; //客户编号
private  String contType ; //跟单方式
private String contStatus ; //跟单状态
private Integer isClose  ; //是否结束跟单#1是  #0否
private Date nextContDate ;//下次联系时间
private Integer isRemind ; // 是否提醒
private String  contFile ; //附件
private String  contInfo  ; //备注
private String parentCode ;//员工的上级Code 
private String cusName ; // 客户名称
private String userName ; //员工名称
private String contTypeVal ; //跟单方式信息
private String contStatusVal ; //跟单状态信息
public String getContCode() {
	return contCode;
}
public void setContCode(String contCode) {
	this.contCode = contCode;
}
public String getUserCode() {
	return userCode;
}
public void setUserCode(String userCode) {
	this.userCode = userCode;
}
public String getCusCode() {
	return cusCode;
}
public void setCusCode(String cusCode) {
	this.cusCode = cusCode;
}
public String getContType() {
	return contType;
}
public void setContType(String contType) {
	this.contType = contType;
}
public String getContStatus() {
	return contStatus;
}
public void setContStatus(String contStatus) {
	this.contStatus = contStatus;
}
public Integer getIsClose() {
	return isClose;
}
public void setIsClose(Integer isClose) {
	this.isClose = isClose;
}
public Date getNextContDate() {
	return nextContDate;
}
public void setNextContDate(Date nextContDate) {
	this.nextContDate = nextContDate;
}
public Integer getIsRemind() {
	return isRemind;
}
public void setIsRemind(Integer isRemind) {
	this.isRemind = isRemind;
}
public String getContFile() {
	return contFile;
}
public void setContFile(String contFile) {
	this.contFile = contFile;
}
public String getContInfo() {
	return contInfo;
}
public void setContInfo(String contInfo) {
	this.contInfo = contInfo;
}
public String getParentCode() {
	return parentCode;
}
public void setParentCode(String parentCode) {
	this.parentCode = parentCode;
}
public String getCusName() {
	return cusName;
}
public void setCusName(String cusName) {
	this.cusName = cusName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getContTypeVal() {
	return contTypeVal;
}
public void setContTypeVal(String contTypeVal) {
	this.contTypeVal = contTypeVal;
}
public String getContStatusVal() {
	return contStatusVal;
}
public void setContStatusVal(String contStatusVal) {
	this.contStatusVal = contStatusVal;
}

@Override
public String toString() {
	return "Contract [contCode=" + contCode + ", userCode=" + userCode + ", cusCode=" + cusCode + ", contType="
			+ contType + ", contStatus=" + contStatus + ", isClose=" + isClose + ", nextContDate=" + nextContDate
			+ ", isRemind=" + isRemind + ", contFile=" + contFile + ", contInfo=" + contInfo + ", parentCode="
			+ parentCode + ", cusName=" + cusName + ", userName=" + userName + "]";
}



}
