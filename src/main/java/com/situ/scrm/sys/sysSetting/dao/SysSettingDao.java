package com.situ.scrm.sys.sysSetting.dao;

import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.sysSetting.domain.SysSetting;

@Repository
public interface SysSettingDao extends BaseDao<SysSetting>{

	SysSetting findBysysSet(SysSetting sysSet);

	SysSetting gett();


	
	

}
