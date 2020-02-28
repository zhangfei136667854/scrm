package com.situ.scrm.sys.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.situ.scrm.utils.AppConfig;
import com.situ.scrm.utils.DAOUtils;
import com.situ.scrm.utils.MD5Utils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleResourceDao roleResourceDao;
	@Autowired
	private SysActionInfoDao sysActionInfoDao;
	@Autowired
	private SysResourceDao sysResourceDao;

	/**
	 * @Title: checkUserCode
	 * @Description:(检测名称唯一)
	 * @param userCode
	 * @return
	 */
	@Override
	public Integer checkUserCode(String userCode) {
		User checkUser = userDao.getUserByCode(userCode);
		return checkUser != null ? 1 : 0;
	}

	/**
	 * @Title: saveUser
	 * @Description:(新增功能)
	 * @param User
	 * @return
	 */
	@Override
	public Long saveUser(User user) {
		// String parentCode=user.getUserCode();
		user.setUserPass(MD5Utils.encode(user.getUserPass()));
		user.setIsLock(User.IS_LOCK_NO);
		// user.setParentCode(parentCode);
		user.setLoginCount(1);
		user.setCreateBy("SYS");
		user.setCreateDate(new Date());
		user.setActiveFlag(1);
		user.setLoginDate(new Date());
		user.setIsLock(0);
		if (user.getUserLevel() == 1) {
			user.setUserKind(1);
		} else {
			user.setUserKind(0);
		}
		userDao.save(user);
		return user.getRowId();
	}

	@Override
	public Integer doDeleteUser(Long rowId) {
		userDao.delete(rowId);
		return 1;
	}

	@Override
	public User getUser(Long rowId) {
		return userDao.get(rowId);
	}

	@Override
	public Integer doEditUser(User user) {
		Long rowId = user.getRowId();
		User editUser = userDao.get(rowId);
		userDao.update(DAOUtils.buildEditData(editUser, user));
		return 1;
	}

	@Override
	public Integer getCount(User searchUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LayResult findUserByPage(Integer page, Integer limit, User searchUser) {
		// 通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的拼写
		User searchParam = DAOUtils.buildSearchParam(searchUser);
		// 查询出符合条件的一共有多少条记录。
		Integer dataCount = userDao.getCount(searchParam);
		// 根据分页的请求信息查询出数量列表。
		List<User> roleList = userDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, roleList);
	}

	@Override
	public List<User> findByUserLevel(int i) {
		return userDao.findByLevel(i);
	}

	@Override
	public Integer update4Lock(Long rowId, Integer isLock) {
		userDao.update4Lock(rowId, isLock);
		return 1;
	}

	@Override
	public LayResult doUserLogin(User loginUserParam, HttpSession session, HttpServletResponse response) {
		Integer code = 0;
		String msg = "";
		String userCode = loginUserParam.getUserCode();
		String userPass = loginUserParam.getUserPass();
		User loginUser = userDao.findByCodeAndPass(userCode, MD5Utils.encode(userPass));// 根据账号密码 查询一个实例
		if (loginUser != null) {// 有这个用户
			Integer isLock = loginUser.getIsLock();// 得到这个用户是否锁定
			if (isLock == 0) {// 如果没被锁定
				code = 1;// 登陆成功
				hanlderUserLoginEdData(loginUser, session);// 把这个用户的所有信息放到session里
			} else {
				msg = "用户被锁定,请联系管理员!";
			}
		} else {
			msg = "用户名或密码错误!";
		}

		return new LayResult(code, msg, null);
	}

	private void hanlderUserLoginEdData(User loginUser, HttpSession session) {// 把这个用户的所有信息放到session里
		session.setAttribute(AppConfig.SESSION_LOGIN_USER, loginUser);

		List<SysActionInfo> actionInfoList = null;// 定义动作集合
		Integer userKind = loginUser.getUserKind();// 取出当前用户类型
		if (userKind == 1) {// 超级用户
			actionInfoList = sysActionInfoDao.find();// 查询出所有的动作信息
		} else {// 如果是普通用户
			String roleCode = loginUser.getRoleCode();// 取出当前用户的roleCode
			if (roleCode != null) {
				Long roleId = roleDao.getByCode(roleCode).getRowId();// 查处一个角色role，然后取到他的rowId，也是角色资源表的roleId
				System.out.println("0000000000000000000000000" + roleId);// 打印看看取出来没有
				// Long roleId = roleResourceDao.getByCode(roleCode);
				List<String> rescCodeList = roleResourceDao.findCode(roleId);// 根据roleId查询出一个角色的所有资源编号rescCode的集合
				if (rescCodeList != null) {
					actionInfoList = new ArrayList<SysActionInfo>();
					List<SysActionInfo> allActionInfoList = sysActionInfoDao.find();// 查询所有的动作信息
					if (allActionInfoList != null) {
						for (SysActionInfo sysActionInfo : allActionInfoList) {
							if (rescCodeList.contains(sysActionInfo.getRescCode())) {// 如果资源编号的集合包含在所有动作资源内，就将其追加到所有动作集合
								actionInfoList.add(sysActionInfo);
							}
						}
					}

				}

			}
		}
		if (actionInfoList != null) {
			Map<String, Set<String>> actionInfoMap = new HashMap<String, Set<String>>();
			for (SysActionInfo actionInfo : actionInfoList) {
				String method = actionInfo.getMethod();

				String actionUrl = actionInfo.getActionUrl();

				Set<String> actionUrlSet = actionInfoMap.get(method);
				if (actionUrlSet == null) {
					actionUrlSet = new HashSet<String>();
				}
				actionUrlSet.add(actionUrl.replaceAll("\\{\\w*\\}", "\\\\w+"));
				actionInfoMap.put(method, actionUrlSet);
			}
			System.out.println("111111111111111111111111" + actionInfoMap.toString());//
			session.setAttribute(AppConfig.SESSION_RESOURCE_MAP_ROLE, actionInfoMap);

		}
	}

	@Override
	public List<SysResource> findAuthResourceList(HttpSession session) {
		User loginUser = (User) session.getAttribute(AppConfig.SESSION_LOGIN_USER);
		String roleCode = loginUser.getRoleCode();
		Integer userKind = loginUser.getUserKind();// 用户类型#1 :超级用户;0:普通用户;
		List<SysResource> resourceList = null;
		if (userKind == 1) {
			resourceList = sysResourceDao.find();
		} else {
			if (roleCode != null) {
				Long roleId = roleDao.findByRoleCode(roleCode);
				List<String> rescCodeList = roleResourceDao.findCode(roleId);
				resourceList = new ArrayList<SysResource>();
				List<SysResource> allResourceList = sysResourceDao.find();
				for (SysResource sysResource : allResourceList) {
					if (rescCodeList != null && rescCodeList.contains(sysResource.getRescCode())) {
						resourceList.add(sysResource);
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
			// 如果是一级资源
			if (sysResource.getParentCode().equals(SysResource.DEFAULT_PARENT_CODE)) {
				// 查询子资源
				//List<SysResource> children = sysResourceDao.findByParent(sysResource.getRescCode());
				List<SysResource> children = resourceMap.get(sysResource.getRescCode());

				if (children != null) {
					// 设置子资源
					sysResource.setChildren(children);
				}

			}

		}

		return resourceList;

	}

}
