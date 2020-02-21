package com.situ.scrm.sys.user.controller;

import java.io.Serializable;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.role.service.RoleService;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;
import com.situ.scrm.utils.DAOUtils;
@RestController
@RequestMapping("/user")
public class UserController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String USER_INDEX ="sys/user/user_index";
	private static final String USER_LIST="sys/user/user_add_edit";
	private static final String USER_OPTION = "sys/user/user_option";
	private static final  Logger LOG = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService ;
	@Autowired
	private RoleService roleService ;
	/**
	 * 
	 * @param modelAndView
	 * @return进首页
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(USER_INDEX);
		return modelAndView ;
	}
	/**
	 * 
	 * @param page
	 * @param limit
	 * @param user
	 * @return分页查询用户
	 */
	@GetMapping("/{page}/{limit}")
	public LayResult  doFind (@PathVariable Integer page,@PathVariable Integer limit,User user) {
		
				
				return userService.findByPage(page,limit,user);
	}
	/**
	 * 
	 * @param modelAndView
	 * @return新增的表单
	 */
	@GetMapping("/form")
	public ModelAndView doFormList(ModelAndView modelAndView) {
		modelAndView.addObject("roleList",roleService.findAllRole());
		modelAndView.setViewName(USER_LIST);
		return modelAndView;
	}
	@GetMapping("/findByUserLevel/{userLevel}")
	public ModelAndView doFindByUserLevel(@PathVariable Integer userLevel,ModelAndView modelAndView) {
		modelAndView.setViewName(USER_OPTION);
		User user =userService.findByUserLevel(userLevel-1);
		modelAndView.addObject("user",user) ;
		 return modelAndView;
	}
	/**
	 * 
	 * @param user
	 * @return用胡新增
	 */
	@PostMapping
	public Long doPose(User user) {
		LOG.debug("这是userxinzeng"+user);
		return userService.saveUser(DAOUtils.buildSearchParam(user));
	}
	/**
	 * 
	 * @param rowId
	 * @return删除用户
	 */
	@DeleteMapping("/{rowId}")
	public Long doDelete(@PathVariable Long rowId) {
		return userService.daDelete(rowId);
	}
	/**
	 * 
	 * @param rowId
	 * @return根据rowid查询实例
	 */
	@GetMapping("/{rowId}")
	public User doGetUser(@PathVariable Long rowId) {
		return userService.getByRowId(rowId);
	}
	@PutMapping
	public Long doUpdate(User user) {
		return userService.doUpdate(user);
	
	}
	@PutMapping("/{isLock}")
public Long UpdateIsLock(@PathVariable Integer isLock) {
		return userService.updateIsLock(isLock);
	}

}