package com.situ.scrm.sys.sysconfig.service.impl;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.sys.sysconfig.dao.SysConfigDao;
import com.situ.scrm.sys.sysconfig.domain.SysConfig;
import com.situ.scrm.sys.sysconfig.service.SysConfigService;
import com.situ.scrm.utils.DAOUtils;

@Service
public class SysConfigServiceImpl implements Serializable, SysConfigService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	@PostConstruct
	public void initData() { // 判断表中没有数据，则生成一条默认数据，应该保证表中只有一条数据
		SysConfig sysConfig = sysConfigDao.getConfig();
		if (sysConfig == null) {
			sysConfig = new SysConfig();
			sysConfig.setConfig1("1");
			sysConfig.setConfig2("1");
			sysConfig.setConfig3("1");
			sysConfig.setConfig4("1");
			sysConfig.setActiveFlag(1);
			sysConfig.setCreateBy("SYS");
			sysConfig.setCreateDate(new Date());
			sysConfigDao.save(sysConfig);
		}
	}

	@Override
	public Integer updateSysConfig(SysConfig sysconfig) {
		SysConfig editSysConfig=sysConfigDao.getConfig();
		editSysConfig=DAOUtils.buildEditData(editSysConfig, sysconfig);
		sysConfigDao.update(editSysConfig);
		return 1;
	}

	@Override
	public SysConfig getSysConfig() {
		return sysConfigDao.getConfig();
	}


}
