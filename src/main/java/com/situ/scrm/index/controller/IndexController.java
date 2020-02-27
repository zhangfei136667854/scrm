package com.situ.scrm.index.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;

@RestController
@RequestMapping("/go")
public class IndexController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX = "index";
	private static final String PAGE_LOGIN = "login";
	private static final Logger LOG=Logger.getLogger(IndexController.class);
	@Autowired
	private UserService userService;
	/**
	 * @Title: goIndex 
	 * @Description:(进系统首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView goIndex(ModelAndView modelAndView,HttpSession session) {
		List<SysResource> resourceList = userService.findAuthResourceList(session);
		LOG.debug("这是查看到的菜单集合"+resourceList);
		modelAndView.addObject("resourceList",resourceList);
		modelAndView.setViewName(PAGE_INDEX);
		return modelAndView;
	}
	/**
	 * @Title: goIndex 
	 * @Description:(进系统首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/login")
	public ModelAndView goLogin(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_LOGIN);
		return modelAndView;
	}
	@GetMapping("/dologin")
	public LayResult doLogin(User loginUserParam,HttpSession session, HttpServletResponse response) {
		return userService.doUserLogin(loginUserParam, session, response);
	}
	@GetMapping("/loginout")
	public ModelAndView Exit(ModelAndView modelAndView,HttpServletRequest request , HttpServletResponse response) {
		request.getSession().invalidate();
		modelAndView.setViewName(PAGE_LOGIN);
		return modelAndView;
	}

}
