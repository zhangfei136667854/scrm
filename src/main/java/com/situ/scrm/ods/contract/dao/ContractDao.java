package com.situ.scrm.ods.contract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.domain.Pagination;
import com.situ.scrm.ods.chart.domain.Data;
import com.situ.scrm.ods.contract.domain.Contract;

@Repository
public interface ContractDao {

	void save(Contract cont);

	Integer getCount();

	List<Contract> findByPage(@Param("pagination")Pagination pagination,@Param("cont") Contract cont);

	List<Contract> findByPageAndParentCode(@Param("pagination")Pagination pagination,@Param("parentCode") String parentCode);

	List<Contract> findByPageAndUserCode(@Param("pagination")Pagination pagination,@Param("userCode") String userCode);

	List<Data> find();

	List<Data> finByParentCode(String parentCode);

	List<Data> findByUserCode(String userCode);



}
