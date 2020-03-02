package com.situ.scrm.ods.chart.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.ods.chart.domain.Data;
import com.situ.scrm.ods.chart.service.ChartService;
import com.situ.scrm.ods.contract.dao.ContractDao;
import com.situ.scrm.ods.customer.dao.CustomerDao;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.utils.AppConfig;

@Service
public class ChartServiceImpl implements Serializable, ChartService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Autowired
private ContractDao contractDao ;
@Autowired
private CustomerDao customerDao ;
@Override
public List<Data> getType(HttpSession session) {
	User user = (User)session.getAttribute(AppConfig.SESSION_LOGIN_USER);
	List<Data> dataList =null;
	if(user.getUserLevel()==1) {
		//查看所有跟单得集合查看dataCount contTypeVal
		 dataList = contractDao.find();
	}else if(user.getUserLevel()==2) {
		//查询所属业务员得跟单
		dataList=contractDao.finByParentCode(user.getUserCode());
	}else{
		//根据用户code查
		dataList=contractDao.findByUserCode(user.getUserCode());
	}
	
	return dataList;
}
@Override
public List<Data> getFrom(HttpSession session) {
	User user = (User)session.getAttribute(AppConfig.SESSION_LOGIN_USER);
	List<Data> dataList =null;
	if(user.getUserLevel()==1) {
		//查看所有跟单得集合查看dataCount contTypeVal
		 dataList = customerDao.findAll();
	}else if(user.getUserLevel()==2) {
		//查询所属业务员得跟单
		dataList=customerDao.finByParentCode(user.getUserCode());
	}else{
		//根据用户code查
		dataList=customerDao.findByUserCode(user.getUserCode());
	}
	return dataList;
}
}
