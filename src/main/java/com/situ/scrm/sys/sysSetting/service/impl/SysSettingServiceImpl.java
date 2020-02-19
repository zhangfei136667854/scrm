package com.situ.scrm.sys.sysSetting.service.impl;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.sys.sysSetting.dao.SysSettingDao;
import com.situ.scrm.sys.sysSetting.domain.SysSetting;
import com.situ.scrm.sys.sysSetting.service.SysSettingService;

@Service
public class SysSettingServiceImpl implements Serializable, SysSettingService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SysSettingDao sysSettingDao;

	@Override
	public Long doPut(SysSetting sysSet) {

		return sysSettingDao.update(sysSet);
	}
	
	@PostConstruct
	public void init() {
		// 查询有木有实例
		SysSetting sysSet = sysSettingDao.gett();
		if (sysSet == null) {
			sysSet = new SysSetting();
			sysSet.setActiveFlag(1);
			sysSet.setCreateBy("sys");
			sysSet.setCreateDate(new Date());
			sysSet.setCompanyName("1");
			sysSet.setDocRemind("1");
			sysSet.setNationWaterDay("1");
			sysSettingDao.save(sysSet);

		}
	}

	@Override
	public SysSetting doget() {
		// TODO Auto-generated method stub
		return sysSettingDao.gett();
	}
}
