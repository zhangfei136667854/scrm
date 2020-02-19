package com.situ.scrm.area.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;


@Alias("CdArea")
public class CdArea extends BaseClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String areaCode ;//地区编号
	private String areaName ;//地区名称
	private Double parentCode ;//父级编号
	private Integer areaRunk ;//地区等级
	private Integer hasChild ;//是否包括下级单位
	private Integer activeFlag ; // 数据是否有效
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Double getParentCode() {
		return parentCode;
	}
	public void setParentCode(Double parentCode) {
		this.parentCode = parentCode;
	}
	public Integer getAreaRunk() {
		return areaRunk;
	}
	public void setAreaRunk(Integer areaRunk) {
		this.areaRunk = areaRunk;
	}
	public Integer getHasChild() {
		return hasChild;
	}
	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

}
