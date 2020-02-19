/**
 * @Company:中享思途   
 * @Title:SysCount.java 
 * @Author:wxinpeng   
 * @Date:2020年2月5日 下午2:02:57     
 */
package com.situ.scrm.syscount.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * @ClassName:SysCount
 * @Description:(系统计数)
 */
@Alias("SysCount")
public class SysCount extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String INDEX1 = "INDEX1";
	public static final String INDEX2 = "INDEX2";
	private Integer index1;// 订单编号#每天重置
	private Integer index2;// 资源编号#无需重置
	private Integer index3;// 系统预留

	public Integer getIndex1() {
		return index1;
	}

	public void setIndex1(Integer index1) {
		this.index1 = index1;
	}

	public Integer getIndex2() {
		return index2;
	}

	public void setIndex2(Integer index2) {
		this.index2 = index2;
	}

	public Integer getIndex3() {
		return index3;
	}

	public void setIndex3(Integer index3) {
		this.index3 = index3;
	}
}
