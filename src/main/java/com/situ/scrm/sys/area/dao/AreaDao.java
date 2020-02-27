package com.situ.scrm.sys.area.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.scrm.sys.area.domain.Area;

@Repository
public interface AreaDao {
	List<Area> findByParent(Integer areaCode);
}
