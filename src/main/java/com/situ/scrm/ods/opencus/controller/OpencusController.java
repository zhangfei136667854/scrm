package com.situ.scrm.ods.opencus.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/opencus")
public class OpencusController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String OPENCUS_INDEX = "ods/opencus/opencus_index";
	@GetMapping
	public ModelAndView  goOpencusIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(OPENCUS_INDEX);
		return modelAndView ;
	}

}
