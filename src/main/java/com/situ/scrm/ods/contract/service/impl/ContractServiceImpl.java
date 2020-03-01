package com.situ.scrm.ods.contract.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.ods.contract.dao.ContractDao;
import com.situ.scrm.ods.contract.domain.Contract;
import com.situ.scrm.ods.contract.service.ContractService;
import com.situ.scrm.ods.customer.dao.CustomerDao;
import com.situ.scrm.sys.dictionary.dao.DictionaryDao;
import com.situ.scrm.sys.dictionary.domain.Dictionary;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.utils.AppConfig;
import com.situ.scrm.utils.DAOUtils;
@Service
public class ContractServiceImpl implements Serializable, ContractService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private SysCountUtils sysCountUtils;
	@Autowired
	private ContractDao contractDao ;
	@Autowired
	private CustomerDao customerDao ;
	@Override
	public Long doPost(Contract cont,HttpSession session) {
		User user =(User)session.getAttribute(AppConfig.SESSION_LOGIN_USER);
		
		List<Dictionary> dicList = dictionaryDao.find();
		String contTypeVal = null;
		String contStatusVal = null;
		if(dicList!=null) {
			for(Dictionary dic:dicList) {
				if(cont.getContStatus().equals(dic.getDicKey())) {
					contStatusVal =dic.getDicValue();
				}
				if(cont.getContType().equals(dic.getDicKey())) {
					contTypeVal =dic.getDicValue();
				}
			}
		}
		cont.setContTypeVal(contTypeVal);
		cont.setContStatusVal(contStatusVal);
		cont.setContCode(sysCountUtils.buildRoleCode());
		cont.setUserCode(user.getUserCode());
		cont.setUserName(user.getUserName());
		cont.setParentCode(user.getParentCode());
		//以后要改
		cont.setNextContDate(new Date());
		cont.setIsRemind(0);
		cont.setIsClose(0);
		cont.setActiveFlag(1);
		cont.setCreateBy("sys");
		cont.setCreateDate(new Date());
		contractDao.save(cont);
		
		//将此客户改为非公海状态
		
		return customerDao.updateByCusCode(cont.getCusCode());
	}
	@Override
	public LayResult doFind(Integer page, Integer limit, Contract cont,HttpSession session) {
		Contract searchParam =DAOUtils.buildSearchParam(cont);
		User user =(User)session.getAttribute(AppConfig.SESSION_LOGIN_USER);
		Integer dataCount =contractDao.getCount();
		List<Contract> contList =null;
		if(user.getUserKind()==1) {
			 contList = contractDao.findByPage(DAOUtils.buildPagination(page, limit),searchParam );
		}
			//查看经理级别的
			if(user.getUserLevel()==2) {
				contList=contractDao.findByPageAndParentCode(DAOUtils.buildPagination(page, limit), user.getUserCode());
			}
			//查看员工级别得
			if(user.getUserLevel()==3) {
				contList=contractDao.findByPageAndUserCode(DAOUtils.buildPagination(page, limit), user.getUserCode());
			}
		
		
		
	
		return new LayResult(0,"",dataCount,contList);
	}

}
