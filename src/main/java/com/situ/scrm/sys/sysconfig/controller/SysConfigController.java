package com.situ.scrm.sys.sysconfig.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.sys.sysconfig.domain.SysConfig;
import com.situ.scrm.sys.sysconfig.service.SysConfigService;
/**
 * 系统设置
 * @author 14850
 *
 */
@RestController
@RequestMapping("/sysconfig")
public class SysConfigController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_SYSCONFIG_INDEX = "sys/sysconfig/sysconfig_index";
	@Autowired
	private SysConfigService sysConfigService;
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.addObject("sysConfig", sysConfigService.getSysConfig());
		modelAndView.setViewName(PAGE_SYSCONFIG_INDEX);
		return modelAndView;
	}
	@PutMapping
	public Integer updateSysConfig(SysConfig sysconfig) {
		return sysConfigService.updateSysConfig(sysconfig);
	}
}
