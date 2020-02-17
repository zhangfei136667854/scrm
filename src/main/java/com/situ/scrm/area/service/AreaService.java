package com.situ.scrm.area.service;

import java.util.List;

import com.situ.scrm.area.domain.CdArea;

public interface AreaService {
	List<CdArea> findAll();
	List<CdArea> findByCode(Integer areaCode);
	String findName (Integer areaCode);


}
