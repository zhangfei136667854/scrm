package com.situ.scrm.sys.user.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.user.dao.UserDao;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;
import com.situ.scrm.utils.DAOUtils;
import com.situ.scrm.utils.MD5Utils;
@Service
public class UserServiceImpl implements Serializable, UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao ;

	@Override
	public LayResult findByPage(Integer page, Integer limit, User user) {
		User user1=DAOUtils.buildSearchParam(user);
		//查看有多少数据
		Integer dataCount = userDao.get();
		//根据分页查询数据
		List<User> userList = userDao.findBYpage(DAOUtils.buildPagination(page, limit),user1);
		return new LayResult(0,"",dataCount,userList);
	}

	@Override
	public User findByUserLevel(int i) {
		
		
		return userDao.findByLevel(i);
	}

	@Override
	public Long saveUser(User buildSearchParam) {
		String userPass = MD5Utils.encode(buildSearchParam.getUserPass());
		buildSearchParam.setUserPass(userPass);
		buildSearchParam.setLoginCount(1);
		buildSearchParam.setLoginDate(new Date());
		buildSearchParam.setIsLock(0);
		buildSearchParam.setActiveFlag(1);
		if(buildSearchParam.getUserLevel()==1) {
			buildSearchParam.setUserKind(1);
			
		}else {
			buildSearchParam.setUserKind(0);
		}
		
		return userDao.save(buildSearchParam);
	}

	@Override
	public Long daDelete(Long rowId) {
		// TODO Auto-generated method stub
		return userDao.delete(rowId);
	}

	@Override
	public User getByRowId(Long rowId) {
		User user =userDao.getUser(rowId);
		User user1 = userDao.findByLevel(user.getUserLevel()-1);
		user.setParentCode(user1.getUserCode());
		user.setParentName(user1.getUserName());
		return user;
	}

	@Override
	public Long doUpdate(User user) {
		LOG.debug("这是要修改的user"+user);
		return userDao.update(user);
	}

	@Override
	public Long updateIsLock(Integer isLock) {
		if(isLock==0) {
			return userDao.updateByIsLock(1);
		}else {
			return userDao.updateByIsLock(0);
		}
		
	}

}
