package com.situ.scrm.ods.customer.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("Customer")
public class Customer extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;
private String cusCode ;//客户账号
	private String cusName;// 客户名称 #要求唯一
	private String cusPhone;// 手机号码
	private String cusContact;// 联系人
	private String cusQq;//QQ
	private String cusEmail;// 电子邮箱
	private Integer proCode;// 省CODE
	private Integer cityCode;// 市CODE
	private Integer areaCode;// 区CODE
	private String detAddress;// 详细地址
	private String cusType;// 客户类型
	private Integer cusKind;// 客户级别
	private String cusSource;// 客户来源
	private String cusIndustry;// 所属行业
	private Integer cusDay;// 公海日期
	private Integer isPublic;// 是否公海
	private String cusEnclosure;// 附件
	private String cusInfo;// 备注
	private String cusFromVal ; //客户来源信息
	private String cusTypeVal ; //客户类型信息
	private String cusInudsVal ; //所属行业信息
	public String getCusContact() {
		return cusContact;
	}
	public void setCusContact(String cusContact) {
		this.cusContact = cusContact;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	
	public String getCusQq() {
		return cusQq;
	}
	public void setCusQq(String cusQq) {
		this.cusQq = cusQq;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public Integer getProCode() {
		return proCode;
	}
	public void setProCode(Integer proCode) {
		this.proCode = proCode;
	}
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	public Integer getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}
	public String getDetAddress() {
		return detAddress;
	}
	public void setDetAddress(String detAddress) {
		this.detAddress = detAddress;
	}
	public String getCusType() {
		return cusType;
	}
	public void setCusType(String cusType) {
		this.cusType = cusType;
	}
	public Integer getCusKind() {
		return cusKind;
	}
	public void setCusKind(Integer cusKind) {
		this.cusKind = cusKind;
	}
	public String getCusSource() {
		return cusSource;
	}
	public void setCusSource(String cusSource) {
		this.cusSource = cusSource;
	}
	
	public String getCusIndustry() {
		return cusIndustry;
	}
	public void setCusIndustry(String cusIndustry) {
		this.cusIndustry = cusIndustry;
	}
	public Integer getCusDay() {
		return cusDay;
	}
	public void setCusDay(Integer cusDay) {
		this.cusDay = cusDay;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public String getCusEnclosure() {
		return cusEnclosure;
	}
	public void setCusEnclosure(String cusEnclosure) {
		this.cusEnclosure = cusEnclosure;
	}
	public String getCusInfo() {
		return cusInfo;
	}
	public void setCusInfo(String cusInfo) {
		this.cusInfo = cusInfo;
	}
	public String getCusCode() {
		return cusCode;
	}
	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}
	public String getCusFromVal() {
		return cusFromVal;
	}
	public void setCusFromVal(String cusFromVal) {
		this.cusFromVal = cusFromVal;
	}
	public String getCusTypeVal() {
		return cusTypeVal;
	}
	public void setCusTypeVal(String cusTypeVal) {
		this.cusTypeVal = cusTypeVal;
	}
	public String getCusInudsVal() {
		return cusInudsVal;
	}
	public void setCusInudsVal(String cusInudsVal) {
		this.cusInudsVal = cusInudsVal;
	}
	@Override
	public String toString() {
		return "Customer [cusCode=" + cusCode + ", cusName=" + cusName + ", cusPhone=" + cusPhone + ", cusContact="
				+ cusContact + ", cusQq=" + cusQq + ", cusEmail=" + cusEmail + ", proCode=" + proCode + ", cityCode="
				+ cityCode + ", areaCode=" + areaCode + ", detAddress=" + detAddress + ", cusType=" + cusType
				+ ", cusKind=" + cusKind + ", cusSource=" + cusSource + ", cusIndustry=" + cusIndustry + ", cusDay="
				+ cusDay + ", isPublic=" + isPublic + ", cusEnclosure=" + cusEnclosure + ", cusInfo=" + cusInfo + "]";
	}
	
	
}
