package com.situ.scrm.sys.dictionary.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("Dictionary")
public class Dictionary extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PARENT_KEY="K000";//得到默认的parentKey
	private String parentKey;//父类索引
	private String dicKey;//字典索引，与父类之间是一对多的关系
	private String dicCode;//字典编码
	private String dicValue;//字典描述（中文）
	private Integer hasChild;//是否有子数据#1：是；2：否；
	private Integer dicOrder;//排序
	
	/*customCodeBegin*/
	private List<Dictionary>children;//子元素的数据
	private String parentName;//父类字典的名称
	public List<Dictionary> getChildren() {
		return children;
	}
	public void setChildren(List<Dictionary> children) {
		this.children = children;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/*customCodeEnd*/
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
	public Integer getHasChild() {
		return hasChild;
	}
	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
	public Integer getDicOrder() {
		return dicOrder;
	}
	public void setDicOrder(Integer dicOrder) {
		this.dicOrder = dicOrder;
	}
	@Override
	public String toString() {
		return "Dictionary [parentKey=" + parentKey + ", dicKey=" + dicKey + ", dicCode=" + dicCode + ", dicValue="
				+ dicValue + ", hasChild=" + hasChild + ", dicOrder=" + dicOrder + ", children=" + children
				+ ", parentName=" + parentName + "]";
	}
	
	
	
	
	
}
