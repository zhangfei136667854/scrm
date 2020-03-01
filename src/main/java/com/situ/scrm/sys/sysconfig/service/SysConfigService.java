package com.situ.scrm.sys.sysconfig.service;



import java.util.Date;

import com.situ.scrm.sys.sysconfig.domain.SysConfig;

public interface SysConfigService {

	void initData();//初始化一条系统信息

	Integer updateSysConfig(SysConfig sysconfig);//修改系统阀值

	SysConfig getSysConfig();

Date getNextContDate();

	

}
