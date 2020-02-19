package com.situ.scrm.sys.sysSetting.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("SysSetting")
public class SysSetting  extends BaseClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nationWaterDay ;//公海天数
	private String docRemind  ; // 跟单提醒
	private String companyName ; //公司名称
	public String getNationWaterDay() {
		return nationWaterDay;
	}
	public void setNationWaterDay(String nationWaterDay) {
		this.nationWaterDay = nationWaterDay;
	}
	public String getDocRemind() {
		return docRemind;
	}
	public void setDocRemind(String docRemind) {
		this.docRemind = docRemind;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "SysSetting [nationWaterDay=" + nationWaterDay + ", docRemind=" + docRemind + ", companyName="
				+ companyName + "]";
	}
	
	

}
