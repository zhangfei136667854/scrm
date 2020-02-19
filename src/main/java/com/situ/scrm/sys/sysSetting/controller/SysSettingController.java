package com.situ.scrm.sys.sysSetting.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.sys.sysSetting.domain.SysSetting;
import com.situ.scrm.sys.sysSetting.service.SysSettingService;
@RestController
@RequestMapping("/sysSetting")
public class SysSettingController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SYSSETTING_INDEX = "sys/sysSetting/sysSetting_index";
	@Autowired
	private SysSettingService sysSettingService ;
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.addObject("sysSetting",sysSettingService.doget());
		modelAndView.setViewName(SYSSETTING_INDEX);
		return modelAndView ;
	}
	@PutMapping
	public Long doPostOrPut(SysSetting sysSet) {
		return sysSettingService.doPut(sysSet);
	}
    
}
