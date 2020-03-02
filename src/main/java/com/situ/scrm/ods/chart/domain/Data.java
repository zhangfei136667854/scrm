package com.situ.scrm.ods.chart.domain;

import org.apache.ibatis.type.Alias;

@Alias("Data")
public class Data {

	
	private Integer dataCount ;
	private String keyVal ;
	public Integer getDataCount() {
		return dataCount;
	}
	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}
	public String getKeyVal() {
		return keyVal;
	}
	public void setKeyVal(String keyVal) {
		this.keyVal = keyVal;
	}
	
	
}
