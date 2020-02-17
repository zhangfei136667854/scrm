package com.situ.scrm.area.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.area.dao.AreaDao;
import com.situ.scrm.area.domain.CdArea;
import com.situ.scrm.area.service.AreaService;
@Service
public class AreaServiceImpl implements Serializable, AreaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AreaDao areaDao ;

	@Override
	public List<CdArea> findAll() {
		return areaDao.find();
	}

	@Override
	public List<CdArea> findByCode(Integer parentCode) {
		return areaDao.findByCode(parentCode);
	}

	@Override
	public String findName(Integer areaCode) {
		return areaDao.findName(areaCode);
	}


}
