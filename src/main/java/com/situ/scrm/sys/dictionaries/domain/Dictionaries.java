package com.situ.scrm.sys.dictionaries.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;


@Alias("Dictionaries")
public class Dictionaries extends BaseClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PARENT_KEY = "S000";// 默认的一级资源的CODE
	private String parentKey; //父类索引
	private String dicKey;//字典索引
	private String dicCode;//字典编码
	private String dicValue;//字典描述
	private Integer orderIndex;//排序
	private Integer hasChild;//是否有数据 1是 0否
	/* 额外的信息 -开始*/
	private List<Dictionaries> children;// 子元素的数据
	private String parentName;// 父类资源的名称
	
	public List<Dictionaries> getChildren() {
		return children;
	}
	public void setChildren(List<Dictionaries> children) {
		this.children = children;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getHasChild() {
		return hasChild;
	}
	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getDicKey() {
		return dicKey;
	}
	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}
	public String getDicCode() {
		return dicCode;
	}
	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
	public String getDicValue() {
		return dicValue;
	}
	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	@Override
	public String toString() {
		return "Dictionaries [parentKey=" + parentKey + ", dicKey=" + dicKey + ", dicCode=" + dicCode + ", dicValue="
				+ dicValue + ", orderIndex=" + orderIndex + "]";
	}
	
	
	
	
	
	
	

}
