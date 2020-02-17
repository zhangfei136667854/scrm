package com.situ.scrm.area.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.scrm.area.domain.CdArea;

@Repository
public interface AreaDao {
	List<CdArea> find();
	List<CdArea> findByCode(Integer parentCode);
	String findName( Integer areaCode);


}
