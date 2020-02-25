package com.situ.scrm.index.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.sys.user.service.UserService;

@RestController
@RequestMapping("/sys")
public class IndexController {
	private static final String SYS_INDEX ="index";
	private static final Logger LOG = Logger.getLogger(IndexController.class);
	@Autowired
	private UserService userService ;
	@GetMapping("/index")
	public ModelAndView goIndex(ModelAndView modelAndView,HttpSession session) {
		List<SysResource> resourceList = userService.findAuthResourceList(session);
		LOG.debug("这是查看到的菜单集合"+resourceList);
		modelAndView.addObject("resourceList",resourceList);
		modelAndView.setViewName(SYS_INDEX);
		return modelAndView;
	}

}
