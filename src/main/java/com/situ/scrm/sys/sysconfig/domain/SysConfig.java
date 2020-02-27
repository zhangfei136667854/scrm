package com.situ.scrm.sys.sysconfig.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("SysConfig")
public class SysConfig extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private String config1;//公司名称
	private String config2;//公海天数
	private String config3;//提醒日期
	private String config4;//备用
	public String getConfig1() {
		return config1;
	}
	public void setConfig1(String config1) {
		this.config1 = config1;
	}
	public String getConfig2() {
		return config2;
	}
	public void setConfig2(String config2) {
		this.config2 = config2;
	}
	public String getConfig3() {
		return config3;
	}
	public void setConfig3(String config3) {
		this.config3 = config3;
	}
	public String getConfig4() {
		return config4;
	}
	public void setConfig4(String config4) {
		this.config4 = config4;
	}
	@Override
	public String toString() {
		return "SysConfig [config1=" + config1 + ", config2=" + config2 + ", config3=" + config3 + ", config4="
				+ config4 + "]";
	}
	
	
}
