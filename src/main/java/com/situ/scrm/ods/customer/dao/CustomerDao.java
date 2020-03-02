package com.situ.scrm.ods.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.ods.chart.domain.Data;
import com.situ.scrm.ods.customer.domain.Customer;
@Repository
public interface CustomerDao extends BaseDao<Customer> {
	/**
	 * @Title: getByName 
	 * @Description:(根据名称查询实例)
	 * @param cusName
	 * @return
	 */
	Customer getByName(String cusName);

	Long updateByCusCode(String cusCode);

	List<Data> findAll();

	List<Data> finByParentCode(String parentCode);

	List<Data> findByUserCode(String userCode);
}
