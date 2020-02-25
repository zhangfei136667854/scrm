package com.situ.scrm.sys.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.role.dao.RoleDao;
import com.situ.scrm.sys.role.dao.RoleResourceDao;
import com.situ.scrm.sys.sysresource.dao.SysActionInfoDao;
import com.situ.scrm.sys.sysresource.dao.SysResourceDao;
import com.situ.scrm.sys.sysresource.domain.SysActionInfo;
import com.situ.scrm.sys.sysresource.domain.SysResource;
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
	@Autowired
	private SysActionInfoDao sysActionDao ;
	@Autowired
	private RoleResourceDao roleResourceDao ;
	@Autowired
	private RoleDao roleDao ;
	@Autowired
	private SysResourceDao  sysResourceDao ;

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
	public Long updateIsLock(Long rowId) {
		User user= userDao.getUser(rowId);
		Integer isLock = user.getIsLock();
		if(isLock==0) {
			return userDao.updateByIsLock(1,rowId);
		}else {
			return userDao.updateByIsLock(0,rowId);
		}
		
	}

	@Override
	public User userLogin(User user,HttpSession session) {
	String userPass = MD5Utils.encode(user.getUserPass());
	user.setUserPass(userPass);
	User user1 = userDao.login(user);
	if(user1!=null) {
		session.setAttribute("user", user1);
	}
	Integer userKind=user1.getUserKind();//用户类型1:超级员工 0:普通员工
	//用户根据角色对应的动作数据集合
		List<SysActionInfo> actionInfoList=null;
		if(userKind==1) {//超级员工
			//拥有所有的actionInfo集合数据
			actionInfoList=sysActionDao.find();
		}else {//普通用户
			List<String> rescodeList=roleResourceDao.findCode(roleDao.findByRoleCode(user1.getRoleCode()));
			if(rescodeList!=null) {
				actionInfoList=new ArrayList<SysActionInfo>();
				List<SysActionInfo> allActionInfoList =sysActionDao.find();
				if(allActionInfoList!=null) {
					for(SysActionInfo sysActionInfo:allActionInfoList) {
						if(rescodeList.contains(sysActionInfo.getRescCode())) {
							actionInfoList.add(sysActionInfo);
						}
					}
				}
			}
		}
		//开始整理动作数据
		if(actionInfoList!=null) {
			Map<String,Set<String>> actionInfoMap =new HashMap<String,Set<String>>();
			for(SysActionInfo actionInfo:actionInfoList) {
				String method = actionInfo.getMethod();
				String actionUrl = actionInfo.getActionUrl();
				Set<String> actionUrlSet = actionInfoMap.get(method);
				if(actionUrlSet==null) {
					actionUrlSet = new HashSet<String>();
					
				}
				actionUrlSet.add(actionUrl.replaceAll("\\{\\w*\\}","\\\\w+"));
				actionInfoMap.put(method,actionUrlSet);
			}
			LOG.debug("这是会话里面的数据actionInfoMap"+actionInfoMap);
			session.setAttribute("actionInfoMap", actionInfoMap);
		}
		
		
		
		
		return user1;
	}

	@Override
	public List<SysResource> findAuthResourceList(HttpSession session) {
	User loginUser =(User)session.getAttribute("user");
	String roleCode = loginUser.getRoleCode();
	Integer userKind =loginUser.getUserKind();// 用户类型#1 :超级用户;0:普通用户;
	List<SysResource> resourceList = null;
	if(userKind==1) {
		resourceList =sysResourceDao.find(); 
		
	}else {
		if(roleCode!=null) {
			Long roleId =roleDao.findByRoleCode(roleCode);
			List<String> rescCodeList = roleResourceDao.findCode(roleId);
			resourceList = new ArrayList<SysResource>();
			List<SysResource> allResourceList = sysResourceDao.find();
			for (SysResource sysResource : allResourceList) {
				if (rescCodeList != null && rescCodeList. contains( sysResource.getRescCode())) {
					resourceList.add( sysResource);
				}
			}
			
			
		}
	}
	
		return bulidAuthResourceList(resourceList);
	}

	private List<SysResource> bulidAuthResourceList(List<SysResource> resourceList) {
		
		Map<String, List<SysResource>> resourceMap = new HashMap<String, List<SysResource>>();
		for (SysResource sysResource : resourceList) {
			String parentCode = sysResource.getParentCode();
			List<SysResource> list = resourceMap.get(parentCode);
			if (list == null) {
				list = new ArrayList<SysResource>();
			}
			list.add(sysResource);
			resourceMap.put(parentCode, list);
		}
		
		for (SysResource sysResource : resourceList) {
			//如果是一级资源
			if(sysResource.getParentCode().equals(SysResource.DEFAULT_PARENT_CODE)) {
				//查询子资源
				List<SysResource> children=resourceMap.get(sysResource.getRescCode());
			    if(children!=null) {
			    	//设置子资源
			    	sysResource.setChildren(children);
			    }
			
			}
			
			}
		
		
		
		return resourceList;
		
	}

}
